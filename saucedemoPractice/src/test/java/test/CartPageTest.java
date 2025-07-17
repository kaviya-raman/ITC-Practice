package test;


import base.baseTest;
import page.LoginPage;
import page.InventoryPage;
import page.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends baseTest {

    // ✅ Positive Test: Add products and verify cart page
    @Test(priority = 1)
    public void testCartPageAfterAddingProducts() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addTwoProductsToCart();
        inventoryPage.goToCart();

        WebElement cartHeader = driver.findElement(By.className("title"));
        Assert.assertEquals(cartHeader.getText(), "Your Cart", "Cart page did not load correctly.");
    }

    // ❌ Negative Test: Access cart without login
    @Test(priority = 2)
    public void testCartAccessWithoutLogin() {
        driver.get("https://www.saucedemo.com/cart.html");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed(), "User should be redirected to login page.");
    }

    // 🎨 UI Test: Validate Checkout button styles
    @Test(priority = 3)
    public void testCheckoutButtonStyles() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addTwoProductsToCart();
        inventoryPage.goToCart();

        WebElement checkoutBtn = driver.findElement(By.id("checkout"));

        // Validate button text
        Assert.assertEquals(checkoutBtn.getText(), "Checkout", "Unexpected button label.");

        // Validate CSS styles
        String bgColor = checkoutBtn.getCssValue("background-color");
        String fontSize = checkoutBtn.getCssValue("font-size");
        String visibility = checkoutBtn.getCssValue("visibility");

        Assert.assertEquals(bgColor, "rgba(226, 35, 26, 1)", "Unexpected background color.");
        Assert.assertEquals(fontSize, "15px", "Unexpected font size.");
        Assert.assertEquals(visibility, "visible", "Checkout button should be visible.");
    }
}

