package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@DisplayName("Application Startup Tests")
public class ApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Should load Spring Boot context successfully")
    void testContextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    @DisplayName("Should have all required controllers")
    void testControllersExist() {
        assertTrue(applicationContext.containsBean("customerController"));
        assertTrue(applicationContext.containsBean("employeeController"));
        assertTrue(applicationContext.containsBean("carController"));
        assertTrue(applicationContext.containsBean("serviceController"));
    }

    @Test
    @DisplayName("Should have all required services")
    void testServicesExist() {
        assertTrue(applicationContext.containsBean("customerService"));
        assertTrue(applicationContext.containsBean("employeeService"));
        assertTrue(applicationContext.containsBean("carService"));
        assertTrue(applicationContext.containsBean("serviceService"));
    }

    @Test
    @DisplayName("Should have all required repositories")
    void testRepositoriesExist() {
        assertTrue(applicationContext.containsBean("clienteRepository"));
        assertTrue(applicationContext.containsBean("funcionarioRepository"));
        assertTrue(applicationContext.containsBean("carroRepository"));
        assertTrue(applicationContext.containsBean("servicoRepository"));
    }

    @Test
    @DisplayName("Should have H2 database configuration")
    void testDatabaseConfiguration() {
        assertTrue(applicationContext.containsBean("dataSource"));
        assertTrue(applicationContext.containsBean("entityManagerFactory"));
    }
}
