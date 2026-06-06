package com.saucedemo.ecommerce.base;
import com.saucedemo.ecommerce.factory.DriverFactory;
import com.saucedemo.ecommerce.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new DriverFactory().initializeDriver();
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
//            if (result.getStatus() == ITestResult.FAILURE) {
//                String testCaseName = result.getMethod().getMethodName();
//                File destFile = new File("target" + File.separator + "screenshots" + File.separator + testCaseName + ".png");
//                takeScreenShot(destFile);
            driver.quit();
            }

        }
    }

//    public void takeScreenShot(File destFile) {
//        // 1. التأكد من إنشاء الفولدرات لو مش موجودة
//        if (destFile.getParentFile() != null && !destFile.getParentFile().exists()) {
//            destFile.getParentFile().mkdirs();
//        }
//
//        // 2. لقط السكرين شوت كمجرد ملف مؤقت
//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//        try {
//            // 3. نعمل الـ copy الأول والملف لسه مقفول ومش معمول عليه Stream
//            FileUtils.copyFile(scrFile, destFile);
//
//            // 4. نفتح الـ Stream من الملف النهائي اللي انشأناه عشان Allure يقرأه ويقفل أوتوماتيك
//            try (InputStream inputStream = new FileInputStream(destFile)) {
//                Allure.addAttachment("screenshot", inputStream);
//            }
//        } catch (IOException e) {
//            System.err.println("Failed to save screenshot: " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }

