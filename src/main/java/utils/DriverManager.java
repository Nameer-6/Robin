//package utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import java.io.File;
//
//public class DriverManager {
//
//    private static WebDriver driver;
//
//    public static WebDriver getDriver() {
//        if (driver == null) {
//            System.out.println("Initializing EdgeDriver manually...");
//
//            // Set the path to EdgeDriver
//            String driverPath = "C:\\Users\\zubai\\Downloads\\edgedriver_win64\\msedgedriver.exe";
//
//            // Verify if the driver file exists
//            File driverFile = new File(driverPath);
//            if (!driverFile.exists()) {
//                throw new RuntimeException("EdgeDriver not found at " + driverPath);
//            }
//
//            // Set System Property for EdgeDriver
//            System.setProperty("webdriver.edge.driver", driverPath);
//
//            // EdgeOptions for a stable browser session
//            EdgeOptions options = new EdgeOptions();
//            options.addArguments("--disable-extensions");
//            options.addArguments("--disable-gpu");
//            options.addArguments("--remote-debugging-port=9225");
//            options.addArguments("--inprivate"); // Open in incognito mode
//            options.addArguments("--disable-plugins");
//            options.addArguments("--disable-software-rasterizer");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--disable-blink-features=AutomationControlled");
//            options.addArguments("--start-maximized");
//
//            // Initialize EdgeDriver
//            driver = new EdgeDriver(options);
//            System.out.println("EdgeDriver initialized successfully.");
//        }
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//            System.out.println("EdgeDriver closed.");
//        }
//    }
//}

package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("Initializing EdgeDriver using WebDriverManager...");

            // Automatically download correct EdgeDriver binary
            WebDriverManager.edgedriver().setup();

            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-gpu");
            options.addArguments("--inprivate");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--start-maximized");

            driver = new EdgeDriver(options);
            System.out.println("EdgeDriver initialized successfully.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("EdgeDriver closed.");
        }
    }
}
