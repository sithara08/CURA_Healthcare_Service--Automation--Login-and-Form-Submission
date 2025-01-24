package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import drivers.DriverManager;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.PathUtils;
import utils.ScreenshotUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base extends DriverManager {
    private static ExtentReports extent;
    protected static ExtentTest test;
    private String baseUrl;
    private String validUsername;
    private String validPassword;


    /// Initializes ExtentReports before the test suite.
    @BeforeSuite
    public void beforeSuite(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /// Loads config properties before the test class.
    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws IOException {
        Properties config = new Properties();
        FileInputStream configFile = new FileInputStream("config.properties");
        config.load(configFile);

        baseUrl = config.getProperty("baseUrl");
        validUsername = config.getProperty("validUsername");
        validPassword = config.getProperty("validPassword");
    }

    /// Getters for configuration properties
    public String getBaseUrl(){
        return baseUrl;
    }
    public String getValidUsername(){
        return validUsername;
    }
    public String getValidPassword(){
        return validPassword;
    }

    /// Sets up the browser before each test method.
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        initializeDriver(browser);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        getDriver().get(getBaseUrl());
    }

    /// Logs results and takes a screenshot on failure after each test method.
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        ScreenshotUtils screenshotUtils = new ScreenshotUtils();

        test = extent.createTest(result.getMethod().getMethodName());

        /// Log results based on the test execution status
        if (result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test passed.");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            /// Capture screenshot on failure
            screenshotUtils.takeScreenshot(getDriver(), result.getName());
            String screenshotPath = PathUtils.getRelativePath(result.getName(), screenshotUtils.getTimestamp());
            test.fail("Test failed. Screenshot attached: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped.");
        }
        quitDriver();
    }

    /// Quits the driver after the test class.
    @AfterClass
    public void afterClass(){
        quitDriver();
    }

    /// Flushes the ExtentReports instance to save the test report after the test suite.
    @AfterSuite
    public void afterSuite(){
        extent.flush();
    }
}
