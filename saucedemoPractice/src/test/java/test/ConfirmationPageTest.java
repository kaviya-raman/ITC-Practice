package test;


import base.baseTest;
import page.LoginPage;
import page.InventoryPage;
import page.CartPage;
import page.checkoutPage;
import page.OverviewPage;
import page.ConfirmationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfirmationPageTest extends baseTest {

    // ✅ Positive Test: Click Back Home after order confirmation
    @Test(priority = 1)
    public void testBackHomeButtonFunctionality() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();
        new checkoutPage(driver).fillCheckoutForm("John", "Doe", "560001");
        new OverviewPage(driver).clickFinish();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.clickBackHome();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), "Back Home did not redirect to inventory page.");
    }

    // ❌ Negative Test: Access confirmation page without completing order
    @Test(priority = 2)
    public void testConfirmationPageAccessWithoutOrder() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        driver.get("https://www.saucedemo.com/checkout-complete.html");

        boolean isErrorVisible = driver.findElements(By.cssSelector("h3[data-test='error']")).size() > 0;
        Assert.assertTrue(isErrorVisible, "Confirmation page should not be accessible without completing order.");
    }

    // 🎨 UI Test: Validate Back Home button styles
    @Test(priority = 3)
    public void testBackHomeButtonStyles() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new InventoryPage(driver).addTwoProductsToCart();
        new InventoryPage(driver).goToCart();
        new CartPage(driver).clickCheckout();
        new checkoutPage(driver).fillCheckoutForm("John", "Doe", "560001");
        new OverviewPage(driver).clickFinish();

        WebElement backHomeBtn = driver.findElement(By.id("back-to-products"));

        Assert.assertEquals(backHomeBtn.getText(), "Back Home", "Unexpected button label.");

        String bgColor = backHomeBtn.getCssValue("background-color");
        String fontSize = backHomeBtn.getCssValue("font-size");
        String visibility = backHomeBtn.getCssValue("visibility");

        Assert.assertEquals(bgColor, "rgba(226, 35, 26, 1)", "Unexpected background color.");
        Assert.assertEquals(fontSize, "15px", "Unexpected font size.");
        Assert.assertEquals(visibility, "visible", "Back Home button should be visible.");
    }
}
