package com.saucedemo.ecommerce.testcases;

import com.saucedemo.ecommerce.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InitialTest extends BaseTest {

    @Test
    public void testFrameworkSetup() {

        String pageTitle = driver.getTitle();
        System.out.println("Page Title Is  " + pageTitle);
        Assert.assertEquals(pageTitle, "Swag Labs", "Error occurs Please try again");
    }
}

