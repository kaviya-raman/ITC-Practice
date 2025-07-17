package test;


import base.baseTest;
import page.LoginPage;
import page.InventoryPage;
import page.CartPage;
import page.checkoutPage;
import page.OverviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OverviewPageTest extends baseTest {

    // ✅ Positive Test: Click Finish and verify confirmation
    @Test(priority = 1)
    public void testFinishButtonFunctionality() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();
        new checkoutPage(driver).fillCheckoutForm("John", "Doe", "560001");

        OverviewPage overviewPage = new OverviewPage(driver);
        overviewPage.clickFinish();

        WebElement confirmationHeader = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(confirmationHeader.getText(), "Thank you for your order!", "Order confirmation message mismatch.");
    }

    // ❌ Negative Test: Access overview page without filling form
    @Test(priority = 2)
    public void testOverviewAccessWithoutFormSubmission() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        driver.get("https://www.saucedemo.com/checkout-step-two.html");

        WebElement errorContainer = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errorContainer.isDisplayed(), "Overview page should not be accessible without form submission.");
    }

    // 🎨 UI Test: Validate Finish button styles
    @Test(priority = 3)
    public void testFinishButtonStyles() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();
        new checkoutPage(driver).fillCheckoutForm("John", "Doe", "560001");

        WebElement finishBtn = driver.findElement(By.id("finish"));

        Assert.assertEquals(finishBtn.getText(), "Finish", "Unexpected button label.");

        String bgColor = finishBtn.getCssValue("background-color");
        String fontSize = finishBtn.getCssValue("font-size");
        String visibility = finishBtn.getCssValue("visibility");

        Assert.assertEquals(bgColor, "rgba(226, 35, 26, 1)", "Unexpected background color.");
        Assert.assertEquals(fontSize, "15px", "Unexpected font size.");
        Assert.assertEquals(visibility, "visible", "Finish button should be visible.");
    }
}

