package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriorityQueueTest {

    private PriorityQueue<String> queueLIFO;
    private PriorityQueue<String> queueFIFO;

    @Before
    public void setUp() {
        queueLIFO = new PriorityQueue<>(2, PriorityQueue.Methods.LIFO);
        queueFIFO = new PriorityQueue<>(2, PriorityQueue.Methods.FIFO);
    }

    @Test
    public void testPutLIFO() {

        queueLIFO.put("Test1", 1);
        queueLIFO.put("Test2", 3);
        queueLIFO.put("Test3");

        String result = queueLIFO.get();

        assertEquals("Test1", result);
    }

    @Test
    public void testPutFIFO()  {

        queueFIFO.put("Test1", 3);
        queueFIFO.put("Test2", 1);
        queueFIFO.put("Test3");

        String result = queueFIFO.get();

        assertEquals("Test2", result);
    }

    @Test
    public void testGet() {

        queueFIFO.put("Test1",4 );
        queueFIFO.put("Test2", 4);
        queueFIFO.put("Test3");

        String result = queueFIFO.get();

        assertEquals("Test3", result);

    }

    @Test
    public void testMultiTypeTest() {
        PriorityQueue<String> qu = new PriorityQueue<>(2, PriorityQueue.Methods.FIFO);

        qu.put("Мax");
        qu.put("Ihor", 1);
        qu.put("Ivan");
        qu.put("Piter");
        qu.put("Pit", 3);
        qu.put("Alex", 1);
        qu.put("Anton", 3);

//        assertEquals("Anton", qu.lastInFirstOut());
//        assertEquals("Ihor", qu.firstInFirstOut());
//        assertEquals("Alex", qu.get());
    }

    @Test
    public void testSamePriorityGetLIFO() {
        queueLIFO.put("Test1", 3);
        queueLIFO.put("Test2", 3);
        queueLIFO.put("Test3", 3);

        String result = queueLIFO.get();

        assertEquals("Test3", result);

    }

    @Test
    public void testSamePriorityGetFIFO() {
        queueFIFO.put("Test1", 3);
        queueFIFO.put("Test2", 3);
        queueFIFO.put("Test3", 3);

        String result = queueFIFO.get();

        assertEquals("Test1", result);

    }

    @Test
    public void testWrongArgumentInConstructorPriority() {

        assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<String>(-1, PriorityQueue.Methods.FIFO);
        });

    }

    @Test
    public void Multithreading(){
        PriorityQueue<String> queue = new PriorityQueue<>(1, PriorityQueue.Methods.FIFO);

        Runnable producer = () -> {
            for (int i = 1; i <= 10; i++) {
                queue.put("Element " + i, i);
                System.out.println("Додано: Element " + i);
            }
        };

        Runnable consumer = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Отримано: " + queue.get());
            }
        };

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();


    }
}