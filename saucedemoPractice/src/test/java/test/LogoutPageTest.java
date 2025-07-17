package test;

import base.baseTest;
import page.LoginPage;
import page.LogoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutPageTest extends baseTest {

    // ✅ Positive Test: Logout after login
    @Test(priority = 1)
    public void testLogoutFunctionality() throws InterruptedException {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo.com"), "Logout failed or incorrect redirection.");
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login button not visible after logout.");
    }

    // ❌ Negative Test: Access logout without login
    @Test(priority = 2)
    public void testLogoutAccessWithoutLogin() {
        driver.get("https://www.saucedemo.com/inventory.html");

        boolean isLoginPage = driver.findElements(By.id("login-button")).size() > 0;
        Assert.assertTrue(isLoginPage, "User should be redirected to login page when accessing inventory without login.");
    }

    // 🎨 UI Test: Validate Logout button styles
    @Test(priority = 3)
    public void testLogoutButtonStyles() throws InterruptedException {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        WebElement menuBtn = driver.findElement(By.id("react-burger-menu-btn"));
        menuBtn.click();
        Thread.sleep(1000); // Wait for menu to appear

        WebElement logoutBtn = driver.findElement(By.id("logout_sidebar_link"));

        Assert.assertEquals(logoutBtn.getText(), "Logout", "Unexpected button label.");

        String bgColor = logoutBtn.getCssValue("background-color");
        String fontSize = logoutBtn.getCssValue("font-size");
        String visibility = logoutBtn.getCssValue("visibility");

        Assert.assertEquals(bgColor, "rgba(226, 35, 26, 1)", "Unexpected background color.");
        Assert.assertEquals(fontSize, "15px", "Unexpected font size.");
        Assert.assertEquals(visibility, "visible", "Logout button should be visible.");
    }
}
