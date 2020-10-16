package a_threadsafe;

public class InterruptedThread extends Thread{

    @Override
    public void run() {

        while (true){

            if(Thread.currentThread().isInterrupted()){
                System.out.println("Is interrupted");
                break;
            }

            Thread.yield();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        InterruptedThread interruptedThread=new InterruptedThread();

        interruptedThread.start();

        Thread.sleep(1000);

        interruptedThread.interrupt();

    }
}
