package nio;

public class VolatileTest implements Runnable{

    volatile static int i=0;
    public void run() {

        for (int j = 0; j < 10000; j++) {
            i++;
        }

    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads=new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i]=new Thread(new VolatileTest());
            threads[i].start();
        }

        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println("VolatileTest.i = " + VolatileTest.i);
    }

}
