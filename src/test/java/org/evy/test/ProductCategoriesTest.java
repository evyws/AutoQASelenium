package org.evy.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.evy.toolkit.config.ConfigManager;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Product Management")
@Feature("Product Categories Navigation")
public class ProductCategoriesTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "productDropdownData")
    @Parameters({"mainCategory", "subCategory", "expectedUrl"})
    @Story("User navigates through product categories")
    @Description("Verify that users can successfully navigate to the correct product category page")
    public void testUserSetProductCategories(String mainCategory, String subCategory, String expectedUrl) {
        String actualUrl = setProductCategories(mainCategory, subCategory);
        AssertionUtils.verifyEquals(actualUrl, ConfigManager.getConfig().baseUrl() + expectedUrl,
                "URL should match the expected category path");
    }

    private String setProductCategories(String mainCategory, String subCategory) {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesDropdown(mainCategory, subCategory)
                .getUrl();
    }
}