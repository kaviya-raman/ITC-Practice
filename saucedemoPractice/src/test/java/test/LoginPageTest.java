package test;

import base.baseTest;
import page.LoginPage;
import page.LogoutPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends baseTest {

    // ✅ Positive Test: Valid usernames
    @Test(dataProvider = "validUsers")
    public void testValidUserLoginFlow(String username) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, "secret_sauce");

        if (username.equals("locked_out_user")) {
            String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
            Assert.assertTrue(errorMsg.contains("locked out"), "Expected locked out error for locked_out_user.");
        } else {
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed for valid user: " + username);

            // Logout after login
            LogoutPage logoutPage = new LogoutPage(driver);
            logoutPage.logout();
            Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Logout failed.");
        }
    }

    // ❌ Negative Test: Invalid usernames
    @Test(dataProvider = "invalidUsers")
    public void testInvalidUserLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Username and password do not match"), 
            "Unexpected error for invalid credentials: " + username + " / " + password);
    }

    @DataProvider(name = "validUsers")
    public Object[][] validUsernames() {
        return new Object[][] {
            {"standard_user"},
            {"locked_out_user"},
            {"problem_user"},
            {"performance_glitch_user"}
        };
    }

    @DataProvider(name = "invalidUsers")
    public Object[][] invalidUsernames() {
        return new Object[][] {
            {"standard_", "secret_sauce"},
            {"standard_user", "wrong_pass"},
            {"", "secret_sauce"},
            {"standard_user", ""},
            {"invalid_user", "invalid_pass"}
        };
    }
}
