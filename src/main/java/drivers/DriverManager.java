package drivers;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    /// Creates a ThreadLocal storage for WebDriver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /// Initializes the WebDriver based on the specified browser.
    public static void initializeDriver(String browser) {
        WebDriver localDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                localDriver = new ChromeDriver();
                break;
            case "edge":
                localDriver = new EdgeDriver();
                break;
            case "firefox":
                localDriver = new FirefoxDriver();
                break;
            default:
                throw new InvalidArgumentException("Unsupported browser: " + browser);
        }
        /// Store WebDriver instance in ThreadLocal
        driver.set(localDriver);
    }

    /// Retrieves and returns WebDriver specific to the current thread
    public static WebDriver getDriver() {
        return driver.get();
    }

    /// Quits the WebDriver and removes it from the ThreadLocal storage.
    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
