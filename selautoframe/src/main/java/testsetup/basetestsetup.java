package testsetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager; 

public class basetestsetup {
    protected WebDriver driver;
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
