package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    WebDriver driver;

    // Locators
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Action: Open menu and click logout
    public void logout() {
        driver.findElement(menuButton).click();

        // Optional: Add a short sleep or explicit wait if needed
        try {
            Thread.sleep(1000); // Wait for menu to slide out
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(logoutLink).click();
    }
}
