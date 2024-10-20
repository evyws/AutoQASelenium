package org.evy.toolkit.pages.checkout;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class CheckoutPage extends BasePage {

    @FindBy(css = "#checkout_billingAddress_firstName")
    private WebElement firstname;

    @FindBy(css = "#checkout_billingAddress_lastName")
    private WebElement lastname;

    @FindBy(css = "#checkout_billingAddress_street")
    private WebElement address;

    @FindBy(css = "#checkout_billingAddress_countryCode")
    private WebElement country;

    @FindBy(css = "#checkout_billingAddress_city")
    private WebElement city;

    @FindBy(css = "#checkout_billingAddress_postcode")
    private WebElement postcode;

    @FindBy(css = "label[for='checkout_shipments_0_method_2']")
    private WebElement fedexShipmentBtn;

    @FindBy(css = "label[for='checkout_payments_0_method_1']")
    private WebElement bankTransferBtn;

    @FindBy(css = "button[type='submit']")
    private WebElement placeOrderBtn;

    public CheckoutPage setFirstName(String firstname){
        sendKeys(this.firstname, firstname, "firstname");
        return this;
    }

    public CheckoutPage setLastName(String lastname){
        sendKeys(this.lastname, lastname, "lastname");
        return this;
    }

    public CheckoutPage setAddress(String address){
        sendKeys(this.address, address, "address");
        return this;
    }

    public CheckoutPage setCountry(String countryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Store the current URL before selecting the country
        String currentUrl = driver.getCurrentUrl();

        // Select the country
        selectByVisibleText(this.country, countryName, "country");

        try {
            wait.until(ExpectedConditions.jsReturnsValue("return jQuery.active == 0"));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)),
                    ExpectedConditions.stalenessOf(country),
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout_billingAddress_countryCode"))
            ));
        } catch (TimeoutException e) {
            LoggerUtils.warn(getClass(), "Page did not reload as expected after country selection. Continuing...");
        }
        PageFactory.initElements(driver, this);
        return this;
    }

    public CheckoutPage setCity(String city) {
        if(this.city.isEnabled()){
            sendKeysWithJS(this.city, city, "city");
        }
        return this;
    }

    public CheckoutPage setPostcode(String postcode){
        sendKeys(this.postcode, postcode, "postcode");
        return this;
    }

    public CheckoutPage clickBankTransferBtn(){
        click(this.bankTransferBtn, "bank transfer button");
        return this;
    }

    public CheckoutPage clickFedexShipmentBtn(){
        click(this.fedexShipmentBtn,"fedex shipment button");
        return this;
    }

    public PlaceOrderPage clickPlaceOrderBtn(){
        click(this.placeOrderBtn, "placeOrderBtn");
        verifyUrl("/order/thank-you");
        LoggerUtils.info(getClass(),"Navigate to PlaceOrderPage");
        return new PlaceOrderPage();
    }
}