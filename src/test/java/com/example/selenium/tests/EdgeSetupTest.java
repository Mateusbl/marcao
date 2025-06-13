package com.example.selenium.tests;

import com.example.selenium.BaseSeleniumTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple Selenium test to verify Edge WebDriver setup
 */
@DisplayName("Selenium Edge Setup Test")
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb-edge-setup"
})
class EdgeSetupTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Should successfully initialize Edge WebDriver")
    void shouldInitializeEdgeWebDriver() {
        // When - navigating to the application
        navigateToPage("/");
        
        // Then - verify we can access the application
        assertNotNull(driver, "WebDriver should be initialized");
        assertNotNull(getCurrentUrl(), "Should be able to get current URL");
        assertTrue(getCurrentUrl().contains("localhost"), "Should be connected to localhost");
        
        // Verify basic WebDriver functionality
        String userAgent = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return navigator.userAgent;");
        
        assertTrue(userAgent.contains("Edg"), "Should be using Edge browser");
    }

    @Test
    @DisplayName("Should be able to navigate to static pages")
    void shouldNavigateToStaticPages() {
        // When - navigating to index page
        navigateToPage("/index.html");
        
        // Then - verify navigation works
        assertTrue(getCurrentUrl().contains("/index.html"), "Should navigate to index.html");
        
        // When - navigating to customers page  
        navigateToPage("/customers.html");
        
        // Then - verify navigation works
        assertTrue(getCurrentUrl().contains("/customers.html"), "Should navigate to customers.html");
    }
}
