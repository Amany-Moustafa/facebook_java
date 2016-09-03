package com.facebook.demo;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageobjects.common.PageBase;
import utilities.CaptureScreenShotHandler;



/**
 * Base class for all test case classes
 * it contains all shared functions and suite initializations
 * Retrieves base url from testng parameters and assigne to local feild to be utilized all over the suite
 * Retrieves browsers parameters to allow multi browser execution for same testcases
 * initialize profiles for the different Web drivers
 * Contains listener for execution status with implementation to capture screenshot ono failure 
 */
public class TestBase 
{
    public static WebDriver driver;
    public static String BaseURl;
    protected static String downloadPath ;
    
    public static FirefoxProfile firefoxProfile()
    {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        downloadPath = System.getProperty("user.dir") + File.separator + "Downloads";
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.dir", downloadPath);
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
        firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.manager.useWindow", false);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
        return firefoxProfile;
    }

    @BeforeSuite
    @Parameters({ "browser", "URL" })
    public void startDriver(@Optional("firefox") String WindowBrowser,
            @Optional("http://www.facebook.com") String URL) {
        if (WindowBrowser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver(firefoxProfile());
        }
        else if (WindowBrowser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "resources"
                    + File.separator + "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type");
            driver = new ChromeDriver(options);
        }

        else if (WindowBrowser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "./resources/IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        BaseURl = URL;
    }

    public static void Navigate(String URL) {
            driver.navigate().to(URL);
    }

    @AfterSuite(alwaysRun=true)
    public void stopDriver() {
        driver.quit();
    }

   
    @AfterMethod
    public void screeshotOnFailure(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
        	CaptureScreenShotHandler.captureScreenshot(driver, result.getName());
        }
    }

    
}
