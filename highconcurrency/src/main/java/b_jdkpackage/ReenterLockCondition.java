package b_jdkpackage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition extends Thread {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    @Override
    public void run() {

        try {
            lock.lock();
            System.out.println("Thread is awaiting!");
            condition.await();
            System.out.println("Thread is going!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {


        ReenterLockCondition t = new ReenterLockCondition();

        t.start();

        Thread.sleep(1000);

        lock.lock();
        condition.signal();
        lock.unlock();


    }
}
