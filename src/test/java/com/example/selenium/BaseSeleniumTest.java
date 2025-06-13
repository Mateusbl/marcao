package com.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

/**
 * Base class for Selenium integration tests
 * Provides WebDriver setup and common utilities for UI testing using Microsoft Edge
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("selenium")
public abstract class BaseSeleniumTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    
    @LocalServerPort
    private int port;
    
    protected String baseUrl;

    @BeforeAll
    static void setupClass() {
        // Setup Edge driver automatically
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    void setUp() {
        // Configure Edge options for headless testing
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless"); // Run in headless mode for CI/CD
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        
        // Create WebDriver instance
        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Set base URL for the application
        baseUrl = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Navigate to a specific page
     * @param path the path relative to baseUrl
     */
    protected void navigateToPage(String path) {
        driver.get(baseUrl + path);
    }

    /**
     * Get the current page URL
     * @return current URL
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get the page title
     * @return page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }
}
