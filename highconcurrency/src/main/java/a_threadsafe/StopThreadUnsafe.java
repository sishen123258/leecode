package a_threadsafe;

public class StopThreadUnsafe {

    static User user = new User();

    public static class User {
        String userName;
        Long id;

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    public static class ChangeObjectUnsafe extends Thread {

        @Override
        public void run() {

            while (true) {
                synchronized (user) {

                    long id = System.currentTimeMillis();

                    user.setId(id);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    user.setUserName(String.valueOf(id));

                }

//                Thread.yield(); //暂停当前正在执行的线程对象，并执行其他线程
            }
        }
    }


    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.id!=null&&!user.id.toString().equals(user.userName)) {
                        System.out.println(user);
                    }
                }
//                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {


        new ReadObjectThread().start();

        while (true) {
            ChangeObjectUnsafe objectUnsafe = new ChangeObjectUnsafe();
            objectUnsafe.start();
            Thread.sleep(150);
            objectUnsafe.stop();
        }



    }


}
