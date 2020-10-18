package b_jdkpackage;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable{

    ReentrantLock lock=new ReentrantLock();

    static int i;

    @Override
    public void run() {

        for (int j=0;j<10000000;j++){

            lock.lock();

            i++;

            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReenterLock target = new ReenterLock();
        Thread t = new Thread(target);
        Thread t2 = new Thread(target);

        t.start();
        t2.start();

        t.join();
        t2.join();

        System.out.println("i = " + i);

    }


}
