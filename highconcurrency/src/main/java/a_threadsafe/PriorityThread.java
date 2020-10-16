package a_threadsafe;

public class PriorityThread {

    public static class HighPriority extends Thread {
        static int count;

        @Override
        public void run() {

            while (true) {
                synchronized (HighPriority.class) {
                    count++;

                    if (count > 1000000) {
                        System.out.println(this.getClass().getName() + " is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {

        static int count;

        @Override
        public void run() {

            while (true) {
                synchronized (HighPriority.class) {
                    count++;

                    if (count > 1000000) {
                        System.out.println(this.getClass().getName() + " is complete");
                        break;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {

        HighPriority highPriority=new HighPriority();
        highPriority.setPriority(Thread.MAX_PRIORITY);

        LowPriority lowPriority=new LowPriority();
        lowPriority.setPriority(Thread.MIN_PRIORITY);

        highPriority.start();
        lowPriority.start();




    }


}
