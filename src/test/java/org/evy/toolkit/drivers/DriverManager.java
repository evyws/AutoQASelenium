package org.evy.toolkit.drivers;

import org.evy.toolkit.config.ConfigManager;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * Manages the WebDriver instance for our tests, ensuring that each test runs independently.
 * <p>
 * The {@link #initDriver(BrowserType, boolean)} method initializes the WebDriver, while the
 * {@link #quitDriver()} ()} method cleans up the driver after tests, maintaining test independence.
 * Use the {@link #getDriver()} method to retrieve the current WebDriver instance whenever needed.
 * </p>
 */
public final class DriverManager {

    private static final DriverManager INSTANCE = new DriverManager();

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private DriverManager() {}

    public static DriverManager getInstance() {
        return INSTANCE;
    }

    public void initDriver(BrowserType browserType, boolean headlessMode) {
        try {
            WebDriver driver = browserType.getDriver(headlessMode);
            DRIVER_THREAD_LOCAL.set(driver);
            configureDriver(driver);
            LoggerUtils.info(DriverManager.class, "Successfully Driver Added To ThreadLocal");
        } catch (Exception e) {
            LoggerUtils.error(DriverManager.class, "Error initializing WebDriver: " + e.getMessage(), e);
            throw e;
        }
    }

    public void quitDriver() {
        try {
            WebDriver driver = DRIVER_THREAD_LOCAL.get();
            if (driver != null) {
                driver.quit();
                LoggerUtils.info(DriverManager.class, "Successfully Quit Driver, And Removed From ThreadLocal");
            } else {
                LoggerUtils.warn(DriverManager.class, "No WebDriver instance found to quit.");
            }
        } catch (Exception e) {
            LoggerUtils.error(DriverManager.class, "Error quitting WebDriver: " + e.getMessage(), e);
            throw e;
        } 
    }

    public WebDriver getDriver() {
        WebDriver driver = DRIVER_THREAD_LOCAL.get();
        if (driver == null) {
            LoggerUtils.warn(DriverManager.class, "No WebDriver instance is currently set in ThreadLocal.");
        }
        return driver;
    }

    public void configureDriver(WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.getConfig().pageLoadTime()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getConfig().implicitTime()));
        driver.manage().window().maximize();
        driver.get(ConfigManager.getConfig().baseUrl());
        LoggerUtils.info(DriverManager.class, "Configured driver with base URL: " + ConfigManager.getConfig().baseUrl());
    }
}
