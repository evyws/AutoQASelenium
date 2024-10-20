package org.evy.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.config.ConfigManager;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Logout Functionality")
public class LogoutTest extends BaseTest {

    @Test
    @Story("User Logout")
    @Description("Verify that a logged-in user can successfully log out of the application and that the account button is no longer visible.")
    public void testUserLogout() {
        boolean isLoggedOut = performLogout();
        AssertionUtils.verifyTrue(isLoggedOut, "The account button should not be visible after logout.");
    }

    private boolean performLogout() {
        return new HomePage()
                .navigateToAccountSection()
                .navigateToLoginPage()
                .login(ConfigManager.getConfig().email(), ConfigManager.getConfig().password(), true, HomePage.class)
                .navigateToAccountSection()
                .navigateToLogoutPage();
    }
}