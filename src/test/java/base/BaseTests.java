package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;  //significant problems with safari driver

import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class BaseTests {
    public String landingPageURL = "https://rahulshettyacademy.com/client";
    public String appLoginPageURL = "https://rahulshettyacademy.com/client/auth/login";
    public String homePageURL = "https://rahulshettyacademy.com/client/dashboard/dash";
    public String registerPageURL = "https://rahulshettyacademy.com/client/auth/register";
    public String myOrdersPageURL = "https://rahulshettyacademy.com/client/dashboard/myorders";
    public WebDriver driver;
    public static String reportDate; // part of unique per run path for generated Extent reports

    public static String setReportDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("MMMddHmm");  // e.g "Aug311513" based on Aug 31 15:13
        return sdf.format(date);
    }

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public WebDriver setup(String browser) throws Exception {

        if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            throw new Exception("Incorrect Browser");
        }

        // global timeout implicit wait for all tests
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // go to the webapp landing page
        goToLandingPage();

        // global maximize window for all tests
        //driver.manage().window().maximize();

        return driver;
    }

    @AfterMethod (alwaysRun = true)
    public void teardown() {
        driver.quit();  // .close?

    }

    public void goToLandingPage() {
        driver.get(landingPageURL);
    }

    public  void doLogIn() {
        // pre-requisite: assumes valid registered user credentials

        LoginPage loginPage = new LoginPage(driver);

        // enter registered info and log in
        loginPage.enterEmail("username@ms.com");
        loginPage.enterPwd("passWORD123");
        loginPage.clickLoginBtnWait();

    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public String getRandomStr(int length) {
        Random random = new Random();
        String alphaStr = "abcdefghijklmnopqrstuvwxyz";
        int rndIndx=0;
        String s = "";
        for (int i=0; i<length; i++){
            rndIndx = random.nextInt(alphaStr.length());
            s = s + alphaStr.charAt(rndIndx);
        }
        return s;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File (System.getProperty("user.dir") +
                                "/reports/" + reportDate + "/" + testCaseName + ".png");
        FileUtils.copyFile(source, file);  // put the SS file in the /reports directory
        return testCaseName + ".png";   // return filname.ext only - file in same path as report
    }

}
