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

@Epic("Cart Management")
@Feature("Add Product to Cart")
public class AddProductToCartTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "productAttributeData")
    @Parameters({"mainCategory", "subCategory", "productName", "productSize", "productQuantity", "expectedMsg"})
    @Story("User adds a product to the shopping cart")
    @Description("Verify that a user can successfully add a product to the shopping cart with specified attributes")
    public void testUserAddProductToCart(String mainCategory, String subCategory, String productName,
                                         String productSize, String productQuantity, String expectedMsg) {
        String actualMsg = performAddProductToCart(mainCategory, subCategory, productName, productSize, productQuantity);
        AssertionUtils.verifyEquals(actualMsg, expectedMsg,
                "The actual success message should match the expected message when adding a product to cart");
    }

    private String performAddProductToCart(String mainCategory, String subCategory, String productName,
                                           String productSize, String productQuantity) {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesDropdown(mainCategory, subCategory)
                .setProductName(productName)
                .setProductSize(productSize)
                .setProductQuantity(productQuantity)
                .clickAddProductToCart()
                .getAddProductToCartSuccessMsg();
    }
}