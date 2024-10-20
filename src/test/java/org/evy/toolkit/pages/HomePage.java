package org.evy.toolkit.pages;

import org.evy.toolkit.pages.account.AccountSectionNavigator;
import org.evy.toolkit.pages.product.ProductSectionNavigator;
import org.evy.toolkit.utils.LoggerUtils;

public class HomePage extends BasePage {

    public HomePage(){
        LoggerUtils.info(getClass(), "User is currently on the home page.");
    }

    public AccountSectionNavigator navigateToAccountSection(){
        LoggerUtils.info(getClass(),"User is navigate to Account Section");
        return new AccountSectionNavigator();
    }

    public ProductSectionNavigator navigateToProductSection(){
        LoggerUtils.info(getClass(),"User is navigate to Product Section");
        return new ProductSectionNavigator();
    }

}
