package com.saucedemo.ecommerce.testcases;

import com.saucedemo.ecommerce.base.BaseTest;
import com.saucedemo.ecommerce.pages.*;
import com.saucedemo.ecommerce.utils.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    @Test
    public void testProductSortingPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("valid.username") , ConfigReader.getProperty("valid.password"));
        driver.get("https://www.saucedemo.com/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortingOption("Price (low to high)");
        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        System.out.println("Actual Prices from UI: " + actualPrices);
        System.out.println("Expected Sorted Prices: " + expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices,
                "Assertion Failed: Products are not correctly sorted by price (low to high) on the UI!");
    }
    @Test
    public void testEndToEndPurchaseFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("valid.username") , ConfigReader.getProperty("valid.password"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        infoPage.fillInformation(ConfigReader.getProperty("first.name") , ConfigReader.getProperty("last.name"),ConfigReader.getProperty("zip.code"));
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        String actualSuccessMessage = completePage.getSuccessMessageText();
        String expectedSuccessMessage = "Thank you for your order!";

        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage,
                "Purchase Failed: The success header message is missing or incorrect!");
    }
}