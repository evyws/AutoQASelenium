package org.evy.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.pages.account.RegistrationPage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Registration Functionality")
public class RegistrationTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class,dataProvider = "registrationData")
    @Story("User Registration")
    @Description("Verify user registration process with different input combinations")
    public void testUserRegistration(String firstname, String lastname, String email, String password, String confirmation, String operation, String expectedMsg) {
        String actualMsg = performRegistration(firstname, lastname, email, password, confirmation, operation);
        AssertionUtils.verifyEquals(actualMsg, expectedMsg, "Validating registration response message");
    }

    private String performRegistration(String firstname, String lastname, String email, String password, String confirmation, String operation) {
        return new HomePage()
                .navigateToAccountSection()
                .navigateToRegisterPage()
                .registration(firstname, lastname, email, password, confirmation, false, RegistrationPage.class)
                .getRegistrationResponseMsg(operation);
    }
}