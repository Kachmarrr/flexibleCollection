package org.example;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {

//        PriorityQueue<String> qu = new PriorityQueue<>(2, PriorityQueue.Methods.LIFO);
//
//        qu.put("Мax");
//        qu.put("Ihor", 1);
//        qu.put("Ivan");
//        qu.put("Piter");
//        qu.put("Pit", 3);
//        qu.put("Alex", 1);
//        qu.put("Anton", 3);
//        qu.printer();

//        System.out.println("\n");
//        qu.get();
//        qu.get();
//        qu.get();
//        qu.get();
//        qu.get();

//        qu.printer();

        BlockingQueue<Type> blockingQueue = new LinkedBlockingDeque<>();

        PriorityQueue<String> qu = new PriorityQueue<>(1, PriorityQueue.Methods.FIFO);

        Runnable producer = () -> {

        qu.put("Мax");
        qu.put("Ihor", 1);
        qu.put("Ivan");
        qu.put("Piter");
        qu.put("Pit", 3);
        qu.put("Alex", 1);
        qu.put("Anton", 3);

        };

        Runnable consumer = () -> {

            qu.put("Мax");
            qu.put("Ihor", 1);
            qu.put("Alex", 1);
            qu.put("Ivan");
            qu.put("Anton", 3);
            qu.put("Piter");
            qu.put("Pit", 3);

        };

        blockingQueue.put();

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);



        t1.start();
        t2.start();

        qu.printer();

    }
}