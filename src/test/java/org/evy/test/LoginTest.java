package org.evy.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.pages.account.LoginPage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "loginData")
    @Parameters({"email", "password", "operation"})
    @Story("User Login")
    @Description("Verify user login functionality with various credentials and scenarios")
    public void testUserLogin(String email, String password, String operation) {
        boolean actualDisplayMsg = performLogin(email, password, operation);
        AssertionUtils.verifyTrue(actualDisplayMsg,
                String.format("Login %s for user with email: %s",
                        operation.equals("success") ? "should succeed" : "should fail", email));
    }

    private boolean performLogin(String email, String password, String operation) {
        return new HomePage()
                .navigateToAccountSection()
                .navigateToLoginPage()
                .login(email, password, false, LoginPage.class)
                .getLoginResponseMgs(operation);
    }
}