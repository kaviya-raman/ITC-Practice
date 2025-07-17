package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    // Locators for two products and cart icon
    private By backpackAddBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLightAddBtn = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addTwoProductsToCart() {
        driver.findElement(backpackAddBtn).click();
        driver.findElement(bikeLightAddBtn).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}

