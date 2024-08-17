package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

public class BaseTests {

    protected WebDriver driver;

    @BeforeSuite
    public void setup() throws Exception {

        // TODO: add cross-browser capability here

        driver = new ChromeDriver();
    }

    @AfterSuite
    public void teardown() {
        driver.quit();

    }

}
