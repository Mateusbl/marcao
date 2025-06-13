package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CarService Unit Tests with Mock")
public class CarServiceTest {

    @Mock
    private CarroRepository carroRepository;

    @InjectMocks
    private CarService carService;

    private Carro carro;

    @BeforeEach
    void setUp() {
        carro = new Carro("ABC-1234", "Toyota", "Corolla", 2023, "Branco", 85000.0, "disponivel");
        carro.setIdCarro(1L);
    }

    @Test
    @DisplayName("Deve retornar todos os carros usando mock")
    void testGetAllCars() {
        List<Carro> carros = Arrays.asList(carro);
        when(carroRepository.findAll()).thenReturn(carros);

        List<Carro> resultado = carService.getAllCars();

        assertEquals(1, resultado.size());
        assertEquals("Toyota", resultado.get(0).getMarca());
        verify(carroRepository).findAll();
    }

    @Test
    @DisplayName("Deve adicionar um carro usando mock")
    void testAddCar() {
        when(carroRepository.save(any(Carro.class))).thenReturn(carro);

        Carro novoCarro = new Carro("DEF-5678", "Honda", "Civic", 2022, "Preto", 90000.0, null);
        Carro resultado = carService.addCar(novoCarro);

        assertNotNull(resultado);
        assertEquals("Toyota", resultado.getMarca());
        verify(carroRepository).save(novoCarro);
    }
}
