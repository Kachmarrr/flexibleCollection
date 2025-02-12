package org.example.AggregationList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AggregationList<T> extends ArrayList<T> {

    public <K> Map<K, List<T>> groupBy(Function<? super T, ? extends K> keyFunction) {
        return this.stream()
                .collect(
                        Collectors.groupingBy(keyFunction)
                );
    }

    public <K> Map<K, List<T>> mapReduce(Function<? super T, ? extends K> keyFunction, Predicate<? super T> predicate) {
        return this.stream()
                .filter(predicate)
                .collect(
                        Collectors.groupingBy(keyFunction)
                );
    }

    public void filter(Predicate<? super T> predicate) {

        this.removeIf(predicate);
    }

    public <U extends Comparable<? super U>> void sort(Function<? super T, ? extends U> sortFunction) {

        this.sort(Comparator.comparing(sortFunction));
    }

    public <R> List<R> transform(Function<? super T, R> mapFunction) {

        return this.stream()
                .map(mapFunction)
                .collect(Collectors.toList());
    }

    public List<T> find(Predicate<? super T> predicate) {

        return this.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}