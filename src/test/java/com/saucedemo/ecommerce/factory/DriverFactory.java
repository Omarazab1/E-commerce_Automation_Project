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
                driver = new ChromeDriver();
                org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
                options.addArguments("--disable-features=PasswordLeakDetection");
                java.util.Map<String, Object> prefs = new java.util.HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                driver = new org.openqa.selenium.chrome.ChromeDriver(options);
                break;
            case ("FIREFOX"):
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case ("EDGE"):
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("The browser is not supported");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;

    }
}

