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
        File postPicture = new File("src/test/java/resourcesUpload/testUpload.jpg");
        String caption = "Testing create post caption";

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
            String caption)
    {



    }

}
