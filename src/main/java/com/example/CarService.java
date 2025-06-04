package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Carro> cars = new ArrayList<>();

    public List<Carro> getAllCars() {
        return cars;
    }

    public Carro addCar(Carro car) {
        cars.add(car);
        return car;
    }

    public void deleteCar(Integer idCarro) {
        cars.removeIf(car -> car.getIdCarro().equals(idCarro));
    }
}
