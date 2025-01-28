package org.example;

import lombok.Getter;

public class PriorityQueue<T> {

    private Object[] elements;
    private int size;
    private int defaultPriority;

    public PriorityQueue(int defaultPriority) {
        elements = new Object[1];// Масив
        this.defaultPriority = defaultPriority;
    }

    /**
     * hello
     * @param element test
     * @param priority test
     */
    public void put(T element, int priority) {

        for (int i = 0; i < elements.length; i++) {
            if (((PriorityQueueNode<T>) elements[i]).getPriority() > ((PriorityQueueNode<T>) elements[i + 1]).getPriority()) {
                elements[size] = new PriorityQueueNode<>(element, priority);
                size++;

            }

        }
//        if (size == elements.length) {
//            resizeArray();
//        }
//        elements[size] = new PriorityQueueNode<>(element, priority);
//        size++;
    }

    public void put(T element){

    }


    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index does not exist: " + index);
        }
        return (T) elements[index];
    }

    private void resizeArray() {
        Object[] newArray = new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
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

    public void putInIf(T element, int priority) {
        if (size == elements.length) {
            resizeArray();
        }

        PriorityQueueNode<T> newNode = new PriorityQueueNode<>(element, priority);

        elements[size] = newNode;
        size++;

        sort();
    }

    private void toSwap(int first, int second) {
        Object dummy = elements[first];
        elements[first] = elements[second];
        elements[second] = dummy;
    }

    public void sort() {
        for (int out = size - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (((PriorityQueueNode<T>) elements[in]).getPriority() > ((PriorityQueueNode<T>) elements[in + 1]).getPriority()) {
                    toSwap(in, in + 1);
                }
            }
        }
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