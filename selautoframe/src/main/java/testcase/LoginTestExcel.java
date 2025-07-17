package testcase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.excelutil;
public class LoginTestExcel {
    @DataProvider
    (name = "excelData")
    public Object[][] excelDataProvider() throws Exception {
        String path = "C:\\Users\\LabsKraft\\eclipse-workspace\\selautoframe\\src\\main\\java\\testdata\\Book1.xlsx";
        return excelutil.getExcelData(path, "Sheet1");
    }
    @Test
    (dataProvider = "excelData")
    public void loginTest(String username, String password) {
        System.out.println("Username: " + username + " | Password: " + password);
        // Here you can plug in Selenium WebDriver to automate login
        // For example: driver.findElement(By.id("user")).sendKeys(username);
    }
}
