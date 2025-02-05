package org.example.TrackableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class TrackableList<T> extends ArrayList {

    private final List<T> list = new ArrayList<>();

    //Predicate
    /**
     * Отримує значення та умову, якщо умова повертає true,
     * тоді значення додається в список. rev0.9
     */
    public void whenAdd(T value, Predicate<? super T> predicate) {

        if (predicate.test(value)) {
            list.add(value);
        }
    }
    /**
     * Видаляє всі елементи які відповідають умові, можна зробити як з whenAdd,
     * тобто метод буде видаляти елемент який ми вказали, а не всі що підходять умові.
     * rev0.9
     */
    public void whenRemove(Predicate<? super T> predicate) {

        list.removeIf(predicate);

    }
    /**
     * Видаляє всі елементи, якщо хоча б один відповідає умові.
     * rev0.9
     */
    public void whenRemoveAll(Predicate<? super T> predicate) {

        if (list.stream()
                .anyMatch(predicate)) { // anyMatch повертає boolean
            list.clear();
        }
    }
    //Consumer:
    /**
     * Оскільки Consumer нічого не повертає, з ним можна придумати лише метод додавання.
     */
    public void whenAdd(T value, Consumer<? super T> consumer) {
        consumer.accept(value);
        list.add(value);
    }

    // чому потрібно переписати forEach, якщо ні він працювати не буде.
    public void forEach(Consumer consumer) {
        list.forEach(consumer);
    }

    public static class TrackableListBuilder {



    }

}