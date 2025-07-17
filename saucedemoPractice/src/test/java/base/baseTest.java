package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class baseTest {
    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set path to your ChromeDriver if necessary
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ChromeOptions options = new ChromeOptions();
        // Disable Chrome password manager popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("disable-password-manager-reauthentication");


        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

