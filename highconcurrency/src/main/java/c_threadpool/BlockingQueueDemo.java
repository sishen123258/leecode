package c_threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<Integer> synchronousQueue=new SynchronousQueue<>();


        Integer poll = synchronousQueue.poll();
        System.out.println("poll = " + poll);

        ArrayBlockingQueue<Integer> arrayBlockingQueue=new ArrayBlockingQueue(10);
        arrayBlockingQueue.add(1);
        arrayBlockingQueue.put(1);

        LinkedBlockingQueue<Integer> linkedBlockingQueue=new LinkedBlockingQueue<>();
        linkedBlockingQueue.put(1);

        PriorityBlockingQueue<Integer> priorityBlockingQueue=new PriorityBlockingQueue();
        priorityBlockingQueue.add(1);
        priorityBlockingQueue.add(1);

    }



}
