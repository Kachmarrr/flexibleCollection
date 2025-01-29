package org.example;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<String> qu = new PriorityQueue<>(2, "FIFO");

        qu.put("Мax");
        qu.put("Ihor", 1);
        qu.put("Ivan");
        qu.put("Piter");
        qu.put("Pit", 3);
        qu.put("Alex", 1);
        qu.put("Anton", 3);
        qu.printer();

        System.out.println("\n");
        qu.get();
        qu.get();
        qu.get();
        qu.get();
        qu.get();

        System.out.println(qu.firstInFirstOut());

        qu.printer();
    }
}