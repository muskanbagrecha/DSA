import org.example.MyQueue;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyQueueTest {

    @Test
    public void testEnqueueDequeue() {
        MyQueue<Integer> queue = new MyQueue<>(4);

        queue.enqueue(Integer.valueOf(10));
        queue.enqueue(Integer.valueOf(20));
        queue.enqueue(Integer.valueOf(30));

        assertEquals(Integer.valueOf(10), queue.dequeue());
        assertEquals(Integer.valueOf(20), queue.dequeue());
        assertEquals(Integer.valueOf(30), queue.dequeue());
    }

    @Test
    public void testEnqueueOverflow() {
        MyQueue<Integer> queue = new MyQueue<>(2);

        queue.enqueue(Integer.valueOf(10));
        queue.enqueue(Integer.valueOf(20));

        assertThrows(RuntimeException.class, () -> {
            queue.enqueue(Integer.valueOf(30)); // This should throw RuntimeException
        });
    }

    @Test
    public void testPeek() {
        MyQueue<Integer> queue = new MyQueue<>(4);

        queue.enqueue(Integer.valueOf(10));
        queue.enqueue(Integer.valueOf(20));

        assertEquals(Integer.valueOf(10), queue.peek());
        assertEquals(Integer.valueOf(10), queue.peek()); // Peek should not remove the element
        assertEquals(Integer.valueOf(10), queue.dequeue());
        assertEquals(Integer.valueOf(20), queue.dequeue());
    }

    @Test
    public void testIsEmpty() {
        MyQueue<Integer> queue = new MyQueue<>(4);

        assertTrue(queue.isEmpty());

        queue.enqueue(Integer.valueOf(10));

        assertFalse(queue.isEmpty());

        queue.dequeue();

        assertTrue(queue.isEmpty());
    }

}