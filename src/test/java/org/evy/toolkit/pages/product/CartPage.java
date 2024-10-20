package org.evy.toolkit.pages.product;

import org.evy.test.BaseTest;
import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.pages.checkout.CheckoutPage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(css = ".positive.message p")
    private WebElement addProductToCartSuccessMsg;

    @FindBy(css = ".sylius-cart-remove-button")
    private WebElement removeProductFromCartBtn;

    @FindBy(css = "button[value='checkout']")
    private WebElement checkoutBtn;


    public String getAddProductToCartSuccessMsg(){
        return getText(this.addProductToCartSuccessMsg,"add product success message");
    }

    public CartPage clickRemoveProductFromCart(){
        click(this.removeProductFromCartBtn,"remove product from cart button");
        return this;
    }

    public String getRemoveProductFromCartMsg(){
        return getText(this.addProductToCartSuccessMsg,"remove product from cart success message");
    }

    public CheckoutPage clickCheckout(){
        click(this.checkoutBtn,"checkout button");
        verifyUrl("/checkout");
        LoggerUtils.info(getClass(),"Naigate to CheckoutPage");
        return new CheckoutPage();
    }
}
