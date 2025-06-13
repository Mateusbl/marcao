package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Car> cars = new ArrayList<>();

    public List<Car> getAllCars() {
        return cars;
    }

    public Car addCar(Car car) {
        cars.add(car);
        return car;
    }

    public void deleteCar(String id) {
        cars.removeIf(car -> car.getId().equals(id));
    }
}
// Esta classe já está preparada para uso de mock do CarroRepository via injeção de dependência.
