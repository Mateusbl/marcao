package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAllCars() {
        return carroRepository.findAll();
    }

    public Carro addCar(Carro car) {
        if (car.getStatus() == null) {
            car.setStatus("disponivel");
        }
        return carroRepository.save(car);
    }

    public void deleteCar(Long id) {
        carroRepository.deleteById(id);
    }
}
