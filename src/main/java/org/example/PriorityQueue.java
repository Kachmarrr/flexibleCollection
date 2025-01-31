package org.example;

import lombok.Getter;

public class PriorityQueue<T> {

    private Object[] elements;
    private int size;
    private int defaultPriority;
    private Methods method;

    public enum Methods {
        LIFO,
        FIFO
    }

    public PriorityQueue(int defaultPriority, Methods method) {
        elements = new Object[1];// Масив

        if (defaultPriority <= 0) {
            throw new IllegalArgumentException("Priority are less or equals zero: " + defaultPriority);
        }

        this.defaultPriority = defaultPriority;
        this.method = method;
    }

    /**
     * hello
     *
     * @param element  "Max"
     * @param priority 2
     */
    public synchronized void put(T element, int priority) {
        if (size == elements.length) {
            resizeArray();
        }

        int insertIndex = size;

        if (method == Methods.LIFO || method == Methods.FIFO) {
            boolean isLIFO = (method == Methods.LIFO);
            for (int i = 0; i < insertIndex; i++) {
                if ((isLIFO && priority <= ((PriorityQueueNode<T>) elements[i]).getPriority()) ||
                        (!isLIFO && priority < ((PriorityQueueNode<T>) elements[i]).getPriority())) {
                    insertIndex = i;
                    break;
                }
            }
        }

        //суне масив в право
        for (int i = size; i > insertIndex; i--) {
            elements[i] = elements[i - 1];
        }

        elements[insertIndex] = new PriorityQueueNode<>(element, priority);
        size++;
    }

    public synchronized void put(T element) {
        put(element, defaultPriority);
    }

    public T get() {
        if (size == 0) {
            throw new IllegalStateException("The queue is empty!");
        }

        PriorityQueueNode<T> node = (PriorityQueueNode<T>) elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;

        return node.value;
    }

    private void resizeArray() {
        Object[] newArray = new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
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

        public PriorityQueueNode(T value, int priority) {
            this.value = value;
            this.priority = priority;

            if (priority <= 0) { // Exception in constructor
                throw new IllegalArgumentException("Priority are less or equals zero: " + priority);
            }
        }

        @Override
        public String toString() {
            return "Element: " + value + ", Priority: " + priority;
        }
    }
}