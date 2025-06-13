package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page Object for the Customers page
 * Provides methods to interact with customer management functionality
 */
public class CustomersPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Form elements
    @FindBy(id = "customerName")
    private WebElement nameInput;

    @FindBy(id = "customerCpf")
    private WebElement cpfInput;

    @FindBy(id = "customerEmail")
    private WebElement emailInput;

    @FindBy(id = "customerEndereco")
    private WebElement enderecoInput;

    @FindBy(id = "customerTelefone")
    private WebElement telefoneInput;

    @FindBy(css = "#customerForm button[type='submit']")
    private WebElement addButton;

    // Table elements
    @FindBy(id = "customersTable")
    private WebElement customersTable;

    @FindBy(css = "#customersTable tbody")
    private WebElement tableBody;

    @FindBy(css = "#customersTable tbody tr")
    private List<WebElement> customerRows;

    public CustomersPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    /**
     * Add a new customer
     */
    public void addCustomer(String nome, String cpf, String email, String endereco, String telefone) {
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        
        nameInput.clear();
        nameInput.sendKeys(nome);
        
        cpfInput.clear();
        cpfInput.sendKeys(cpf);
        
        emailInput.clear();
        emailInput.sendKeys(email);
        
        enderecoInput.clear();
        enderecoInput.sendKeys(endereco);
        
        telefoneInput.clear();
        telefoneInput.sendKeys(telefone);
        
        addButton.click();
    }

    /**
     * Get the number of customers in the table
     */
    public int getCustomerCount() {
        wait.until(ExpectedConditions.visibilityOf(tableBody));
        return customerRows.size();
    }

    /**
     * Check if a customer with the given name exists in the table
     */
    public boolean customerExists(String customerName) {
        wait.until(ExpectedConditions.visibilityOf(tableBody));
        
        for (WebElement row : customerRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1 && cells.get(1).getText().equals(customerName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a customer by name
     */
    public void deleteCustomer(String customerName) {
        wait.until(ExpectedConditions.visibilityOf(tableBody));
        
        for (WebElement row : customerRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1 && cells.get(1).getText().equals(customerName)) {
                // Find and click the delete button in this row
                WebElement deleteButton = row.findElement(By.cssSelector("button.delete-btn"));
                deleteButton.click();
                break;
            }
        }
    }    /**
     * Get customer data from a specific row
     */
    public CustomerData getCustomerData(int rowIndex) {
        wait.until(ExpectedConditions.visibilityOf(tableBody));
        
        if (rowIndex >= customerRows.size()) {
            return null;
        }
        
        WebElement row = customerRows.get(rowIndex);
        List<WebElement> cells = row.findElements(By.tagName("td"));
        
        // Based on the actual JavaScript implementation:
        // columns are: ID, Nome, Email, Delete Button
        if (cells.size() >= 3) {
            return new CustomerData(
                cells.get(0).getText(), // ID
                cells.get(1).getText(), // Nome
                cells.get(2).getText(), // Email (not CPF!)
                ""                      // Telefone not displayed
            );
        }
        
        return null;
    }

    /**
     * Wait for the page to load completely
     */
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(customersTable));
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    /**
     * Clear all form fields
     */
    public void clearForm() {
        nameInput.clear();
        cpfInput.clear();
        emailInput.clear();
        enderecoInput.clear();
        telefoneInput.clear();
    }    /**
     * Data class to hold customer information
     */
    public static class CustomerData {
        public final String id;
        public final String nome;
        public final String email;  // Changed from cpf to email
        public final String telefone;

        public CustomerData(String id, String nome, String email, String telefone) {
            this.id = id;
            this.nome = nome;
            this.email = email;  // Changed from cpf to email
            this.telefone = telefone;
        }
    }
}
