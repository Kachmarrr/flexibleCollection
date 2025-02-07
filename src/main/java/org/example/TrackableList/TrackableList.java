package org.example.TrackableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TrackableList<T> extends ArrayList<T> {

    private final Optional<Predicate<? super T>> addPredicate;
    private final Optional<Consumer<? super T>> addConsumer;
    private final Optional<Predicate<? super T>> removePredicate;
    private final Optional<Consumer<? super T>> removeConsumer;

    private TrackableList(Predicate<? super T> addPredicate, Consumer<? super T> addConsumer, Predicate<? super T> removePredicate, Consumer<? super T> removeConsumer) {
        this.addPredicate = Optional.ofNullable(addPredicate);
        this.addConsumer = Optional.ofNullable(addConsumer);
        this.removePredicate = Optional.ofNullable(removePredicate);
        this.removeConsumer = Optional.ofNullable(removeConsumer);
    }

    @Override
    public boolean add(T object) {

        if (addPredicate.isPresent() && !addPredicate.get().test(object)) {
            return false;
        }

        addConsumer.ifPresent(consumer -> consumer.accept(object));

        return super.add(object);
    }

    @Override
    public boolean remove(Object object) {
        T castedObject = (T) object; // Приводимо до T

        if (removePredicate.isPresent() && !removePredicate.get().test(castedObject)) {
            return false;
        }

        removeConsumer.ifPresent(consumer -> consumer.accept(castedObject));

        return super.remove(object);
    }

    public static class TrackableListBuilder<T> {

        private Predicate<T> predicateForAdd = t -> true;
        private Predicate<T> predicateForRemove = t -> false;
        private Consumer<T> consumerForAdd = t -> {
        };
        private Consumer<T> consumerForRemove = t -> {
        };

        public TrackableListBuilder<T> addIf(Predicate<T> predicate) {
            predicateForAdd = predicateForAdd.and(predicate);
            return this;
        }

        public TrackableListBuilder<T> doWhenAdd(Consumer<T> consumer) {
            consumerForAdd = consumerForAdd.andThen(consumer);
            return this;
        }

        public TrackableListBuilder<T> removeIf(Predicate<T> predicate) {
            predicateForRemove = predicateForRemove.or(predicate);
            return this;
        }

        public TrackableListBuilder<T> doWhenRemove(Consumer<T> consumer) {
            consumerForRemove = consumerForRemove.andThen(consumer);
            return this;
        }

        public TrackableList<T> build() {
            return new TrackableList<>(predicateForAdd, consumerForAdd, predicateForRemove, consumerForRemove);
        }
    }

}