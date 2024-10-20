package org.evy.toolkit.pages.account;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#_username")
    private WebElement email;

    @FindBy(css = "#_password")
    private WebElement password;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;

    @FindBy(css = ".inverted.menu>div")
    private WebElement validLoginMsg;

    @FindBy(css = ".negative.message p")
    private WebElement invalidLoginMsg;


    public <T>T login(String email,String password,boolean criteria,Class<T>nextPageClass){
        try {
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            click(this.loginBtn,"login button");

            if(criteria){
                waitForElementText(this.validLoginMsg,"Hello","Success login message");
                LoggerUtils.info(getClass(),String.format("Login Success, Navigate to %s : ",nextPageClass.getSimpleName()));
            }

            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to complete login operation",e);
            throw new RuntimeException("Error During login operation",e);
        }
    }


    public boolean getLoginResponseMgs(String operation){
        return switch (operation){
            case "valid"->isDisplayed(this.validLoginMsg,"valid login message");
            case "invalid","empty"->isDisplayed(this.invalidLoginMsg,"invalid login message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
