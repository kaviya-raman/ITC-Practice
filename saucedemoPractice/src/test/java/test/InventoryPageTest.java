package test;

import base.baseTest;
import page.LoginPage;
import page.InventoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InventoryPageTest extends baseTest {

    @Test(dataProvider = "validUsers")
    public void testInventoryAccessForValidUsers(String username) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, "secret_sauce");

        if (username.equals("locked_out_user")) {
            String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
            Assert.assertTrue(errorMsg.contains("locked out"), "Expected locked out error.");
        } else {
            InventoryPage inventoryPage = new InventoryPage(driver);
            inventoryPage.addTwoProductsToCart();
            inventoryPage.goToCart();

            WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
            Assert.assertEquals(cartIcon.getText(), "2", "Cart count mismatch for user: " + username);
        }
    }

    @DataProvider(name = "validUsers")
    public Object[][] getValidUsers() {
        return new Object[][] {
            {"standard_user"},
            {"locked_out_user"},
            {"problem_user"},
            {"performance_glitch_user"},
            {"error_user"},
            {"visual_user"}
        };
    }
}

