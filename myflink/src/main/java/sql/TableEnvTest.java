package sql;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.api.DataTypes;
import java.util.UUID;

import static org.apache.flink.table.api.Expressions.$;

public class TableEnvTest {


    public static void main(String[] args) throws Exception {

        EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamExecutionEnvironment fsEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(fsEnv, fsSettings);

        DataStreamSource<String> uuidStream = fsEnv.addSource(new SourceFunction<String>() {
            boolean isRunning=true;

            @Override
            public void run(SourceContext<String> ctx) throws Exception {
                System.out.println("isRunning = " + isRunning);
                while (isRunning) {
                    String uuid = UUID.randomUUID().toString();
                    ctx.collect(uuid);
                    Thread.sleep(10L);
                }
            }
            @Override
            public void cancel() {
                this.isRunning=false;
            }
        });


//        Table table = tableEnv.fromDataStream(uuidStream);
        tableEnv.createTemporaryView("uuidSource",uuidStream);

        String tablePath = "C:\\Users\\TYue1\\IdeaProjects\\LeeCode\\leecode\\myflink\\log\\";
        tableEnv.connect(new FileSystem().path(tablePath))
                .withFormat(new Csv())
                .withSchema(new Schema().field("uuid", DataTypes.STRING()))
                .createTemporaryTable("uuidSink");

        Table table = tableEnv.from("uuidSource").select($("f0"));

        table.executeInsert("uuidSink");


    }
}
