package com.saucedemo.ecommerce.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    private WebDriver driver;
    public  WebDriver  initializeDriver(){
        String browser = System.getProperty("browser" , "CHROME");
        switch (browser){
            case ("CHROME"):
                WebDriverManager.chromedriver().setup();
                org.openqa.selenium.chrome.ChromeOptions chromeOptions = new org.openqa.selenium.chrome.ChromeOptions();
                chromeOptions.addArguments("--guest");
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-popup-blocking");
                java.util.Map<String, Object> prefs = new java.util.HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);
                if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                }
                driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                break;
            case ("FIREFOX"):
                WebDriverManager.firefoxdriver().setup();
                org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = new org.openqa.selenium.firefox.FirefoxOptions();
                if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                    firefoxOptions.addArguments("-headless");
                }
                driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                break;
            case ("EDGE"):
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("The browser is not supported");
        }
        if (!Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(20));
        return driver;

    }
}

