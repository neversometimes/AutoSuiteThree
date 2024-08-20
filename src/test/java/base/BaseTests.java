package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;  //tests usually aren't reliable on Safari

import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;



public class BaseTests {

    protected WebDriver driver;

    @BeforeMethod
    public WebDriver setup() {

        // TODO: add cross-browser capability here

        driver = new ChromeDriver();

        // global timeout implicit wait for all tests
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // global maximize window for all tests
        //driver.manage().window().maximize();
        // go to the webapp landing page
        goToLandingPage();
        return driver;
    }

    @AfterMethod
    public void teardown() {
        driver.quit();  // .close?

    }

    public void goToLandingPage() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public LoginPage doLogIn() {
        // pre-requisite: assumes valid registered user credentials

        LoginPage loginPage = new LoginPage(driver);

        // enter non-registered info and try to log in
        loginPage.enterEmail("username@ms.com");
        loginPage.enterPwd("passWORD123");
        loginPage.clickLoginBtnWait();

        return loginPage;
    }

}
