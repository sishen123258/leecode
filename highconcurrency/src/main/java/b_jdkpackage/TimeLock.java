package b_jdkpackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable{


    static ReentrantLock lock=new ReentrantLock();
    @Override
    public void run() {

        try {

            if(lock.tryLock(500,TimeUnit.MILLISECONDS)){
                System.out.println("Lock success ");
                Thread.sleep(1000);
            }else{
                System.out.println("unlock ");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {


        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);

        t1.start();
        t2.start();



    }


}
