package com.saucedemo.ecommerce.testcases;

import com.saucedemo.ecommerce.base.BaseTest;
import com.saucedemo.ecommerce.pages.LoginPage;
import com.saucedemo.ecommerce.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testSuccessfulLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("valid.username") , ConfigReader.getProperty("valid.password"));
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login Failed Please Try again");

    }
    @Test
    public void testLockedOutUserLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(ConfigReader.getProperty("locked.username") , ConfigReader.getProperty("valid.password"));

        String actualError = loginPage.getErrorMessageText();
        String expectedError = "Sorry, this user has been locked out.";

        Assert.assertTrue(actualError.contains(expectedError),
                "Assertion Failed: Locked out user error message is missing or incorrect!");
    }

    @Test
    public void testInvalidPasswordLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(ConfigReader.getProperty("invalid.username") , ConfigReader.getProperty("invalid.password"));

        String actualError = loginPage.getErrorMessageText();
        String expectedError = " Username and password do not match any user in this service";

        Assert.assertTrue(actualError.contains(expectedError),
                "Assertion Failed: Invalid credentials error message is missing or incorrect!");
    }
}
