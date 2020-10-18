package b_jdkpackage;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierThread {

    static class BarrierRun implements Runnable{

        boolean tag;
        int N;

        public BarrierRun(boolean tag, int n) {
            this.tag = tag;
            N = n;
        }


        @Override
        public void run() {

            if(tag){
                System.out.println("Solider "+ N+ " 个，任务完成！ ");
            }else{
                System.out.println("Solider "+ N+ " 个，集合完毕！ ");
                tag=true;
            }
        }
    }

    static class Solider implements Runnable{

        CyclicBarrier cyclicBarrier;
        String name;

        public Solider(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {

            try {
                //集合
                cyclicBarrier.await();

                //dowork
                Thread.sleep(new Random().nextInt(10)*100);

                //完成任务
                cyclicBarrier.await();


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        boolean tag=false;
        int N=10;

        CyclicBarrier cyclicBarrier=new CyclicBarrier(N,new BarrierRun(tag,N));

        for (int i = 0; i < N; i++) {

            System.out.println("Solider 报道！");
            Thread thread = new Thread(new Solider(cyclicBarrier, "Solider " + i));
            thread.start();

        }


    }



}
