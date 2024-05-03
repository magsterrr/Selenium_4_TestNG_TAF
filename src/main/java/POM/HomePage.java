package POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.TimeoutException;

public class HomePage extends iSkillo {
    public static final String HOME_PAGE_URL = "posts/all";

    @FindBy(id = "nav-link-login")
    private WebElement navigationLoginButton;
    @FindBy(id = "nav-link-new-post")
    private WebElement navigationNewPostButton;

    @FindBy(xpath = "//ul[contains(@class,'navbar-nav my-ml d-none d-md-block')]")
    private WebElement navigationLogOutButton;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);

        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        navigateTo(HOME_PAGE_URL);
    }

    public void navigateToLoginPageViaClickOnNavigationLoginButton() {
        waitAndClickOnWebElement(navigationLoginButton);
    }

    public void clickOnNavButtonForNewPost() {
        waitAndClickOnWebElement(navigationNewPostButton);
    }

    public void waitNewPostNavigationLinkToAppear() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) navigationNewPostButton));
    }

    public boolean isLogOutButtonShown() {
        boolean isButtonShown = false;
        log.info(" ACTION @ The user is verifying if the navigation log out button is presented");
        try {
            wait.until(ExpectedConditions.visibilityOf(navigationLogOutButton));
            log.info("CONFIRM # Navigation logout button is presented to the user");
            isButtonShown = true;
        } catch (TimeoutException e) {
            log.error("ERROR ! The navigation logout button was not presented to the user");
            isButtonShown = false;
        }
        return isButtonShown;
    }
}
