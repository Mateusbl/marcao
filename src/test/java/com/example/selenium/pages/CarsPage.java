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
 * Page Object for the Cars page
 * Provides methods to interact with car management functionality
 */
public class CarsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Form elements
    @FindBy(id = "placa")
    private WebElement placaInput;

    @FindBy(id = "make")
    private WebElement makeInput;

    @FindBy(id = "model")
    private WebElement modelInput;

    @FindBy(id = "year")
    private WebElement yearInput;

    @FindBy(id = "cor")
    private WebElement corInput;

    @FindBy(id = "price")
    private WebElement priceInput;

    @FindBy(css = "#carForm button[type='submit']")
    private WebElement addButton;

    // Table elements
    @FindBy(id = "carsTable")
    private WebElement carsTable;

    @FindBy(css = "#carsTable tbody")
    private WebElement tableBody;

    @FindBy(css = "#carsTable tbody tr")
    private List<WebElement> carRows;

    public CarsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    /**
     * Add a new car
     */
    public void addCar(String placa, String marca, String modelo, String ano, String cor, String preco) {
        wait.until(ExpectedConditions.visibilityOf(placaInput));
        
        placaInput.clear();
        placaInput.sendKeys(placa);
        
        makeInput.clear();
        makeInput.sendKeys(marca);
        
        modelInput.clear();
        modelInput.sendKeys(modelo);
        
        yearInput.clear();
        yearInput.sendKeys(ano);
        
        corInput.clear();
        corInput.sendKeys(cor);
        
        priceInput.clear();
        priceInput.sendKeys(preco);
        
        addButton.click();
    }    /**
     * Get the number of cars in the table
     */
    public int getCarCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(tableBody));
            return carRows.size();
        } catch (Exception e) {
            // If table body is not visible or doesn't exist, assume no cars
            return 0;
        }
    }

    /**
     * Check if a car with the given make and model exists in the table
     */
    public boolean carExists(String marca, String modelo) {
        wait.until(ExpectedConditions.visibilityOf(tableBody));
        
        for (WebElement row : carRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 2 && 
                cells.get(1).getText().equals(marca) && 
                cells.get(2).getText().equals(modelo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a car by make and model
     */
    public void deleteCar(String marca, String modelo) {
        wait.until(ExpectedConditions.visibilityOf(tableBody));
        
        for (WebElement row : carRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 2 && 
                cells.get(1).getText().equals(marca) && 
                cells.get(2).getText().equals(modelo)) {
                // Find and click the delete button in this row
                WebElement deleteButton = row.findElement(By.cssSelector("button.delete-btn"));
                deleteButton.click();
                break;
            }
        }
    }

    /**
     * Get car data from a specific row
     */
    public CarData getCarData(int rowIndex) {
        wait.until(ExpectedConditions.visibilityOf(tableBody));
        
        if (rowIndex >= carRows.size()) {
            return null;
        }
        
        WebElement row = carRows.get(rowIndex);
        List<WebElement> cells = row.findElements(By.tagName("td"));
        
        if (cells.size() >= 5) {
            return new CarData(
                cells.get(0).getText(), // ID
                cells.get(1).getText(), // Marca
                cells.get(2).getText(), // Modelo
                cells.get(3).getText(), // Ano
                cells.get(4).getText()  // Preço
            );
        }
        
        return null;
    }

    /**
     * Get car data by make and model
     */
    public CarData getCarDataByMakeModel(String marca, String modelo) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#carsTable tbody")));
        
        for (WebElement row : carRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3 && 
                cells.get(1).getText().equals(marca) && 
                cells.get(2).getText().equals(modelo)) {
                
                return new CarData(
                    cells.get(0).getText(), // ID
                    cells.get(1).getText(), // Marca
                    cells.get(2).getText(), // Modelo
                    cells.get(3).getText(), // Ano
                    cells.get(4).getText()  // Preço
                );
            }
        }
        return null;
    }

    /**
     * Wait for the page to load completely
     */
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(carsTable));
        wait.until(ExpectedConditions.visibilityOf(placaInput));
    }

    /**
     * Clear all form fields
     */
    public void clearForm() {
        placaInput.clear();
        makeInput.clear();
        modelInput.clear();
        yearInput.clear();
        corInput.clear();
        priceInput.clear();
    }

    /**
     * Data class to hold car information
     */
    public static class CarData {
        public final String id;
        public final String marca;
        public final String modelo;
        public final String ano;
        public final String preco;

        public CarData(String id, String marca, String modelo, String ano, String preco) {
            this.id = id;
            this.marca = marca;
            this.modelo = modelo;
            this.ano = ano;
            this.preco = preco;
        }
    }
}
