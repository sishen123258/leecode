package a_threadsafe;

public class ThreadGroupThread implements Runnable{


    @Override
    public void run() {
        String name = Thread.currentThread().getThreadGroup() + "_" + Thread.currentThread().getName();

        while (true){
            System.out.println("name = " + name);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("MyThreadGroup");

        Thread t1 = new Thread(threadGroup,new ThreadGroupThread());
        Thread t2 = new Thread(threadGroup,new ThreadGroupThread());

        t1.start();
        t2.start();

        int i = threadGroup.activeCount();
        System.out.println("i = " + i);

        threadGroup.list();

    }



}
