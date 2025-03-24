package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("🟢 Setting up ChromeDriver in headless mode for CI...");

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=chrome");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage"); // <— key fix!
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));

            System.out.println("✅ ChromeDriver ready.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("🛑 ChromeDriver closed.");
        }
    }

    public static void takeScreenshot(String fileName) {
        if (driver != null) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                File targetFile = new File("target/" + fileName);
                FileUtils.copyFile(scrFile, targetFile);
                System.out.println("📸 Screenshot saved at: " + targetFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("❌ Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}
