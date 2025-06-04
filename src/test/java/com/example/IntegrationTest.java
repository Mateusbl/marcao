package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Integration Tests - Full Application Flow")
public class IntegrationTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    @Order(1)
    @DisplayName("Should create a new customer via API")
    void testCreateCustomer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String customerJson = """
            {
                "nome": "João da Silva",
                "cpf": "12345678901",
                "endereco": "Rua das Flores, 123",
                "telefone": "(11) 99999-9999",
                "email": "joao@email.com"
            }
            """;

        HttpEntity<String> entity = new HttpEntity<>(customerJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            createURLWithPort("/api/customers"), entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(2)
    @DisplayName("Should retrieve all customers")
    void testGetAllCustomers() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            createURLWithPort("/api/customers"), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("João da Silva"));
    }

    @Test
    @Order(3)
    @DisplayName("Should create a new car via API")
    void testCreateCar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String carJson = """
            {
                "placa": "ABC-1234",
                "marca": "Toyota",
                "modelo": "Corolla",
                "ano": 2023,
                "cor": "Branco",
                "precoVenda": 85000.0,
                "status": "disponivel"
            }
            """;

        HttpEntity<String> entity = new HttpEntity<>(carJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            createURLWithPort("/api/cars"), entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    @DisplayName("Should retrieve all cars")
    void testGetAllCars() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            createURLWithPort("/api/cars"), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("Toyota"));
        assertTrue(response.getBody().contains("ABC-1234"));
    }

    @Test
    @Order(5)
    @DisplayName("Should create a new employee via API")
    void testCreateEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String employeeJson = """
            {
                "nome": "Maria Santos",
                "cpf": "98765432109",
                "endereco": "Av. Principal, 456",
                "telefone": "(11) 88888-8888",
                "email": "maria@empresa.com",
                "cargo": "Vendedora",
                "salario": 3500.0,
                "dataContratacao": "2024-01-15T00:00:00.000Z"
            }
            """;

        HttpEntity<String> entity = new HttpEntity<>(employeeJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            createURLWithPort("/api/employees"), entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(6)
    @DisplayName("Should create a new service via API")
    void testCreateService() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String serviceJson = """
            {
                "nomeServico": "Troca de Óleo",
                "descricao": "Troca completa do óleo do motor",
                "custoBase": 150.0
            }
            """;

        HttpEntity<String> entity = new HttpEntity<>(serviceJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            createURLWithPort("/api/services"), entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(7)
    @DisplayName("Should retrieve all services")
    void testGetAllServices() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            createURLWithPort("/api/services"), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("Troca de Óleo"));
    }

    @Test
    @Order(8)
    @DisplayName("Should serve static content")
    void testStaticContent() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            createURLWithPort("/"), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("html"));
    }

    @Test
    @Order(9)
    @DisplayName("Should handle invalid endpoints")
    void testInvalidEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            createURLWithPort("/api/invalid"), String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
