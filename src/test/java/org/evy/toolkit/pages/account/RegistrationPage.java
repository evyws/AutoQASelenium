package org.evy.toolkit.pages.account;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    @FindBy(css = "#sylius_customer_registration_firstName")
    private WebElement firstname;

    @FindBy(css = "#sylius_customer_registration_lastName")
    private WebElement lastname;

    @FindBy(css = "#sylius_customer_registration_email")
    private WebElement email;

    @FindBy(css = "#sylius_customer_registration_user_plainPassword_first")
    private WebElement password;

    @FindBy(css = "#sylius_customer_registration_user_plainPassword_second")
    private WebElement confirmation;

    @FindBy(css = "button[type='submit']")
    private WebElement registrationBtn;

    @FindBy(css = ".positive.message p")
    private WebElement validRegistrationMsg;

    @FindBy(css = ".sylius-validation-error")
    private WebElement invalidRegistrationMsg;


    public <T>T registration(String firstname,String lastname,String email,String password,String confirmation,boolean criteria,Class<T>nextPageClass){
        try {
            sendKeys(this.firstname,firstname,"firstname");
            sendKeys(this.lastname,lastname,"lastname");
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            sendKeys(this.confirmation,confirmation,"confirmation");
            click(this.registrationBtn,"registration button");

            if(criteria){
                verifyUrl("/register/");
                LoggerUtils.info(getClass(),"");
            }

            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to complete registration operation",e);
            throw new RuntimeException("Error during registration operation",e);
        }
    }

    public String getRegistrationResponseMsg(String operation){
        return switch (operation){
            case "valid"->getText(this.validRegistrationMsg,"valid registration message");
            case "invalid"->getText(this.invalidRegistrationMsg,"invalid registration message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
