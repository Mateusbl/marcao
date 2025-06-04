package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Cliente Repository Tests")
public class ClienteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("12345678901", "João Silva", "Rua A, 123", 
                            "(11) 99999-9999", "joao@email.com", new Date());
    }

    @Test
    @DisplayName("Should save and find cliente")
    void testSaveAndFind() {
        // Save
        Cliente savedCliente = clienteRepository.save(cliente);
        entityManager.flush();

        // Find
        Optional<Cliente> found = clienteRepository.findById(savedCliente.getId());
        
        assertTrue(found.isPresent());
        assertEquals("João Silva", found.get().getNome());
        assertEquals("12345678901", found.get().getCpf());
    }

    @Test
    @DisplayName("Should find all clientes")
    void testFindAll() {
        // Save multiple clientes
        Cliente cliente2 = new Cliente("98765432109", "Maria Santos", "Rua B, 456", 
                                     "(11) 88888-8888", "maria@email.com", new Date());
        
        clienteRepository.save(cliente);
        clienteRepository.save(cliente2);
        entityManager.flush();

        // Find all
        List<Cliente> clientes = clienteRepository.findAll();
        
        assertEquals(2, clientes.size());
    }

    @Test
    @DisplayName("Should delete cliente")
    void testDelete() {
        // Save
        Cliente savedCliente = clienteRepository.save(cliente);
        entityManager.flush();
        
        // Delete
        clienteRepository.deleteById(savedCliente.getId());
        entityManager.flush();
        
        // Verify deletion
        Optional<Cliente> found = clienteRepository.findById(savedCliente.getId());
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Should handle unique CPF constraint")
    void testUniqueCpf() {
        // Save first cliente
        clienteRepository.save(cliente);
        entityManager.flush();

        // Try to save another cliente with same CPF
        Cliente duplicateCliente = new Cliente("12345678901", "José Silva", "Rua C, 789", 
                                             "(11) 77777-7777", "jose@email.com", new Date());
        
        // This should throw an exception due to unique constraint
        assertThrows(Exception.class, () -> {
            clienteRepository.save(duplicateCliente);
            entityManager.flush();
        });
    }
}
