package tests.post;

import POM.*;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

public class PostHappyPath extends TestBase {
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUsers() {
        File postPicture = new File("src/test/resources/resourcesUpload/testUpload.jpg");
        String caption = "Testing the create post caption";

        return new Object[][]{{
                "magster", "123456",
                "magster", postPicture, caption},
        };
    }
    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanCreateNewPost(
            String user,
            String password,
            String username,
            File file,
            String caption){
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(driver, log);

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateTo(LOGIN_PAGE_URL);

        log.info("The user has logged in.");
        loginPage.loginWithUSerAndPassword(user, password);

        boolean isShownLogOutButton = homePage.isLogOutButtonShown();
        Assert.assertTrue(isShownLogOutButton);

        log.info("The user has clicked on the New Post button.");
        homePage.clickOnNavButtonForNewPost();

        PostPage postPage = new PostPage(super.driver, log);

        log.info("The user has selected the file for uploading.");
        postPage.uploadPicture(file);

        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is incorrect!");

        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(driver, log);
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}
