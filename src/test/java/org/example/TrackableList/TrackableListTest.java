package org.example.TrackableList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class TrackableListTest {

    private TrackableList<Car> trackableList;

    Car car1 = new Car("Reno", "red", 800, 80);
    Car car2 = new Car("Mercedes", "black", 1600, 120);
    Car car3 = new Car("BMW", "blue", 700, 60);

    @Before
    public void setUp() {

        trackableList = new TrackableList.TrackableListBuilder<Car>()
                .addIf(car -> car.getBalance() >= 800)
                .addIf(car -> car.getSpeed() > 60)
                .removeIf(car -> car.getBalance() > 2000)
                .doWhenRemove(car -> car.setBalance(500))
                .doWhenAdd(Car::catchFair)
                .doWhenAdd(car -> {
                    car.setBalance(car.getBalance() + 1000);
                })
                .build();
    }

    @Test
    public void addTest() {

        trackableList.add(car1);

        int result = trackableList.size();
        assertEquals(1, result);
    }

    @Test
    public void conditionAddTest() {

        trackableList.add(car1);
        trackableList.add(car2);
        trackableList.add(car3);

        int result = trackableList.size();

        // car3 must fail by the condition, balance < 800
        assertEquals(2, result);
    }

    @Test
    public void manyConditionAddTest() {

        car2.setSpeed(70); // change car speed for Test

        trackableList.add(car1);
        trackableList.add(car2);
        trackableList.add(car3);

        assertEquals(car1, trackableList.getFirst());
    }

    @Test
    public void catchFairTest() {

        car1.setBalance(1000);

        trackableList.add(car1);

        // 2000 because, every car which add to list, passed the conditions, add 1000 to balance
        assertEquals(2000 - 400, trackableList.getFirst().getBalance());
    }

    @Test
    public void doWhenAddTest() {

        car1.setBalance(1000);
        car1.setSpeed(65);

        trackableList.add(car1);

        assertEquals(2000, trackableList.getFirst().getBalance());
    }

    @Test
    public void removeTest() {

        car1.setBalance(2000);

        trackableList.add(car1);
        assertEquals(1, trackableList.size());

        trackableList.remove(car1);
        assertEquals(0, trackableList.size());
    }

    @Test
    public void conditionRemoveTest() {

        trackableList.add(car1);
        trackableList.add(car2);

        // balance < 2000 - it should not be removed
        trackableList.remove(car1);
        trackableList.remove(car2);

        assertEquals(car1, trackableList.getFirst());
    }

    @Test
    public void doWhenRemoveTest() {

        trackableList.add(car2);
        trackableList.remove(car2);

        assertEquals(500, car2.getBalance());
    }

    @Test
    public void addMethodIsNull(){

        TrackableList<Car> nullTrackableList = new TrackableList.TrackableListBuilder<Car>()
                .addIf(null)
                .build();

        nullTrackableList.add(car1);

        assertTrue(nullTrackableList.contains(car1));
    }

    @Test
    public void removeMethodIsNull() {

        TrackableList<Car> nullTrackableList = new TrackableList.TrackableListBuilder<Car>()
                .removeIf(null)
                .build();

        nullTrackableList.add(car1);
        nullTrackableList.remove(car1);

        assertFalse(nullTrackableList.contains(car1));
    }

    @Test
    public void multiRemoveIfTest() {

        TrackableList<Car> removeIfTrackableList = new TrackableList.TrackableListBuilder<Car>()
                .removeIf(car -> car.getBalance() >= 800)
                .removeIf(car -> car.getSpeed() > 80)
                .build();

        removeIfTrackableList.add(car1);
        removeIfTrackableList.add(car2);
        removeIfTrackableList.add(car3);

        removeIfTrackableList.remove(car1);
        removeIfTrackableList.remove(car2);
        removeIfTrackableList.remove(car3);

        assertEquals(car3, removeIfTrackableList.getFirst());
    }

    @Test
    public void multiDoWhenRemoveTest() {

        TrackableList<Car> doWhenRemoveNullTrackableList = new TrackableList.TrackableListBuilder<Car>()
                .doWhenRemove(car -> car.setBalance(500))
                .doWhenRemove(car -> car.setSpeed(50))
                .build();

        doWhenRemoveNullTrackableList.add(car1);
        doWhenRemoveNullTrackableList.remove(car1);

        assertEquals(500, car1.getBalance());
        assertEquals(50, car1.getSpeed());
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Car {

        private String name;
        private String color;
        private int balance;
        private int speed;

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    ", balance=" + balance +
                    ", speed=" + speed +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Car car = (Car) o;
            return balance == car.balance && speed == car.speed && Objects.equals(name, car.name) && Objects.equals(color, car.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, color, balance, speed);
        }

        // own method
        public static void catchFair(Car car) {
            if (car.getSpeed() > 70) {
                car.setBalance(car.getBalance() - 400);
            }
        }
    }
}