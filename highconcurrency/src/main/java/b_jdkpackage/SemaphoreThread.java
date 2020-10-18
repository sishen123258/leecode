package b_jdkpackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreThread extends Thread{

    static Semaphore semaphore=new Semaphore(5);


    @Override
    public void run() {

        try {
            semaphore.acquire();

            Thread.sleep(1000);

            System.out.println(Thread.currentThread().getName()+" is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        SemaphoreThread semaphoreThread=new SemaphoreThread();

        for (int i = 0; i < 20; i++) {
            executorService.submit(semaphoreThread);
        }

        executorService.shutdown();
    }

}
