package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.util.concurrent.TimeUnit;

public class TestBase extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public String HOME_URL = "https://www.google.com/";

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        // headless browser testing with Chrome headless option
        else if (browserName.equalsIgnoreCase("chrome-headless")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");
            option.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(option);
        }
    }

    @AfterSuite
    public void stopDriver() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    // take screenshot when test case fail and add it in the screenshots folder
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed! - Taking screenshots..");
            Helper.captureScreenshot(driver, result.getName());
        }
    }

}
