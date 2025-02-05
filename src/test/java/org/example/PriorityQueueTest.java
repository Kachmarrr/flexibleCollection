package org.example;

import org.example.PriorityQueue.PriorityQueue;
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

        //GIVEN
        //WHEN
        //THEN

        queueLIFO.put("Test1", 1);
        queueLIFO.put("Test2", 3);
        queueLIFO.put("Test5");

        String result = queueLIFO.get();

        assertEquals("Test1", result);
    }

    @Test
    public void testPutFIFO() {

        queueFIFO.put("Test1", 3);
        queueFIFO.put("Test2", 1);
        queueFIFO.put("Test3");

        String result = queueFIFO.get();

        assertEquals("Test2", result);
    }

    @Test
    public void testGet() {

        queueFIFO.put("Test1", 4);
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
    public void testZeroArgumentInConstructorPriority() {

        assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<String>(0, PriorityQueue.Methods.FIFO);
        });

    }

    @Test
    public void testWrongArgumentInConstructorPriorityNode(){

        assertThrows(IllegalArgumentException.class, () -> {
            queueLIFO.put("Test", -1);
        });

    }

    @Test
    public void testZeroArgumentInConstructorPriorityNode(){

        assertThrows(IllegalArgumentException.class, () -> {
            queueLIFO.put("Test", 0);
        });

    }

    @Test
    public void queueIsEmptyTest(){

        assertThrows(IllegalStateException.class, () -> {
            queueLIFO.get();
        });

    }

    @Test
    public void MultithreadingLIFO() {


        Runnable producer = () -> {
            queueLIFO.put("Test1");
            queueLIFO.put("Test2", 1);
            queueLIFO.put("Test3");
            queueLIFO.put("Test4");
            queueLIFO.put("Test5", 3);
        };

        Runnable consumer = () -> {
            queueLIFO.put("Test6", 1);
            queueLIFO.put("Test7", 3);
            queueLIFO.put("Test8");
            queueLIFO.put("Test9");
            queueLIFO.put("Test10", 1);
        };

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        queueLIFO.printer();

        String result = queueLIFO.get();

        assertEquals("Test10", result);

    }

    @Test
    public void MultithreadingFIFO() {

        Runnable producer = () -> {
            queueFIFO.put("Test1");
            queueFIFO.put("Test2", 1);
            queueFIFO.put("Test3");
            queueFIFO.put("Test4");
            queueFIFO.put("Test5", 3);
        };

        Runnable consumer = () -> {
            queueFIFO.put("Test6", 1);
            queueFIFO.put("Test7", 3);
            queueFIFO.put("Test8");
            queueFIFO.put("Test9");
            queueFIFO.put("Test10", 1);
        };

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        queueFIFO.printer();

        String result = queueFIFO.get();

        assertEquals("Test2", result);
    }

    @Test
    public void getSizeTest() {

        queueLIFO.put("Test1", 2);
        queueLIFO.put("Test2", 1);
        queueLIFO.put("Test3", 3);
        queueLIFO.put("Test4", 2);
        queueLIFO.put("Test5", 1);

        int size = queueLIFO.size();

        assertEquals(5, size);
    }
}