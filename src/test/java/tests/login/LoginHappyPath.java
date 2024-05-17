package tests.login;

import POM.HomePage;
import POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;

public class LoginHappyPath extends TestBase {
    public static final int WAIT = 3333;

    @Test
    public void verifyUserCanNavigateToLoginPageViaNavigationLoginButton() throws InterruptedException {
        final String USERNAME = "magster";
        final String PASSWORD = "123456";

        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();
        boolean isLogOutButtonShown = homePage.isLogOutButtonShown();
        Assert.assertFalse(isLogOutButtonShown);

        log.info("STEP 2: The user has navigated to the Skillo LoginPage");
        homePage.navigateToLoginPageViaClickOnNavigationLoginButton();

        log.info("STEP 3: The user has verified that the LoginPage is open as per requirements ");
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 4: The user has provided a valid username");
        loginPage.provideUserName(USERNAME);

        log.info("STEP 5: The user has provided a valid password");
        loginPage.providePassword(PASSWORD);

        log.info("STEP 6: The user has clicked on login submit button");
        loginPage.clickOnLoginSubmitButton();

        boolean isShownLogOutButton = homePage.isLogOutButtonShown();
        Assert.assertTrue(isShownLogOutButton);
    }
}