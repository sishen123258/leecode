package c_threadpool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountForkJoinDemo extends RecursiveTask<Long> {

    private static int THRESHOLD = 1000;

    Long start;
    Long end;


    public CountForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        Long sum = 0L;

        boolean canCompute = (end-start)<THRESHOLD;

        if (canCompute) {

            for (Long i = start; i <= end; i++) {
                sum += i;
            }

            System.out.println("canCompute = " + canCompute);
            System.out.println("sum = " + sum);

        } else {

            long step = (end - start) / 100;

            ArrayList<CountForkJoinDemo> arrayList = new ArrayList<>();

            Long pos = start;

            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;

                if (lastOne > end) lastOne = end;

                CountForkJoinDemo subtask = new CountForkJoinDemo(pos, lastOne);

                pos += step + 1;

                arrayList.add(subtask);

                subtask.fork();
            }

            for (CountForkJoinDemo item : arrayList) {
                item.join();
            }


        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool=new ForkJoinPool();

        CountForkJoinDemo demo=new CountForkJoinDemo(0L,1000000L);

        ForkJoinTask<Long> submit = forkJoinPool.submit(demo);

        Long res = submit.get();

        System.out.println("res = " + res);
    }
}
