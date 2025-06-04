package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@DisplayName("Funcionario Entity Tests")
public class FuncionarioTest {

    private Funcionario funcionario;
    private Date dataContratacao;

    @BeforeEach
    void setUp() {
        dataContratacao = new Date();
        funcionario = new Funcionario("98765432109", "Maria Santos", "Rua C, 789",
                                    "(11) 77777-7777", "maria@empresa.com", 
                                    "Vendedora", 3500.0, dataContratacao);
    }

    @Test
    @DisplayName("Should create Funcionario with valid data")
    void testFuncionarioCreation() {
        assertNotNull(funcionario);
        assertEquals("98765432109", funcionario.getCpf());
        assertEquals("Maria Santos", funcionario.getNome());
        assertEquals("Rua C, 789", funcionario.getEndereco());
        assertEquals("(11) 77777-7777", funcionario.getTelefone());
        assertEquals("maria@empresa.com", funcionario.getEmail());
        assertEquals("Vendedora", funcionario.getCargo());
        assertEquals(3500.0, funcionario.getSalario());
        assertEquals(dataContratacao, funcionario.getDataContratacao());
    }

    @Test
    @DisplayName("Should set cargo correctly")
    void testSetCargo() {
        funcionario.setCargo("Gerente");
        assertEquals("Gerente", funcionario.getCargo());
    }

    @Test
    @DisplayName("Should set salario correctly")
    void testSetSalario() {
        funcionario.setSalario(4000.0);
        assertEquals(4000.0, funcionario.getSalario());
    }

    @Test
    @DisplayName("Should set data contratacao correctly")
    void testSetDataContratacao() {
        Date novaData = new Date();
        funcionario.setDataContratacao(novaData);
        assertEquals(novaData, funcionario.getDataContratacao());
    }

    @Test
    @DisplayName("Should return correct resumo")
    void testExibirResumo() {
        String resumo = funcionario.exibirResumo();
        assertTrue(resumo.contains("Maria Santos"));
        assertTrue(resumo.contains("98765432109"));
    }

    @Test
    @DisplayName("Should handle salary validation")
    void testSalarioValidation() {
        funcionario.setSalario(0.0);
        assertEquals(0.0, funcionario.getSalario());
        
        funcionario.setSalario(-100.0);
        assertEquals(-100.0, funcionario.getSalario()); // Business logic could prevent negative salaries
    }

    @Test
    @DisplayName("Should handle null values in constructor")
    void testFuncionarioWithNullValues() {
        Funcionario funcionarioNull = new Funcionario();
        assertNotNull(funcionarioNull);
        assertNull(funcionarioNull.getCpf());
        assertNull(funcionarioNull.getCargo());
        assertEquals(0.0, funcionarioNull.getSalario());
    }
}
