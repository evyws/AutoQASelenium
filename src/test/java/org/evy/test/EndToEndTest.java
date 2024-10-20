package org.evy.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.config.ConfigManager;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Test;

@Epic("End To End")
@Feature("End-to-End Shopping Experience")
public class EndToEndTest extends BaseTest {

    @Test
    @Story("User completes a full shopping journey")
    @Description("This test simulates a complete user journey from login to order placement")
    public void testUserPerformEndToEnd() {
        String actualMsg = performEndToEnd();
        String expectedMsg = "You have successfully placed an order.";
        AssertionUtils.verifyEquals(actualMsg, expectedMsg, "Verify order placement success message");
    }

    private String performEndToEnd() {
        Faker faker = new Faker();
        return new HomePage()
                .navigateToAccountSection()
                .navigateToLoginPage()
                .login(ConfigManager.getConfig().email(), ConfigManager.getConfig().password(), true, HomePage.class)
                .navigateToProductSection()
                .setProductCategoriesDropdown("T-Shirts", "Men")
                .setProductName("Slim fit men")
                .setProductSize("M")
                .setProductQuantity("1")
                .clickAddProductToCart()
                .clickCheckout()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setAddress(faker.address().fullAddress())
                .setCountry("Portugal")
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .clickFedexShipmentBtn()
                .clickBankTransferBtn()
                .clickPlaceOrderBtn()
                .getSuccessOrderMsg();
    }
}