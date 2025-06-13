package com.example.selenium.tests;

import com.example.selenium.BaseSeleniumTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Selenium tests for navigation and overall application functionality
 * Tests the main pages and navigation flow
 */
@DisplayName("Application Navigation UI Tests")
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb-selenium-nav"
})
class NavigationSeleniumTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Should load index page successfully")
    void shouldLoadIndexPage() {
        // When - navigating to index page
        navigateToPage("/index.html");
        
        // Then - verify page loads correctly
        assertTrue(getCurrentUrl().contains("/index.html"));
        assertFalse(getPageTitle().isEmpty());
    }

    @Test
    @DisplayName("Should load customers page successfully")
    void shouldLoadCustomersPage() {
        // When - navigating to customers page
        navigateToPage("/customers.html");
        
        // Then - verify page loads correctly
        assertTrue(getCurrentUrl().contains("/customers.html"));
        assertTrue(getPageTitle().contains("Gerenciar Clientes"));
        
        // Verify key elements are present
        WebElement customerForm = driver.findElement(By.id("customerForm"));
        WebElement customersTable = driver.findElement(By.id("customersTable"));
        
        assertNotNull(customerForm, "Customer form should be present");
        assertNotNull(customersTable, "Customers table should be present");
    }

    @Test
    @DisplayName("Should load cars page successfully")
    void shouldLoadCarsPage() {
        // When - navigating to cars page
        navigateToPage("/cars.html");
        
        // Then - verify page loads correctly
        assertTrue(getCurrentUrl().contains("/cars.html"));
        assertTrue(getPageTitle().contains("Gerenciar Carros"));
        
        // Verify key elements are present
        WebElement carForm = driver.findElement(By.id("carForm"));
        WebElement carsTable = driver.findElement(By.id("carsTable"));
        
        assertNotNull(carForm, "Car form should be present");
        assertNotNull(carsTable, "Cars table should be present");
    }

    @Test
    @DisplayName("Should load employees page successfully")
    void shouldLoadEmployeesPage() {
        // When - navigating to employees page
        navigateToPage("/employees.html");
        
        // Then - verify page loads correctly
        assertTrue(getCurrentUrl().contains("/employees.html"));
        assertFalse(getPageTitle().isEmpty());
        
        // Verify page content is loaded (basic check)
        assertFalse(driver.getPageSource().isEmpty());
    }

    @Test
    @DisplayName("Should load services page successfully")
    void shouldLoadServicesPage() {
        // When - navigating to services page
        navigateToPage("/services.html");
        
        // Then - verify page loads correctly
        assertTrue(getCurrentUrl().contains("/services.html"));
        assertFalse(getPageTitle().isEmpty());
        
        // Verify page content is loaded (basic check)
        assertFalse(driver.getPageSource().isEmpty());
    }

    @Test
    @DisplayName("Should load sales page successfully")
    void shouldLoadSalesPage() {
        // When - navigating to sales page
        navigateToPage("/sales.html");
        
        // Then - verify page loads correctly
        assertTrue(getCurrentUrl().contains("/sales.html"));
        assertFalse(getPageTitle().isEmpty());
        
        // Verify page content is loaded (basic check)
        assertFalse(driver.getPageSource().isEmpty());
    }

    @Test
    @DisplayName("Should handle direct URL access to all pages")
    void shouldHandleDirectUrlAccess() {
        String[] pages = {
            "/index.html",
            "/customers.html", 
            "/cars.html",
            "/employees.html",
            "/services.html",
            "/sales.html"
        };
        
        for (String page : pages) {
            // When - accessing page directly via URL
            navigateToPage(page);
            
            // Then - verify page loads without errors
            assertTrue(getCurrentUrl().contains(page), 
                      "URL should contain " + page);
            assertFalse(getPageTitle().isEmpty(), 
                       "Page title should not be empty for " + page);
            assertFalse(driver.getPageSource().isEmpty(), 
                       "Page source should not be empty for " + page);
            
            // Verify no JavaScript errors by checking console logs would go here
            // But that requires additional WebDriver configuration
        }
    }

    @Test
    @DisplayName("Should verify CSS and JavaScript files are loaded")
    void shouldVerifyCssAndJsFilesLoaded() {
        // When - navigating to customers page
        navigateToPage("/customers.html");
        
        // Then - verify CSS files are referenced
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("css/style.css"), 
                  "Main CSS file should be referenced");
        assertTrue(pageSource.contains("css/dark-theme.css"), 
                  "Dark theme CSS should be referenced");
        
        // Verify JavaScript files are referenced
        assertTrue(pageSource.contains("js/hud.js"), 
                  "HUD JavaScript should be referenced");
        assertTrue(pageSource.contains("js/customers.js"), 
                  "Customers JavaScript should be referenced");
    }

    @Test
    @DisplayName("Should handle page refresh without errors")
    void shouldHandlePageRefresh() {
        // Given - navigate to customers page
        navigateToPage("/customers.html");
        String originalUrl = getCurrentUrl();
        String originalTitle = getPageTitle();
        
        // When - refreshing the page
        driver.navigate().refresh();
        
        // Then - verify page still works after refresh
        assertEquals(originalUrl, getCurrentUrl(), "URL should remain the same after refresh");
        assertEquals(originalTitle, getPageTitle(), "Title should remain the same after refresh");
        
        // Verify key elements are still present
        WebElement customerForm = driver.findElement(By.id("customerForm"));
        assertNotNull(customerForm, "Customer form should still be present after refresh");
    }

    @Test
    @DisplayName("Should handle browser back navigation")
    void shouldHandleBrowserBackNavigation() {
        // Given - navigate to index page first
        navigateToPage("/index.html");
        String indexUrl = getCurrentUrl();
        
        // When - navigate to customers page
        navigateToPage("/customers.html");
        String customersUrl = getCurrentUrl();
        
        // And - go back using browser navigation
        driver.navigate().back();
        
        // Then - should be back to index page
        assertEquals(indexUrl, getCurrentUrl(), "Should be back to index page");
        
        // And - forward navigation should work
        driver.navigate().forward();
        assertEquals(customersUrl, getCurrentUrl(), "Should be back to customers page");
    }
}
