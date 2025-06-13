package com.example.selenium.tests;

import com.example.selenium.BaseSeleniumTest;
import com.example.selenium.pages.CustomersPage;
import org.junit.jupiter.api.*;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Selenium tests for Customer management functionality
 * Tests the complete UI workflow for managing customers
 */
@DisplayName("Customer Management UI Tests")
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb-selenium"
})
class CustomerSeleniumTest extends BaseSeleniumTest {

    private CustomersPage customersPage;

    @BeforeEach
    void setUpPage() {
        navigateToPage("/customers.html");
        customersPage = new CustomersPage(driver, wait);
        customersPage.waitForPageLoad();
    }

    @Test
    @DisplayName("Should load customers page successfully")
    void shouldLoadCustomersPage() {
        // Verify page title
        assertTrue(getPageTitle().contains("Gerenciar Clientes"));
        
        // Verify URL
        assertTrue(getCurrentUrl().contains("/customers.html"));
        
        // Verify page loaded completely
        assertDoesNotThrow(() -> customersPage.waitForPageLoad());
    }

    @Test
    @DisplayName("Should add a new customer successfully")
    void shouldAddNewCustomer() {
        // Given - customer data
        String nome = "João Silva";
        String cpf = "123.456.789-00";
        String email = "joao.silva@email.com";
        String endereco = "Rua das Flores, 123";
        String telefone = "(11) 99999-9999";
        
        // When - adding the customer
        int initialCount = customersPage.getCustomerCount();
        customersPage.addCustomer(nome, cpf, email, endereco, telefone);
        
        // Wait for the customer to be added (table to update)
        try {
            Thread.sleep(2000); // Wait for AJAX call to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify customer was added
        int finalCount = customersPage.getCustomerCount();
        assertEquals(initialCount + 1, finalCount, "Customer count should increase by 1");
        
        // Verify customer appears in the table
        assertTrue(customersPage.customerExists(nome), "Customer should exist in the table");
    }

    @Test
    @DisplayName("Should add multiple customers")
    void shouldAddMultipleCustomers() {
        // Given - multiple customer data
        String[][] customers = {
            {"Maria Santos", "987.654.321-00", "maria@email.com", "Av. Paulista, 1000", "(11) 88888-8888"},
            {"Pedro Costa", "111.222.333-44", "pedro@email.com", "Rua Augusta, 500", "(11) 77777-7777"},
            {"Ana Oliveira", "555.666.777-88", "ana@email.com", "Rua Oscar Freire, 200", "(11) 66666-6666"}
        };
        
        int initialCount = customersPage.getCustomerCount();
        
        // When - adding multiple customers
        for (String[] customer : customers) {
            customersPage.addCustomer(customer[0], customer[1], customer[2], customer[3], customer[4]);
            
            // Wait for each customer to be added
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Then - verify all customers were added
        int finalCount = customersPage.getCustomerCount();
        assertEquals(initialCount + customers.length, finalCount, 
                    "Customer count should increase by " + customers.length);
        
        // Verify each customer exists
        for (String[] customer : customers) {
            assertTrue(customersPage.customerExists(customer[0]), 
                      "Customer " + customer[0] + " should exist in the table");
        }
    }

    @Test
    @DisplayName("Should handle form validation for required fields")
    void shouldValidateRequiredFields() {
        // Given - empty form
        customersPage.clearForm();
        
        // When - trying to submit without required fields
        // Note: HTML5 validation should prevent submission
        int initialCount = customersPage.getCustomerCount();
        
        // Try to submit with empty fields (should not work due to HTML5 required attribute)
        customersPage.addCustomer("", "", "", "", "");
        
        // Wait a moment
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify no customer was added
        int finalCount = customersPage.getCustomerCount();
        assertEquals(initialCount, finalCount, "No customer should be added with empty fields");
    }

    @Test
    @DisplayName("Should display customer data correctly in table")
    void shouldDisplayCustomerDataCorrectly() {
        // Given - customer data
        String nome = "Carlos Mendes";
        String cpf = "444.555.666-77";
        String email = "carlos@email.com";
        String endereco = "Rua Consolação, 789";
        String telefone = "(11) 55555-5555";
        
        // When - adding the customer
        customersPage.addCustomer(nome, cpf, email, endereco, telefone);
        
        // Wait for customer to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify customer data is displayed correctly
        assertTrue(customersPage.customerExists(nome), "Customer should exist in table");
        
        // Get the first customer data (assuming it's the one we just added)
        if (customersPage.getCustomerCount() > 0) {
            CustomersPage.CustomerData customerData = customersPage.getCustomerData(0);
            assertNotNull(customerData, "Customer data should not be null");
            assertEquals(nome, customerData.nome, "Customer name should match");
            assertEquals(cpf, customerData.cpf, "Customer CPF should match");
            assertEquals(telefone, customerData.telefone, "Customer phone should match");
        }
    }

    @Test
    @DisplayName("Should handle special characters in customer data")
    void shouldHandleSpecialCharacters() {
        // Given - customer data with special characters
        String nome = "José da Silva Neto";
        String cpf = "123.456.789-01";
        String email = "jose.neto@email.com.br";
        String endereco = "Rua José de Alencar, 1500 - Apto 25B";
        String telefone = "(11) 9 8765-4321";
        
        // When - adding the customer
        int initialCount = customersPage.getCustomerCount();
        customersPage.addCustomer(nome, cpf, email, endereco, telefone);
        
        // Wait for customer to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify customer was added successfully
        int finalCount = customersPage.getCustomerCount();
        assertEquals(initialCount + 1, finalCount, "Customer with special characters should be added");
        assertTrue(customersPage.customerExists(nome), "Customer with special characters should exist");
    }
}
