package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
@DisplayName("Customer Service Tests")
public class CustomerServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CustomerService customerService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("12345678901", "João Silva", "Rua A, 123", 
                            "(11) 99999-9999", "joao@email.com", new Date());
        cliente.setId(1L);
    }

    @Test
    @DisplayName("Should return all customers")
    void testGetAllCustomers() {
        // Arrange
        List<Cliente> expectedClientes = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(expectedClientes);

        // Act
        List<Cliente> result = customerService.getAllCustomers();

        // Assert
        assertEquals(expectedClientes, result);
        verify(clienteRepository).findAll();
    }

    @Test
    @DisplayName("Should add customer with automatic date registration")
    void testAddCustomer() {
        // Arrange
        Cliente newCliente = new Cliente();
        newCliente.setNome("Maria Santos");
        newCliente.setCpf("987.654.321-00");
        newCliente.setEndereco("Rua B, 456");
        newCliente.setTelefone("(11) 88888-8888");
        newCliente.setEmail("maria@email.com");
        
        Cliente savedCliente = new Cliente();
        savedCliente.setId(1L);
        savedCliente.setNome("Maria Santos");
        savedCliente.setCpf("987.654.321-00");
        savedCliente.setEndereco("Rua B, 456");
        savedCliente.setTelefone("(11) 88888-8888");
        savedCliente.setEmail("maria@email.com");
        savedCliente.setDataRegistro(new Date());
        
        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);
        
        // Act
        Cliente result = customerService.addCustomer(newCliente);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Maria Santos", result.getNome());
        assertEquals("987.654.321-00", result.getCpf());
        assertNotNull(result.getDataRegistro());
        verify(clienteRepository).save(newCliente);
        assertNotNull(newCliente.getDataRegistro()); // Should have been set automatically
    }
    
    @Test
    @DisplayName("Should preserve existing registration date when adding customer")
    void testAddCustomer_WithExistingDate() {
        // Arrange
        Date existingDate = new Date(System.currentTimeMillis() - 86400000); // 1 day ago
        Cliente newCliente = new Cliente();
        newCliente.setNome("João Silva");
        newCliente.setCpf("123.456.789-00");
        newCliente.setDataRegistro(existingDate);
        
        Cliente savedCliente = new Cliente();
        savedCliente.setId(1L);
        savedCliente.setNome("João Silva");
        savedCliente.setCpf("123.456.789-00");
        savedCliente.setDataRegistro(existingDate);
        
        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);
        
        // Act
        Cliente result = customerService.addCustomer(newCliente);
        
        // Assert
        assertNotNull(result);
        assertEquals(existingDate, result.getDataRegistro());
        verify(clienteRepository).save(newCliente);
    }

    @Test
    @DisplayName("Should delete customer by ID")
    void testDeleteCustomer() {
        // Arrange
        Long customerId = 1L;
        doNothing().when(clienteRepository).deleteById(customerId);

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(clienteRepository).deleteById(customerId);
    }
}
