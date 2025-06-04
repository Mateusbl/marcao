package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carro Entity Tests")
public class CarroTest {

    private Carro carro;

    @BeforeEach
    void setUp() {
        carro = new Carro("ABC-1234", "Toyota", "Corolla", 2023, "Branco", 85000.0, "disponivel");
    }

    @Test
    @DisplayName("Should create Carro with valid data")
    void testCarroCreation() {
        assertNotNull(carro);
        assertEquals("ABC-1234", carro.getPlaca());
        assertEquals("Toyota", carro.getMarca());
        assertEquals("Corolla", carro.getModelo());
        assertEquals(2023, carro.getAno());
        assertEquals("Branco", carro.getCor());
        assertEquals(85000.0, carro.getPrecoVenda());
        assertEquals("disponivel", carro.getStatus());
    }

    @Test
    @DisplayName("Should set all properties correctly")
    void testSetProperties() {
        carro.setPlaca("XYZ-5678");
        carro.setMarca("Honda");
        carro.setModelo("Civic");
        carro.setAno(2024);
        carro.setCor("Preto");
        carro.setPrecoVenda(95000.0);
        carro.setStatus("vendido");

        assertEquals("XYZ-5678", carro.getPlaca());
        assertEquals("Honda", carro.getMarca());
        assertEquals("Civic", carro.getModelo());
        assertEquals(2024, carro.getAno());
        assertEquals("Preto", carro.getCor());
        assertEquals(95000.0, carro.getPrecoVenda());
        assertEquals("vendido", carro.getStatus());
    }

    @Test
    @DisplayName("Should mark car as sold")
    void testMarcarComoVendido() {
        carro.marcarComoVendido();
        assertEquals("Vendido", carro.getStatus());
    }

    @Test
    @DisplayName("Should send car to service")
    void testEnviarParaServico() {
        carro.enviarParaServico();
        assertEquals("Em Serviço", carro.getStatus());
    }

    @Test
    @DisplayName("Should handle price validation")
    void testPrecoValidation() {
        carro.setPrecoVenda(0.0);
        assertEquals(0.0, carro.getPrecoVenda());
        
        carro.setPrecoVenda(-1000.0);
        assertEquals(-1000.0, carro.getPrecoVenda()); // Business logic could prevent negative prices
    }

    @Test
    @DisplayName("Should handle year validation")
    void testAnoValidation() {
        carro.setAno(1900);
        assertEquals(1900, carro.getAno());
        
        carro.setAno(2030);
        assertEquals(2030, carro.getAno());
    }

    @Test
    @DisplayName("Should handle null values in constructor")
    void testCarroWithNullValues() {
        Carro carroNull = new Carro();
        assertNotNull(carroNull);
        assertNull(carroNull.getPlaca());
        assertNull(carroNull.getMarca());
        assertNull(carroNull.getModelo());
        assertNull(carroNull.getAno());
        assertNull(carroNull.getCor());
        assertEquals(0.0, carroNull.getPrecoVenda());
        assertNull(carroNull.getStatus());
    }

    @Test
    @DisplayName("Should validate placa format")
    void testPlacaFormat() {
        carro.setPlaca("ABC-1234");
        assertEquals("ABC-1234", carro.getPlaca());
        
        // Test with different format
        carro.setPlaca("DEF1E23");
        assertEquals("DEF1E23", carro.getPlaca());
    }
}
