package org.evy.toolkit.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum BrowserType {

    CHROME {
        @Override
        public WebDriver getDriver(boolean headless) {
            LoggerUtils.info(BrowserType.class, "Initializing Chrome browser");
            try {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                return new ChromeDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize ChromeDriver", e);
                throw new RuntimeException("Error initializing ChromeDriver", e);
            }
        }
    },

    FIREFOX {
        @Override
        public WebDriver getDriver(boolean headless) {
            LoggerUtils.info(BrowserType.class, "Initializing Firefox browser");
            try {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                return new FirefoxDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize FirefoxDriver", e);
                throw new RuntimeException("Error initializing FirefoxDriver", e);
            }
        }
    },

    EDGE {
        @Override
        public WebDriver getDriver(boolean headless) {
            LoggerUtils.info(BrowserType.class, "Initializing Edge browser");
            try {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                return new EdgeDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize EdgeDriver", e);
                throw new RuntimeException("Error initializing EdgeDriver", e);
            }
        }
    },

    OPERA {
        @Override
        public WebDriver getDriver(boolean headless) {
            LoggerUtils.info(BrowserType.class, "Initializing Opera browser");
            try {
                WebDriverManager.operadriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                return new ChromeDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize OperaDriver", e);
                throw new RuntimeException("Error initializing OperaDriver", e);
            }
        }
    },

    SAFARI {
        @Override
        public WebDriver getDriver(boolean headless) {
            LoggerUtils.info(BrowserType.class, "Initializing Safari browser");
            try {
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize SafariDriver", e);
                throw new RuntimeException("Error initializing SafariDriver", e);
            }
        }
    },

    INTERNET_EXPLORER {
        @Override
        public WebDriver getDriver(boolean headless) {
            LoggerUtils.info(BrowserType.class, "Initializing Internet Explorer browser");
            try {
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize InternetExplorerDriver", e);
                throw new RuntimeException("Error initializing InternetExplorerDriver", e);
            }
        }
    };

    public abstract WebDriver getDriver(boolean headless);
}