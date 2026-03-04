package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.utils;

public class productPage {
    private WebDriver driver;
    private utils u;

    // Locators
    private By qtyDropdown = By.name("quantity");
    private By addToCartBtn = By.id("add-to-cart-button");

    public productPage(WebDriver driver) {
        this.driver = driver;
        this.u = new utils(driver);
    }

    // Select quantity using JS (Amazon requires change event)
    public void selectQuantity(String qty) {
      //  WebElement dropdown = driver.findElement(qtyDropdown);
        u.jsSetValue(qtyDropdown, qty);
    }

    public void addToCart() {
        u.click(addToCartBtn);
    }
}