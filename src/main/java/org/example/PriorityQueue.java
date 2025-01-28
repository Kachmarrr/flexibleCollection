package org.example;

import lombok.Getter;

public class PriorityQueue<T> {

    private Object[] elements;
    private int size;
    private int defaultPriority;
    private methods method;

    public enum methods {
        LIFO,
        FIFO
    }

    public PriorityQueue(int defaultPriority, methods getMethods) {
        elements = new Object[1];// Масив
        this.defaultPriority = defaultPriority;
        this.method = getMethods;
    }
    /**
     * hello
     * @param element test
     * @param priority test
     */
    public void put(T element, int priority) {
        if (size == elements.length) {
            resizeArray();
        }

        int insertIndex = size;

        // залежить чи <= чи <
        if (method == methods.LIFO) {
            for (int i = 0; i < insertIndex; i++) {
                if (priority <= ((PriorityQueueNode<T>) elements[i]).getPriority()){
                    insertIndex = i;
                    break;
                }
            }
        }
        for (int i = 0; i < insertIndex; i++) {
            if (priority < ((PriorityQueueNode<T>) elements[i]).getPriority()){
                insertIndex = i;
                break;
            }
        }

        //суне масив в право
        for (int i = size; i > insertIndex; i--) {
            elements[i] = elements[i - 1];
        }

        elements[insertIndex] = new PriorityQueueNode<>(element, priority);
        size++;
    }

    public void put(T element){
        put(element, defaultPriority);
    }


    private void resizeArray() {
        Object[] newArray = new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    public void get() {

    }

    public T lastInFirstOut() {
        if (size == 0) {
            throw new IllegalStateException("the queue is empty!");
        }

        T lastElement = (T) elements[size - 1];

        elements[size - 1] = null;
        size--;

        return lastElement;
    }

    public T firstInFirstOut() {
        if (size == 0) {
            throw new IllegalStateException("the queue is empty!");
        }

        T firstElement = (T) elements[0];

        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;

        return firstElement;
    }


    public void printer() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }

    private static class PriorityQueueNode<T> {
        private T value;
        @Getter
        private int priority;
        private int defaultPriority;

        public PriorityQueueNode(T value, int priority) {
            this.value = value;
            this.priority = priority;

            if (priority <= 0 ){ // Exception in constructor
                throw new IllegalArgumentException("Priority are less or equals zero: " + priority);
            }
        }

        @Override
        public String toString() {
            return "Element: " + value + ", Priority: " + priority;
        }
    }
}