package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkoutPage {
    WebDriver driver;

    // Locators
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public checkoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Action: Fill form and continue
    public void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        driver.findElement(continueButton).click();
    }
}

