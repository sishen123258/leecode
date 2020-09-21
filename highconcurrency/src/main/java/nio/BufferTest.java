package nio;

import java.nio.IntBuffer;

public class BufferTest {

    public static void main(String[] args) {

        IntBuffer intBuffer=IntBuffer.allocate(10);
        int position = intBuffer.position();
        int limit = intBuffer.limit();
        int capacity = intBuffer.capacity();

        System.out.println("capacity = " + capacity);
        System.out.println("limit = " + limit);
        System.out.println("position = " + position);

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        position = intBuffer.position();
        limit = intBuffer.limit();
        capacity = intBuffer.capacity();

        System.out.println("\n");
        System.out.println("capacity = " + capacity);
        System.out.println("limit = " + limit);
        System.out.println("position = " + position);

        intBuffer.flip();

        position = intBuffer.position();
        limit = intBuffer.limit();
        capacity = intBuffer.capacity();

        System.out.println("\n");
        System.out.println("capacity = " + capacity);
        System.out.println("limit = " + limit);
        System.out.println("position = " + position);

    }
}
