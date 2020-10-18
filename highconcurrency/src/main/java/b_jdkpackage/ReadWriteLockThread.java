package b_jdkpackage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockThread {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static ReentrantLock lock = new ReentrantLock();

    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock=readWriteLock.writeLock();


    static int value;

    public Integer handleRead(Lock lock) {

        try {
            lock.lock();

            Thread.sleep(1000);
            System.out.println("Read:"+value);

            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock){

        try {
            lock.lock();

            Thread.sleep(1000);

            value+=1;

            System.out.println("Write:"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockThread t=new ReadWriteLockThread();

        Runnable writeThread = new Runnable() {

            @Override
            public void run() {
                t.handleWrite(writeLock);
            }
        };

        Runnable readThread = new Runnable() {

            @Override
            public void run() {
                t.handleRead(readLock);
            }
        };


        for (int i = 18; i < 20; i++) {
            Thread thread = new Thread(writeThread);
            thread.start();

            thread.join();
        }

        for (int i = 0; i < 18; i++) {
            Thread thread = new Thread(readThread);
            thread.start();
            thread.join();
        }



        System.out.println("value = " + value);


    }

}
