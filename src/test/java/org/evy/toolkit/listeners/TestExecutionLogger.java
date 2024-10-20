package org.evy.toolkit.listeners;

import io.qameta.allure.Allure;
import org.evy.test.BaseTest;
import org.evy.toolkit.drivers.DriverManager;
import org.evy.toolkit.utils.LoggerUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

/**
 * TestExecutionLogger is a TestNG listener that logs test execution events.
 * It logs when tests start, succeed, fail, or are skipped, and attaches
 * screenshots to Allure reports for failed tests.
 */
public class TestExecutionLogger implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtils.info(getClass(), "Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtils.info(getClass(), "Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtils.error(getClass(), "Test Failed: " + result.getName(), null);
        attachScreenshotToAllure(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtils.warn(getClass(), "Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtils.info(getClass(), "Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtils.info(getClass(), "Test Suite Finished: " + context.getName());
    }

    private void attachScreenshotToAllure(String methodName) {
        try {
            WebDriver driver = DriverManager.getInstance().getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(methodName, new ByteArrayInputStream(screenshot));
                LoggerUtils.info(getClass(), "Screenshot attached to Allure report.");
            } else {
                LoggerUtils.error(getClass(), "Driver does not support taking screenshots. Screenshot Error", null);
            }
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Failed to capture screenshot: " + e.getMessage() + " Screenshot Error", e);
        }
    }
}
