package tests.profile;

import POM.ProfilePage;
import base.TestBase;
import POM.HomePage;
import POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LikeAndDislikeHappyPath extends TestBase {
    @Test
    public void verifyUserCanLikeTheirPost (){

        final String LOGIN_PAGE_URL = "users/login";
        final String user = "magster";
        final String password = "123456";

        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);

        log.info("The user has navigated to the Login page.");
        loginPage.navigateTo(LOGIN_PAGE_URL);

        log.info("The user has logged in with username and password.");
        loginPage.loginWithUSerAndPassword(user, password);

        boolean isShownLogOutButton = homePage.isLogOutButtonShown();
        Assert.assertTrue(isShownLogOutButton);

        log.info("The user has navigated to the Profile page.");
        homePage.clickOnProfileButton();

        ProfilePage profilePage = new ProfilePage(driver, log);
        profilePage.clickPost(0);
        log.info("The user has clicked on the first post.");

        profilePage.ClickOnLikeButton();
        log.info("The user has clicked on the like button.");

        profilePage.isLikeMessageVisible();

        profilePage.ClickOnDisikeButton();
        log.info("The user has clicked on the dislike button");

        profilePage.isDislikeMessageVisible();
    }
}
