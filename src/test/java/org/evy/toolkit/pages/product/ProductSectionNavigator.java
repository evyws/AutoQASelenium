package org.evy.toolkit.pages.product;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductSectionNavigator extends BasePage {

    @FindBy(css = "h1.ui.monster")
    private WebElement productListingPageHeader;

    public ProductListingPage setProductCategoriesDropdown(String mainCategory,String subCategory){
        try {
            if("Dresses".equalsIgnoreCase(mainCategory)){
                click(getDressElement(),mainCategory);
                waitForElementText(productListingPageHeader,mainCategory,"ProductListingPage Header");

            }
            else{
                click(getMainCategoryElement(mainCategory),mainCategory);
                click(getSubCategoryElement(mainCategory, subCategory),subCategory);
                waitForElementText(productListingPageHeader,subCategory,"ProductListingPage Header");

            }
            LoggerUtils.info(getClass(),String.format("Selected product categories: %s under %s category ",subCategory,mainCategory));
        }catch (Exception e){
            LoggerUtils.info(getClass(),"Failed to select product categories dropdown");
            throw e;
        }
        LoggerUtils.info(getClass(),"Navigate to ProductListingPage");
        return new ProductListingPage();
    }


    private WebElement getMainCategoryElement(String mainCategory){
        String value=String.format("//span[normalize-space()='%s']",mainCategory);
        return driver.findElement(By.xpath(value));
    }

    private WebElement getSubCategoryElement(String mainCategory,String subCategory){
        String value=String.format("//span[normalize-space()='%s']/parent::div//a[normalize-space()='%s']",mainCategory,subCategory);
        return driver.findElement(By.xpath(value));
    }

    private WebElement getDressElement(){
        return driver.findElement(By.xpath("//a[normalize-space()='Dresses']"));
    }

}
