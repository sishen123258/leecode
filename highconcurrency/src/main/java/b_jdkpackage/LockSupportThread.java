package b_jdkpackage;

import java.util.concurrent.locks.LockSupport;

public class LockSupportThread  {


    static Object u=new Object();
    static  ChangeObjectThread t1=new ChangeObjectThread("t1");
    static  ChangeObjectThread t2=new ChangeObjectThread("t2");

    static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u){

                System.out.println("In = " + getName());
                LockSupport.park();

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        t1.start();
        Thread.sleep(1000);
        t2.start();

        LockSupport.unpark(t1);
        LockSupport.unpark(t2);

        t1.join();
        t2.join();

    }


}
