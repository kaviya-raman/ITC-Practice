package testcase;

import pom.loginpage;
import testsetup.basetestsetup;
public class LoginTest extends basetestsetup {
    public static void main(String[] args) {
        LoginTest test = new LoginTest();
        test.setUp();
        test.driver.get("https://practicetestautomation.com/practice-test-login/");
        loginpage loginPage = new loginpage(test.driver);
        loginPage.login("student", "Password123");
        // You can add validations here
        String currentUrl = test.driver.getCurrentUrl();
        System.out.println("Login success. Navigated to: " + currentUrl);
        test.tearDown();
    }
}