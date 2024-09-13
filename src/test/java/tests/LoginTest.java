package tests;

import base.BaseTests;
import base.Retry;
import pages.LoginPage;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

@Test(groups = {"functional"})
public class LoginTest extends BaseTests {
    // This class includes a basic set of tests for logging into the web app
    // TODO - E2E scenario:
    // after new registration, verify login
    // TODO - E2E scenario:
    // after new password set, verify login

    @Test(groups={"bvt"})
    public void loginNoCreds() {
        LoginPage loginPage = new LoginPage(driver);

        // click login w/o entering any creds, no waiting for toast
        loginPage.clickLoginBtn();

        // verify error text on page
        assertEquals(loginPage.getEmailErrorTxt(), "*Email is required");
        assertEquals(loginPage.getPwdErrorTxt(), "*Password is required");
    }
    @Test(groups={"bvt"})
    public void loginBadCreds() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        // enter non-registered info and try to log in
        loginPage.enterEmail("mrman@job.com");
        loginPage.enterPwd("dummyPW123");

        // click loginBtn and wait to verify toast error message
        loginPage.clickLoginBtn();
        assertEquals(loginPage.getToastTxt(), "Incorrect email or password.");
    }
    @Test(groups={"bvt"}, retryAnalyzer = Retry.class, dataProvider="JSONData")
    public void basicLogIn(HashMap<String, String> data) throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        // pre-requisite: assumes valid registered user credentials
        loginPage.enterEmail(data.get("email"));
        loginPage.enterPwd(data.get("password"));

        // click loginBtn and wait to verify toast success message
        loginPage.clickLoginBtn();
        //Thread.sleep(500);
        assertEquals(loginPage.getToastTxt(), "Login Successfully");

        assertEquals(getPageTitle(), "Let's Shop");

    }
}
