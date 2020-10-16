package a_threadsafe;


public class JoinThread {

    public static class Counter extends Thread{
        Integer i=0;

        @Override
        public void run() {
            for (i=0;i<1000000;i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Counter counter=new Counter();
        //counter 是被等待的线程
        counter.start();
        counter.join();

        System.out.println("counter = " + counter.i);
    }
}
