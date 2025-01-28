package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<String> qu = new PriorityQueue<>(2, PriorityQueue.methods.FIFO);

        qu.put("Мax", 3);
        qu.put("Ihor", 1);
        qu.put("Ivan", 2);
        qu.put("Piter", 2);
        qu.put("Pit", 3);

        qu.put("Alex", 1);

        qu.put("Anton");

        qu.printer();

        qu.get();

    }
}