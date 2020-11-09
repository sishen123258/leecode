package c_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

    static class Runner implements Runnable{

        @Override
        public void run() {

            long currentTimeMillis = System.currentTimeMillis()/1000;
            System.out.println("currentTimeMillis = " + currentTimeMillis);


        }
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.scheduleAtFixedRate(new Runner(),0,2, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(new Runner(),0,2, TimeUnit.SECONDS);

    }



}
