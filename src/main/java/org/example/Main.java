package org.example;

import org.example.TrackableList.Car;
import org.example.TrackableList.TrackableList;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        Car car1 = new Car("Reno", "red", 800, 60);
        Car car2 = new Car("Mercedes", "black", 1700, 120);

        TrackableList<Car> trackableList = new TrackableList<>();

        //Predicate - tests:
//        trackableList.whenAdd(car2, car -> car.getSpeed() >= 60);
//        trackableList.whenAdd(car1, car -> car.getBalance() > 700);
//        trackableList.whenRemoveAll(car -> car.getBalance() > 700);
//
//        trackableList.whenRemove(car -> car.getName().equals("Reno"));
//        trackableList.whenRemove(car -> car.getSpeed() > 110);

        //Consumer:
        trackableList.whenAdd(car1, Car::catchFair);
        trackableList.whenAdd(car2, car -> {
            car.setBalance(car.getBalance() + 1000);
        });

        //Function:


        trackableList.forEach(System.out::println);
    }
}