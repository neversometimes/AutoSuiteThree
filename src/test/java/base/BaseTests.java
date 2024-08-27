package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;  //tests usually aren't reliable on Safari

import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;
import java.util.Random;

public class BaseTests {
    public String landingPageURL = "https://rahulshettyacademy.com/client";
    public String appLoginPageURL = "https://rahulshettyacademy.com/client/auth/login";
    public String homePageURL = "https://rahulshettyacademy.com/client/dashboard/dash";
    public String registerPageURL = "https://rahulshettyacademy.com/client/auth/register";
    public String myOrdersPageURL = "https://rahulshettyacademy.com/client/dashboard/myorders";
    protected WebDriver driver;

    @BeforeMethod
    public WebDriver setup() {

        // TODO: add cross-browser capability here

        driver = new ChromeDriver();

        // global timeout implicit wait for all tests
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // go to the webapp landing page
        goToLandingPage();

        // global maximize window for all tests
        //driver.manage().window().maximize();
        //driver.manage().window().fullscreen();

        return driver;
    }

    @AfterMethod
    public void teardown() {
        driver.quit();  // .close?

    }

    public void goToLandingPage() {
        driver.get(landingPageURL);
    }

    public  void doLogIn() {
        // pre-requisite: assumes valid registered user credentials

        LoginPage loginPage = new LoginPage(driver);

        // enter non-registered info and try to log in
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
}
