package base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {

    public static final String MAIN_RESOURCES_DIR = "src/main/resources/";
    public static final String DOWNLOAD_DIR = MAIN_RESOURCES_DIR.concat("download/");
    public static final String SCREENSHOTS_DIR = MAIN_RESOURCES_DIR.concat("screenshots/");
    public static final String REPORTS_DIR = MAIN_RESOURCES_DIR.concat("reports/");
    protected WebDriver driver;
    protected Logger log;

    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        cleanDirectory(REPORTS_DIR);
        cleanDirectory(SCREENSHOTS_DIR);
    }

    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext ctx, Method method) {
        String testName = ctx.getCurrentXmlTest().getName();

        log = LogManager.getLogger(testName);
        log.info(" ==== Test method name: "+ method.getName() +" ====");

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) {
        log.info("Close driver");
        // Close browser
        takeScreenshot(testResult);
        driver.quit();
    }

    @AfterSuite
    public void deleteDownloadedFiles() throws IOException {
        cleanDirectory(DOWNLOAD_DIR);
    }

    protected void sleepy(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cleanDirectory(String directoryPath) throws IOException {

        File directory = new File(directoryPath);

        Assert.assertTrue(directory.isDirectory(), "Invalid directory!");

        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory:%s%n", directoryPath);
        }
    }

    private void takeScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }
}
