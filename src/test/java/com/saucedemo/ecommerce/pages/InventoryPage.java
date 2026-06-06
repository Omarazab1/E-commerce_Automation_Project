package com.saucedemo.ecommerce.pages;

import com.saucedemo.ecommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

    private final By productSortDropdown = By.className("product_sort_container");
    private final By productPrices = By.className("inventory_item_price");
    private final By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public void selectSortingOption(String optionText) {
        WebElement dropdownElement = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(productSortDropdown));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(optionText);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Double> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        //Its purpose: This is the empty basket we've prepared to clean up the prices and convert them into real numbers so we can compare them.
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }
    public void addBackpackToCart() {
        click(addToCartButton);
    }
    public void clickCartIcon() {
        click(cartIcon);
    }
}
