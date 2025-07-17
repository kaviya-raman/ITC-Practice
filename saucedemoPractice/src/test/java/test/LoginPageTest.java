package test;

import base.baseTest;
import page.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends baseTest {

    // ✅ Positive Test Case: Full purchase flow
    @Test(priority = 1)
    public void testValidLoginAndPurchaseFlow() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed with valid credentials.");

        // Add products
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        // Fill checkout form
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("560001");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();

        // Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Logout failed or incorrect redirection.");
    }

    // ❌ Negative Test Cases: Invalid login attempts
    @Test(priority = 2, dataProvider = "invalidLoginData")
    public void testInvalidLogin(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Thread.sleep(2000);

        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Username and password do not match"), 
            "Unexpected error message for credentials: " + username + " / " + password);
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][] {
            {"standard_", "secret_sauce"},
            {"standard_user", "wrong_pass"},
            {"", "secret_sauce"},
            {"standard_user", ""},
            {"", ""},
            {"admin", "admin123"},
            {"standard_user ", "secret_sauce"},
            {"standard_user", "SECRET_SAUCE"}
        };
    }

    // ❌ Negative Test Case: Checkout with empty fields
    @Test(priority = 3)
    public void testCheckoutWithEmptyFields() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        // Leave fields empty
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("First Name is required"), "Empty checkout form did not trigger expected error.");
    }
}
