package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@DisplayName("Cliente Entity Tests")
public class ClienteTest {

    private Cliente cliente;
    private Date dataRegistro;

    @BeforeEach
    void setUp() {
        dataRegistro = new Date();
        cliente = new Cliente("12345678901", "João Silva", "Rua A, 123", 
                            "(11) 99999-9999", "joao@email.com", dataRegistro);
    }

    @Test
    @DisplayName("Should create Cliente with valid data")
    void testClienteCreation() {
        assertNotNull(cliente);
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("João Silva", cliente.getNome());
        assertEquals("Rua A, 123", cliente.getEndereco());
        assertEquals("(11) 99999-9999", cliente.getTelefone());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals(dataRegistro, cliente.getDataRegistro());
    }

    @Test
    @DisplayName("Should update endereco correctly")
    void testAtualizarEndereco() {
        String novoEndereco = "Rua B, 456";
        cliente.atualizarEndereco(novoEndereco);
        assertEquals(novoEndereco, cliente.getEndereco());
    }

    @Test
    @DisplayName("Should update telefone correctly")
    void testAtualizarTelefone() {
        String novoTelefone = "(11) 88888-8888";
        cliente.atualizarTelefone(novoTelefone);
        assertEquals(novoTelefone, cliente.getTelefone());
    }

    @Test
    @DisplayName("Should return correct resumo")
    void testExibirResumo() {
        String resumo = cliente.exibirResumo();
        assertTrue(resumo.contains("João Silva"));
        assertTrue(resumo.contains("12345678901"));
        assertTrue(resumo.contains("Cliente desde"));
    }

    @Test
    @DisplayName("Should set data registro correctly")
    void testSetDataRegistro() {
        Date novaData = new Date();
        cliente.setDataRegistro(novaData);
        assertEquals(novaData, cliente.getDataRegistro());
    }

    @Test
    @DisplayName("Should handle null values in constructor")
    void testClienteWithNullValues() {
        Cliente clienteNull = new Cliente();
        assertNotNull(clienteNull);
        assertNull(clienteNull.getCpf());
        assertNull(clienteNull.getNome());
    }
}
