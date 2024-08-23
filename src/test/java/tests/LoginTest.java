package tests;

import base.BaseTests;
import pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTests {
    // This class includes a basic set of tests for logging into the web app
    // TODO - E2E scenario:
    // after new registration, verify login
    // TODO - E2E scenario:
    // after new password set, verify login

    @Test
    public void loginNoCreds() {
        LoginPage loginPage = new LoginPage(driver);

        // click login w/o entering any creds, no waiting for toast
        loginPage.clickLoginBtn();

        // verify error text on page
        assertEquals(loginPage.getEmailErrorTxt(), "*Email is required");
        assertEquals(loginPage.getPwdErrorTxt(), "*Password is required");
    }
    @Test
    public void loginBadCreds() {
        LoginPage loginPage = new LoginPage(driver);

        // enter non-registered info and try to log in
        loginPage.enterEmail("mrman@job.com");
        loginPage.enterPwd("dummyPW123");

        // click loginBtn and wait to verify toast error message
        assertEquals(loginPage.clickLoginBtnChkToast(), "Incorrect email or password.");
    }
    @Test
    public void basicLogIn() {
        LoginPage loginPage = new LoginPage(driver);
        // pre-requisite: assumes valid registered user credentials
        loginPage.enterEmail("username@ms.com");
        loginPage.enterPwd("passWORD123");
        // click loginBtn and wait to verify toast success message
        assertEquals(loginPage.clickLoginBtnChkToast(), "Login Successfully");
        assertEquals(loginPage.verifyHomePageTitle(), "Let's Shop");

    }
}
