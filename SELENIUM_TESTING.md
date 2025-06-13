# Selenium Testing Guide

## Overview
This project includes comprehensive Selenium UI tests for testing the web interface of the car dealership management system using Microsoft Edge browser.

## Test Structure

### Base Classes
- `BaseSeleniumTest` - Base class for all Selenium tests with WebDriver setup
- Page Objects in `com.example.selenium.pages` package for clean test organization

### Test Categories
1. **Customer Management Tests** (`CustomerSeleniumTest`)
   - Add new customers
   - Form validation
   - Data display verification
   - Special character handling

2. **Car Management Tests** (`CarSeleniumTest`)
   - Add new cars
   - Multiple car handling
   - Price validation
   - Year range testing

3. **Navigation Tests** (`NavigationSeleniumTest`)
   - Page loading verification
   - URL navigation
   - Browser back/forward functionality
   - CSS/JS file loading

## Running Tests

### Prerequisites
- Java 21
- Microsoft Edge browser (standard on Windows 10/11)
- All dependencies installed via Gradle

### Test Commands

```powershell
# Run only unit tests (excludes Selenium)
.\gradlew unitTest

# Run only Selenium UI tests
.\gradlew seleniumTest

# Run all tests (unit + Selenium)
.\gradlew allTests

# Run regular test suite (excludes Selenium by default)
.\gradlew test
```

### Test Configuration

#### Unit Tests
- Use H2 in-memory database: `jdbc:h2:mem:testdb`
- Profile: `test`
- Fast execution, included in regular builds

#### Selenium Tests
- Use separate H2 database: `jdbc:h2:mem:selenium-testdb`
- Profile: `selenium`
- Headless Microsoft Edge browser
- Sequential execution to avoid conflicts
- Higher memory allocation (1-2GB)

## Test Features

### WebDriver Management
- **Automatic Setup**: WebDriverManager handles Edge driver installation
- **Headless Mode**: Tests run without opening browser windows
- **Cross-Platform**: Works on Windows, Linux, and macOS
- **Cleanup**: Automatic WebDriver cleanup after each test

### Page Object Pattern
- **Maintainable**: Changes to UI only require updates to page objects
- **Reusable**: Page objects can be shared across multiple tests
- **Clear API**: Intuitive methods for interacting with page elements

### Test Reliability
- **Wait Strategies**: Explicit waits for element visibility
- **Error Handling**: Comprehensive error messages and debugging info
- **Isolation**: Each test runs with a fresh database state
- **Retry Logic**: Built-in handling for timing issues

## Configuration Files

- `application-selenium.properties` - Selenium test configuration
- `application-test.properties` - Unit test configuration
- `build.gradle` - Test task definitions and dependencies

## Browser Support

Currently configured for Chrome browser in headless mode. Can be extended to support:
- Firefox
- Edge
- Safari (macOS only)

## CI/CD Integration

Selenium tests are designed for CI/CD environments:
- Headless execution
- Separate test task (`seleniumTest`)
- Detailed test reporting
- Memory-optimized configuration

## Troubleshooting

### Common Issues

1. **Chrome driver not found**
   - WebDriverManager should handle this automatically
   - Ensure Chrome browser is installed

2. **Tests timing out**
   - Increase wait timeouts in `BaseSeleniumTest`
   - Check application startup time

3. **Database conflicts**
   - Tests use separate database instances
   - Each test gets a fresh database state

4. **Memory issues**
   - Selenium tests have higher memory allocation
   - Adjust heap size in `build.gradle` if needed

### Debug Mode

To run tests with visible browser (non-headless):

1. Edit `BaseSeleniumTest.java`
2. Comment out: `options.addArguments("--headless");`
3. Run tests to see browser automation

## Examples

### Adding a New Page Test

1. Create page object in `com.example.selenium.pages`
2. Create test class extending `BaseSeleniumTest`
3. Use `@DisplayName` annotations for clear test descriptions
4. Follow existing test patterns for consistency

### Custom Assertions

```java
// Wait for element and verify
wait.until(ExpectedConditions.visibilityOf(element));
assertTrue(element.isDisplayed(), "Element should be visible");

// Verify data persistence
assertTrue(page.dataExists("test data"), "Data should persist after save");
```

## Performance

- **Unit Tests**: ~5-10 seconds
- **Selenium Tests**: ~30-60 seconds (depending on complexity)
- **All Tests**: ~1-2 minutes total

The Selenium tests are designed to be comprehensive while maintaining reasonable execution times for CI/CD pipelines.
