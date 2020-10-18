package b_jdkpackage;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLanuchThread implements Runnable{

    static CountDownLanuchThread thread=new CountDownLanuchThread();
    static CountDownLatch countDownLatch=new CountDownLatch(10);


    @Override
    public void run() {

        try {
            Thread.sleep(new Random().nextInt(10)*100);

            System.out.println("Check complete!");
            countDownLatch.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {

            executorService.submit(thread);

        }

        countDownLatch.await();

        System.out.println("Fire!");

        executorService.shutdown();

    }
}
