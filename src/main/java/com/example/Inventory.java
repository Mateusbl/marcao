package com.example;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Car> cars;

    public Inventory() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(String carId) {
        cars.removeIf(car -> car.getId().equals(carId));
    }

    public List<Car> getCars() {
        return cars;
    }
}
