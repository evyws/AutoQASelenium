package org.evy.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Product Management")
@Feature("Product Selection From Listing")
public class ProductNameTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "productNameData")
    @Parameters({"mainCategory", "subCategory", "productName", "expectedTitle"})
    @Story("User selects a product from the product listing page")
    @Description("Verify that users can navigate to a specific product page by selecting it from the product listing")
    public void testUserSelectProduct(String mainCategory, String subCategory, String productName, String expectedTitle) {
        String actualTitle = selectProductFromListing(mainCategory, subCategory, productName);
        AssertionUtils.verifyContains(actualTitle, expectedTitle,
                "The product page title should contain the expected product name");
    }

    private String selectProductFromListing(String mainCategory, String subCategory, String productName) {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesDropdown(mainCategory, subCategory)
                .setProductName(productName)
                .getTitle();
    }
}