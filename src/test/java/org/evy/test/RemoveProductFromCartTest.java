package org.evy.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Test;

@Epic("Cart Management")
@Feature("Remove Product From Cart")
public class RemoveProductFromCartTest extends BaseTest {

    @Test
    @Story("User removes a product from the cart")
    @Description("Verify that a user can successfully remove a product from the cart")
    public void testUserRemoveProductFromCart() {
        String actualMsg = performRemoveProductFromCart();
        AssertionUtils.verifyEquals(actualMsg, "Item has been removed from cart", "Verifying the product removal confirmation message");
    }

    private String performRemoveProductFromCart() {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesDropdown("T-Shirts", "Men")
                .setProductName("Slim fit men")
                .setProductSize("M")
                .setProductQuantity("1")
                .clickAddProductToCart()
                .clickRemoveProductFromCart()
                .getRemoveProductFromCartMsg();
    }
}