package com.saucedemo.ecommerce.pages;

import com.saucedemo.ecommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    // 2. Action للضغط على زرار Finish
    public void clickFinish() {
        click(finishButton);
    }
}