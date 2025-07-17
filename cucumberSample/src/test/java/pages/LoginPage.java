package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {
    WebDriver driver;
    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");
    By productsTitle = By.className("title");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
    public boolean isOnProductsPage() {
        return driver.findElement(productsTitle).getText().equals("Products");
    }
}
