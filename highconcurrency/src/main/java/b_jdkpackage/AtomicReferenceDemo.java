package b_jdkpackage;

import sun.misc.Unsafe;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {


    static class Student{
        private String name;
        private String age;

        public Student(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(String age) {
            this.age = age;
        }

       /* @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Student student = (Student) o;

            if (name != null ? !name.equals(student.name) : student.name != null) return false;
            return age != null ? age.equals(student.age) : student.age == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (age != null ? age.hashCode() : 0);
            return result;
        }*/

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }


    static class StudentUpdate implements Runnable{

        private static AtomicReference<Student> atomicReference=new AtomicReference<>();
        static {
            atomicReference.set(new Student("a","a"));
        }


        @Override
        public void run() {

            Student student1 = atomicReference.get();

            Integer l = new Random().nextInt();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Student student2 = new Student(l.toString(), l.toString());

            boolean b = atomicReference.compareAndSet(student1, student2);

            if(b){
                System.out.println("Update success");
                System.out.println(student1.getAge().equals(student1.getName()));
            }else{
                System.out.println("Update failed");
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        StudentUpdate studentUpdate=new StudentUpdate();


        for (int i = 0; i < 1000; i++) {

            Thread thread = new Thread(studentUpdate);

            thread.start();

            thread.join();
        }


        System.out.println("End");


    }


}
