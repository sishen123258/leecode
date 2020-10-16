package a_threadsafe;


public class DemonThread implements Runnable {
    @Override
    public void run() {
        while (true){
        System.out.println("I am alive.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new DemonThread());
        t.setDaemon(true);
        t.start();

        System.out.println("over");
        Thread.sleep(2000);
    }

}
