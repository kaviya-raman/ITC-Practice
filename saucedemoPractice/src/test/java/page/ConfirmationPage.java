package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    WebDriver driver;

    // Locator for the Back Home button
    private By backHomeButton = By.id("back-to-products");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Action: Click Back Home to return to inventory
    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
}

