package com.saucedemo.ecommerce.pages;

import com.saucedemo.ecommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {


    private final By successHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessageText() {
        return getText(successHeader);
    }
}