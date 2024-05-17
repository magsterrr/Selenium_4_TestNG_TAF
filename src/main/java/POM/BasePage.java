package POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    final String BASE_URL = "http://training.skillo-bg.com:4200/";
    WebDriver driver;
    WebDriverWait wait;
    Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitAndClickOnWebElement(WebElement elm) {
        wait.until(ExpectedConditions.visibilityOf(elm));
        wait.until(ExpectedConditions.elementToBeClickable(elm));
        elm .click();
        waitPageTobeFullyLoaded();
    }

    public void waitAndTypeTextInField(WebElement textField, String inputText) {
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.clear();
        textField.sendKeys(inputText);

        waitPageTobeFullyLoaded();
    }

    public String requestedUrl(String pageSuffix ) {
        return BASE_URL+pageSuffix;
    }

    public void navigateTo(String pageURLSuffix) {
        String currentURL = BASE_URL + pageURLSuffix;

        driver.get(currentURL);
        log.info("CONFIRM # The user has navigated to: " +currentURL);

        waitPageTobeFullyLoaded();
    }

    public boolean isURLLoaded(String pageURL) {
        waitPageTobeFullyLoaded();
        return wait.until(ExpectedConditions.urlContains(pageURL));
    }

    public void waitPageTobeFullyLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }
}