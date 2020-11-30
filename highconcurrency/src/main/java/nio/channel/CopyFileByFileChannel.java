package nio.channel;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileByFileChannel {

    static Integer CAPACITY=1024;
    static String srcPath="spark-warehouse\\dme_mapping_table.csv";
    static String dstPath="spark-warehouse\\dme_mapping_table_copy.csv";

    public static void main(String[] args) throws IOException {

        RandomAccessFile raf=new RandomAccessFile(srcPath,"r");

        FileChannel channel = raf.getChannel();

        ByteBuffer buffer=ByteBuffer.allocate(CAPACITY);

        File file = new File(dstPath);
        if(!file.exists()){
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);
        FileChannel writeChannel = fos.getChannel();

        int length=-1;
        while ((length=channel.read(buffer)) !=-1 ){

            //read mode
            buffer.flip();

            int outputLength=0;
            while ((outputLength=writeChannel.write(buffer))!=0){
                System.out.println("Write outputLength = " + outputLength);
            }

            //write mode
            buffer.clear();
        }

        // force write disk
        writeChannel.force(true);

        IOUtils.close(channel);
        IOUtils.close(writeChannel);
        IOUtils.close(raf);
        IOUtils.close(fos);

    }



}
