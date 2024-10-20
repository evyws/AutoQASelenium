package org.evy.toolkit.pages;

import org.evy.toolkit.drivers.DriverManager;
import org.evy.toolkit.utils.ActionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(){
        this.driver= DriverManager.getInstance().getDriver();
        PageFactory.initElements(this.driver,this);
    }

    private FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
    }


    private WebElement waitForElement(WebElement webElement){
        return getFluentWait().until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForElementText(WebElement webElement, String value, String webElementName) {
        ActionUtils.performAction(
                getClass(),
                () -> {
                    getFluentWait().until(ExpectedConditions.textToBePresentInElement(webElement, value));
                },
                String.format("Waiting for text '%s' to be present in element '%s'.", value, webElementName),
                String.format("Failed to wait for text '%s' to be present in element '%s'.", value, webElementName)
        );
    }

    protected void verifyTitle(String title){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    getFluentWait().until(ExpectedConditions.titleContains(title));
                },String.format("Successfully Title Matched To %s",title),
                String.format("Failed Title is not matched to the expected title  %s",title)
        );
    }



    protected void verifyUrl(String expectedUrl) {
        ActionUtils.performAction(
                getClass(),
                () -> {
                    getFluentWait().until(ExpectedConditions.urlContains(expectedUrl));
                },
                String.format("Successfully matched URL to: '%s'", expectedUrl),
                String.format("Failed URL is not matched expected URL '%s'", expectedUrl)
        );
    }

    protected boolean isDisplayed(WebElement webElement,String webElementName){
        return ActionUtils.performAction(
                getClass(),
                webElement::isDisplayed,
                String.format("%s is Displayed",webElementName),
                String.format("%s is not Displayed",webElementName)
        );
    }

    protected void sendKeys(WebElement webElement,String value,String webElementName){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement element=waitForElement(webElement);
                    element.clear();
                    element.sendKeys(value);
                },
                String.format("Successfully send keys to %s:%s",webElementName,value),
                String.format("Failed to send keys to %s ",webElementName)
        );
    }

    protected void sendKeysWithJS(WebElement webElement, String value, String webElementName) {
        ActionUtils.performAction(
                getClass(),
                () -> {
                    WebElement element = waitForElement(webElement);
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].value = arguments[1];", element, value);
                },
                String.format("Successfully sent keys to %s using JavaScript: %s", webElementName, value),
                String.format("Failed to send keys to %s using JavaScript", webElementName)
        );
    }

    protected void click(WebElement webElement,String webElementName){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement element=waitForElement(webElement);
                    JavascriptExecutor js=(JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();",element);
                },
                String.format("Successfully clicked on %s",webElementName),
                String.format("Failed to click on %s",webElementName)
        );
    }

    protected void selectByVisibleText(WebElement webElement,String value,String webElementName){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement element=waitForElement(webElement);
                    Select select=new Select(element);
                    select.selectByVisibleText(value);
                },
                String.format("Successfully select %s from %s dropdown",value,webElementName),
                String.format("Failed to select %s from %s dropdown",value,webElementName)
        );
    }

    protected String getText(WebElement webElement,String webElementName){
        return ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement element=waitForElement(webElement);
                    return  element.getText().trim();
                },
                String.format("Successfully retrieve text from %s ",webElementName),
                String.format("Failed to retrieve text from %s ",webElement)
        );
    }

    public String getUrl(){
        return ActionUtils.performAction(
                getClass(),
                driver::getCurrentUrl,
                "Successfully retrieve current url",
                "Failed to retrieve current url"
        );
    }

    public String getTitle(){
        return ActionUtils.performAction(
                getClass(),
                driver::getTitle,
                "Successfully retrieve title",
                "Failed to retrieve title"
        );
    }





}
