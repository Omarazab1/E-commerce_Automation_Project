package com.saucedemo.ecommerce.testcases;

import com.saucedemo.ecommerce.base.BaseTest;
import com.saucedemo.ecommerce.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    @Test
    public void testProductSortingPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("visual_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortingOption("Price (low to high)");

        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices); // ترتيب تصاعدي تلقائي

        System.out.println("Actual Prices from UI: " + actualPrices);
        System.out.println("Expected Sorted Prices: " + expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices,
                "Assertion Failed: Products are not correctly sorted by price (low to high) on the UI!");
    }
    @Test
    public void testEndToEndPurchaseFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        infoPage.fillInformation("Omar", "Essam", "12345");
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        String actualSuccessMessage = completePage.getSuccessMessageText();
        String expectedSuccessMessage = "Thank you for your order!";

        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage,
                "Purchase Failed: The success header message is missing or incorrect!");
    }
}