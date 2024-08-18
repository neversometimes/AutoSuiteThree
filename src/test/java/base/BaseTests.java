package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class BaseTests {

    protected WebDriver driver;

    @BeforeMethod
    public WebDriver setup() {

        // TODO: add cross-browser capability here

        driver = new ChromeDriver();

        // global timeout implicit wait for all tests
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

}
