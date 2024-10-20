package org.evy.toolkit.pages.product;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "#sylius-product-selecting-variant select")
    private WebElement productSize;

    @FindBy(css = "#sylius_add_to_cart_cartItem_quantity")
    private WebElement productQuantity;

    @FindBy(css = "#sylius-product-selecting-variant button")
    private WebElement addProductToCartBtn;

    public ProductPage setProductSize(String productSize){
        selectByVisibleText(this.productSize,productSize,"product size");
        return this;
    }

    public ProductPage setProductQuantity(String productQuantity){
        sendKeys(this.productQuantity,productQuantity,"product quantity");
        return this;
    }

    public CartPage clickAddProductToCart(){
        click(this.addProductToCartBtn,"add product to cart button");
        verifyTitle("Your shopping cart");
        LoggerUtils.info(getClass(),"Navigate to CartPage");
        return new CartPage();
    }
}
