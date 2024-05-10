package POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;


public class RegistrationPage extends BasePage {

    public static final String REGISTRATION_PAGE_URL = "users/register";

    @FindBy (xpath = "//input[contains(@formcontrolname,'username')]")
    private WebElement registrationUsernameField;

    @FindBy (xpath = "//input[contains(@formcontrolname,'email')]")
    private WebElement registrationEmailField;

    @FindBy (id = "defaultRegisterFormPassword")
    private WebElement registrationPasswordField;

    @FindBy (id = "defaultRegisterPhonePassword")
    private WebElement registrationConfirmPasswordField;

    @FindBy (id = "sign-in-button")
    private WebElement signInButton;

    public RegistrationPage (WebDriver driver, Logger log) {
        super(driver,log);
        PageFactory.initElements(driver, this);
    }

    public void openRegistrationPage() { navigateTo(REGISTRATION_PAGE_URL);}

    public void provideUserName(String username) {
        waitAndTypeTextInField(registrationUsernameField, username);
    }

    public void provideEmail(String email) {
        waitAndTypeTextInField(registrationEmailField, email);
    }

    public void providePassword(String userPassword) {
        waitAndTypeTextInField(registrationPasswordField, userPassword);
    }

    public void confirmPassword(String confirmPassword) {
        waitAndTypeTextInField(registrationConfirmPasswordField, confirmPassword);
    }

    public void clickOnRegistrationButton() {
        waitAndClickOnWebElement(signInButton);
    }

    public void register (String username, String email, String userPassword, String confirmPassword) {
        provideUserName(username);
        provideEmail(email);
        providePassword(userPassword);
        confirmPassword(confirmPassword);
        clickOnRegistrationButton();
    }
}
