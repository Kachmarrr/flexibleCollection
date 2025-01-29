package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriorityQueueTest {

    private PriorityQueue<String> queueLIFO;
    private PriorityQueue<String> queueFIFO;

    @Before
    public void setUp() {
        queueLIFO = new PriorityQueue<>(2, "LIFO");
        queueFIFO = new PriorityQueue<>(2, "FIFO");
    }

    @Test
    public void testPut() {

        queueLIFO.put("Test1", 1);
        queueLIFO.put("Test2", 3);
        queueLIFO.put("Test3");

        String result = queueLIFO.get();

        assertEquals("Test2", result);
    }

    @Test
    public void testGet() {

        queueFIFO.put("Test1");
        queueFIFO.put("Test2");
        queueFIFO.put("Test3");

        String result = queueFIFO.get();

        assertEquals("Test1", result);

    }

    @Test
    public void testLastInFirstOut() {

        queueLIFO.put("Test1");
        queueLIFO.put("Test2");
        queueLIFO.put("Test3");

        String result = queueLIFO.lastInFirstOut();

        assertEquals("Test1", result);
    }

    @Test
    public void testFirstInFirstOut() {

        queueFIFO.put("Test1");
        queueFIFO.put("Test2");
        queueFIFO.put("Test3");

        String result = queueFIFO.firstInFirstOut();

        assertEquals("Test1", result);
    }

    @Test
    public void testMultiTypeTest() {
        PriorityQueue<String> qu = new PriorityQueue<>(2, "FIFO");

        qu.put("Мax");
        qu.put("Ihor", 1);
        qu.put("Ivan");
        qu.put("Piter");
        qu.put("Pit", 3);
        qu.put("Alex", 1);
        qu.put("Anton", 3);

        assertEquals("Anton", qu.lastInFirstOut());
        assertEquals("Ihor", qu.firstInFirstOut());
        assertEquals("Alex", qu.get());
    }

    @Test
    public void testWrongArgumentInConstructorPriority() {

        assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<String>(-1, "FIFO");
        });

    }

    @Test
    public void testWrongArgumentInConstructorMethod() {

        assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<String>(2, "LILO");
        });

    }
}