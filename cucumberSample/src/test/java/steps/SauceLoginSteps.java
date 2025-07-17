package steps;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
public class SauceLoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    @Given("the user is on the SauceDemo login page")
    public void the_user_is_on_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }
    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in(String username, String password) {
        loginPage.login(username, password);
    }
    @Then("the user should be redirected to the products page")
    public void the_user_should_see_products_page() {
        Assert.assertTrue("Login failed or Products page not visible.", loginPage.isOnProductsPage());
        driver.quit();
    }
}
