package a_threadsafe;

public class WaitThread {

    static Object object = new Object();

    public static class T1 extends Thread {

        @Override
        public void run() {

            synchronized (object) {
                System.out.println(System.currentTimeMillis() + " T1 start.");

                try {
                    System.out.println(System.currentTimeMillis() + " T1 start wait for object.");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + " T1 end.");
            }

        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + " T2 start.");

                System.out.println(System.currentTimeMillis() + " T2 start notify object.");
                object.notify();

                System.out.println(System.currentTimeMillis() + " T2 end.");

                //T1并不能立即继续执行，而是要等待T2释放object的锁，并重新成功获得锁后，才能继续执行
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread t1=new T1();
        Thread t2=new T2();

        t1.start();
        t2.start();
    }


}
