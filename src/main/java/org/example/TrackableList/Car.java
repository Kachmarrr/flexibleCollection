package org.example.TrackableList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Car {

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

    public static void catchFair(Car car) {
        if (car.getSpeed() > 70) {
            car.setBalance(car.getBalance() - 400);
        }
    }
}