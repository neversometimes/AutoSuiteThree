package base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;  //significant problems with safari driver

import org.testng.annotations.*;

import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.nio.charset.StandardCharsets;

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

         // TODO: set window size for test run - don't maximize the window
        //driver.manage().window().maximize();

        return driver;
    }

    @AfterMethod (alwaysRun = true)
    public void teardown() {
        driver.quit();

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

    @DataProvider(name="XlData")
    public Object[][] getData() throws IOException {
        // this data provider derives its data from an XL spreadsheet
        DataFormatter formatter = new DataFormatter();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/xlData.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();

        Object [][] data = new Object[rowCount-1][colCount];
        for (int i=0; i<rowCount-1; i++){
            row = sheet.getRow(i+1);  // gets entire row
            for (int j=0; j<colCount; j++){
                XSSFCell cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);  // formats data as String
            }
        }

        return data;
    }
    @DataProvider(name="JSONData")
    public Object[][] getJSONData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/loginData.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};  //JSON contains 3 maps
    }

    // utility to get JSON data into Map form
    public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // read json file to string
        String jsonContent = FileUtils.readFileToString(
                new File(filePath), StandardCharsets.UTF_8);
        // string to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

        });
        return data;
    }

}
