package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<String> qu = new PriorityQueue<>(5);

        qu.put("Мax", 1);
        qu.put("Ivan", 2);
        qu.put("Piter", 2);
        qu.put("Pit", 3);

        qu.put("Alex", 1);

        qu.printer();

    }
}