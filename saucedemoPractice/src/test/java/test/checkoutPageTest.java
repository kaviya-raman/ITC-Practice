package test;


import base.baseTest;
import page.LoginPage;
import page.InventoryPage;
import page.CartPage;
import page.checkoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class checkoutPageTest extends baseTest {

    // ✅ Positive Test: Fill form and continue
    @Test(priority = 1)
    public void testValidCheckoutFormSubmission() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();

        checkoutPage checkoutPage = new checkoutPage(driver);
        checkoutPage.fillCheckoutForm("John", "Doe", "560001");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two"), "Checkout did not proceed to overview page.");
    }

    // ❌ Negative Test: Missing first name
    @Test(priority = 2)
    public void testMissingFirstName() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();

        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("560001");
        driver.findElement(By.id("continue")).click();

        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("First Name is required"), "Missing first name did not trigger error.");
    }

    // ❌ Negative Test: Missing last name
    @Test(priority = 3)
    public void testMissingLastName() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("postal-code")).sendKeys("560001");
        driver.findElement(By.id("continue")).click();

        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Last Name is required"), "Missing last name did not trigger error.");
    }

    // ❌ Negative Test: Missing zip code
    @Test(priority = 4)
    public void testMissingZipCode() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("continue")).click();

        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Postal Code is required"), "Missing zip code did not trigger error.");
    }

    // 🎨 UI Test: Validate Continue button styles
    @Test(priority = 5)
    public void testContinueButtonStyles() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();

        WebElement continueBtn = driver.findElement(By.id("continue"));

        Assert.assertEquals(continueBtn.getText(), "Continue", "Unexpected button label.");

        String bgColor = continueBtn.getCssValue("background-color");
        String fontSize = continueBtn.getCssValue("font-size");
        String visibility = continueBtn.getCssValue("visibility");

        Assert.assertEquals(bgColor, "rgba(226, 35, 26, 1)", "Unexpected background color.");
        Assert.assertEquals(fontSize, "15px", "Unexpected font size.");
        Assert.assertEquals(visibility, "visible", "Continue button should be visible.");
    }
}
