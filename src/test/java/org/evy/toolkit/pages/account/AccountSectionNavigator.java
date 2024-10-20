package org.evy.toolkit.pages.account;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountSectionNavigator extends BasePage {

    @FindBy(css = "a[href*='login']")
    private WebElement loginLink;

    @FindBy(css = "a[href*='register']")
    private WebElement registerLink;

    @FindBy(css = "a[href*='logout']")
    private WebElement logoutLink;

    @FindBy(css = "a[href*='account']")
    private WebElement accountBtn;



    public LoginPage navigateToLoginPage(){
        click(this.loginLink,"login link");
        verifyTitle("Customer login");
        LoggerUtils.info(getClass(),"Navigate to LoginPage");
        return new LoginPage();
    }

    public RegistrationPage navigateToRegisterPage(){
        click(this.registerLink,"registration link");
        verifyTitle("Register in the store");
        LoggerUtils.info(getClass(),"Navigate to RegisterPage");
        return new RegistrationPage();
    }

    public boolean navigateToLogoutPage(){
        click(this.logoutLink,"logout link");
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(this.accountBtn));
    }
}
