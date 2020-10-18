package b_jdkpackage;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptLock extends Thread {

    static ReentrantLock l1 = new ReentrantLock();
    static ReentrantLock l2 = new ReentrantLock();

    int lock;

    public InterruptLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        try {
            if (this.lock == 1) {


                l1.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                l2.lockInterruptibly();


            } else {

                l2.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                l1.lockInterruptibly();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally {

            if(l1.isHeldByCurrentThread()){
                l1.unlock();
            }

            if(l2.isHeldByCurrentThread()){
                l2.unlock();
            }

            System.out.println(Thread.currentThread().getName()+" exit!");
        }

    }


    public static void main(String[] args) throws InterruptedException {


        InterruptLock t1 = new InterruptLock(1);
        InterruptLock t2 = new InterruptLock(2);

        t1.start();
        t2.start();
        Thread.sleep(1000);

        t2.interrupt();

    }
}
