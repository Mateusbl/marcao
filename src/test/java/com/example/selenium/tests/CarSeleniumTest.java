package com.example.selenium.tests;

import com.example.selenium.BaseSeleniumTest;
import com.example.selenium.pages.CarsPage;
import org.junit.jupiter.api.*;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Selenium tests for Car management functionality
 * Tests the complete UI workflow for managing cars
 */
@DisplayName("Car Management UI Tests")
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb-selenium-cars"
})
class CarSeleniumTest extends BaseSeleniumTest {

    private CarsPage carsPage;

    @BeforeEach
    void setUpPage() {
        navigateToPage("/cars.html");
        carsPage = new CarsPage(driver, wait);
        carsPage.waitForPageLoad();
    }

    @Test
    @DisplayName("Should load cars page successfully")
    void shouldLoadCarsPage() {
        // Verify page title
        assertTrue(getPageTitle().contains("Gerenciar Carros"));
        
        // Verify URL
        assertTrue(getCurrentUrl().contains("/cars.html"));
        
        // Verify page loaded completely
        assertDoesNotThrow(() -> carsPage.waitForPageLoad());
    }

    @Test
    @DisplayName("Should add a new car successfully")
    void shouldAddNewCar() {
        // Given - car data
        String placa = "ABC-1234";
        String marca = "Toyota";
        String modelo = "Corolla";
        String ano = "2023";
        String cor = "Branco";
        String preco = "85000.00";
        
        // When - adding the car
        int initialCount = carsPage.getCarCount();
        carsPage.addCar(placa, marca, modelo, ano, cor, preco);
        
        // Wait for the car to be added (table to update)
        try {
            Thread.sleep(2000); // Wait for AJAX call to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify car was added
        int finalCount = carsPage.getCarCount();
        assertEquals(initialCount + 1, finalCount, "Car count should increase by 1");
        
        // Verify car appears in the table
        assertTrue(carsPage.carExists(marca, modelo), "Car should exist in the table");
    }

    @Test
    @DisplayName("Should add multiple cars")
    void shouldAddMultipleCars() {
        // Given - multiple car data
        String[][] cars = {
            {"XYZ-5678", "Honda", "Civic", "2022", "Preto", "78000.00"},
            {"DEF-9012", "Volkswagen", "Golf", "2023", "Azul", "92000.00"},
            {"GHI-3456", "Ford", "Focus", "2021", "Vermelho", "75000.00"}
        };
        
        int initialCount = carsPage.getCarCount();
        
        // When - adding multiple cars
        for (String[] car : cars) {
            carsPage.addCar(car[0], car[1], car[2], car[3], car[4], car[5]);
            
            // Wait for each car to be added
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Then - verify all cars were added
        int finalCount = carsPage.getCarCount();
        assertEquals(initialCount + cars.length, finalCount, 
                    "Car count should increase by " + cars.length);
        
        // Verify each car exists
        for (String[] car : cars) {
            assertTrue(carsPage.carExists(car[1], car[2]), 
                      "Car " + car[1] + " " + car[2] + " should exist in the table");
        }
    }

    @Test
    @DisplayName("Should handle form validation for required fields")
    void shouldValidateRequiredFields() {
        // Given - empty form
        carsPage.clearForm();
        
        // When - trying to submit without required fields
        int initialCount = carsPage.getCarCount();
        
        // Try to submit with empty fields (should not work due to HTML5 required attribute)
        carsPage.addCar("", "", "", "", "", "");
        
        // Wait a moment
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify no car was added
        int finalCount = carsPage.getCarCount();
        assertEquals(initialCount, finalCount, "No car should be added with empty fields");
    }

    @Test
    @DisplayName("Should display car data correctly in table")
    void shouldDisplayCarDataCorrectly() {
        // Given - car data
        String placa = "JKL-7890";
        String marca = "Nissan";
        String modelo = "Sentra";
        String ano = "2023";
        String cor = "Prata";
        String preco = "82000.00";
        
        // When - adding the car
        carsPage.addCar(placa, marca, modelo, ano, cor, preco);
        
        // Wait for car to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify car data is displayed correctly
        assertTrue(carsPage.carExists(marca, modelo), "Car should exist in table");
        
        // Get the first car data (assuming it's the one we just added)
        if (carsPage.getCarCount() > 0) {
            CarsPage.CarData carData = carsPage.getCarData(0);
            assertNotNull(carData, "Car data should not be null");
            assertEquals(marca, carData.marca, "Car make should match");
            assertEquals(modelo, carData.modelo, "Car model should match");
            assertEquals(ano, carData.ano, "Car year should match");
        }
    }

    @Test
    @DisplayName("Should handle different car years")
    void shouldHandleDifferentCarYears() {
        // Given - cars with different years
        String[][] cars = {
            {"OLD-1234", "Chevrolet", "Celta", "2010", "Branco", "25000.00"},
            {"NEW-5678", "BMW", "X5", "2024", "Preto", "350000.00"},
            {"MID-9012", "Hyundai", "HB20", "2020", "Azul", "60000.00"}
        };
        
        int initialCount = carsPage.getCarCount();
        
        // When - adding cars with different years
        for (String[] car : cars) {
            carsPage.addCar(car[0], car[1], car[2], car[3], car[4], car[5]);
            
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Then - verify all cars were added regardless of year
        int finalCount = carsPage.getCarCount();
        assertEquals(initialCount + cars.length, finalCount, "All cars should be added");
        
        // Verify each car exists
        for (String[] car : cars) {
            assertTrue(carsPage.carExists(car[1], car[2]), 
                      "Car " + car[1] + " " + car[2] + " (" + car[3] + ") should exist");
        }
    }

    @Test
    @DisplayName("Should handle decimal prices correctly")
    void shouldHandleDecimalPrices() {
        // Given - car with decimal price
        String placa = "DEC-1234";
        String marca = "Audi";
        String modelo = "A4";
        String ano = "2023";
        String cor = "Cinza";
        String preco = "125000.99";
        
        // When - adding the car
        int initialCount = carsPage.getCarCount();
        carsPage.addCar(placa, marca, modelo, ano, cor, preco);
        
        // Wait for car to be added
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Then - verify car was added successfully
        int finalCount = carsPage.getCarCount();
        assertEquals(initialCount + 1, finalCount, "Car with decimal price should be added");
        assertTrue(carsPage.carExists(marca, modelo), "Car with decimal price should exist");
    }
}
