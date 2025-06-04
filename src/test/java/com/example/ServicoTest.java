package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Servico Entity Tests")
public class ServicoTest {

    private Servico servico;

    @BeforeEach
    void setUp() {
        servico = new Servico("Troca de Óleo", "Troca completa do óleo do motor", 150.0);
    }

    @Test
    @DisplayName("Should create Servico with valid data")
    void testServicoCreation() {
        assertNotNull(servico);
        assertEquals("Troca de Óleo", servico.getNomeServico());
        assertEquals("Troca completa do óleo do motor", servico.getDescricao());
        assertEquals(150.0, servico.getCustoBase());
    }

    @Test
    @DisplayName("Should set all properties correctly")
    void testSetProperties() {
        servico.setNomeServico("Alinhamento");
        servico.setDescricao("Alinhamento e balanceamento das rodas");
        servico.setCustoBase(200.0);

        assertEquals("Alinhamento", servico.getNomeServico());
        assertEquals("Alinhamento e balanceamento das rodas", servico.getDescricao());
        assertEquals(200.0, servico.getCustoBase());
    }

    @Test
    @DisplayName("Should calculate final cost correctly")
    void testCalcularCustoFinal() {
        double custoFinal = servico.calcularCustoFinal();
        assertEquals(150.0, custoFinal); // Base implementation returns base cost
    }

    @Test
    @DisplayName("Should handle cost validation")
    void testCustoValidation() {
        servico.setCustoBase(0.0);
        assertEquals(0.0, servico.getCustoBase());
        
        servico.setCustoBase(-50.0);
        assertEquals(-50.0, servico.getCustoBase()); // Business logic could prevent negative costs
    }

    @Test
    @DisplayName("Should handle null values in constructor")
    void testServicoWithNullValues() {
        Servico servicoNull = new Servico();
        assertNotNull(servicoNull);
        assertNull(servicoNull.getNomeServico());
        assertNull(servicoNull.getDescricao());
        assertEquals(0.0, servicoNull.getCustoBase());
    }

    @Test
    @DisplayName("Should handle empty strings")
    void testEmptyStrings() {
        servico.setNomeServico("");
        servico.setDescricao("");
        
        assertEquals("", servico.getNomeServico());
        assertEquals("", servico.getDescricao());
    }

    @Test
    @DisplayName("Should handle long descriptions")
    void testLongDescription() {
        String longDescription = "A".repeat(1000);
        servico.setDescricao(longDescription);
        assertEquals(longDescription, servico.getDescricao());
    }

    @Test
    @DisplayName("Should set ID correctly")
    void testSetId() {
        Long testId = 123L;
        servico.setIdServico(testId);
        assertEquals(testId, servico.getIdServico());
    }
}
