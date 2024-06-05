package tests.registration;

import POM.RegistrationPage;
import POM.HomePage;
import POM.LoginPage;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.ContentGeneration.*;

public class RegistrationHappyPath extends TestBase {

    @Test
    public void VerifyUserCanRegisterWithValidData()  {

        final String username = createUser();
        final String email = createEmail();
        final String password = createPassword();
        final String confirmPassword = password;

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);

        log.info("STEP 1: Not logged in user has opened the Skillo registration page.");
        registrationPage.openRegistrationPage();

        log.info("STEP 2: The user has provided a valid username.");
        registrationPage.provideUserName(username);

        log.info("STEP 3: The user has provided a valid email");
        registrationPage.provideEmail(email);

        log.info("STEP 4: The user has provided a valid password");
        registrationPage.providePassword(password);

        log.info("STEP 5: The user has confirmed the password.");
        registrationPage.confirmPassword(confirmPassword);

        log.info("STEP 6: The user has clicked on the register button.");
        registrationPage.clickOnRegistrationButton();

        HomePage homePage = new HomePage(driver, log);

        boolean isShownLogOutButton = homePage.isLogOutButtonShown();
        Assert.assertTrue(isShownLogOutButton);
    }
}
