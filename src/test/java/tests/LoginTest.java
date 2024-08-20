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
    public void verifyLoginNoCreds() {
        LoginPage loginPage = new LoginPage(driver);

        // click login w/o entering any creds
        loginPage.clickLoginBtn();

        // verify error text on page
        assertEquals(loginPage.getEmailErrorTxt(), "*Email is required");
        assertEquals(loginPage.getPwdErrorTxt(), "*Password is required");
    }
    @Test
    public void verifyLoginBadCreds() {
        LoginPage loginPage = new LoginPage(driver);

        // enter non-registered info and try to log in
        loginPage.enterEmail("mrman@job.com");
        loginPage.enterPwd("dummyPW123");
        loginPage.clickLoginBtnToast();

        // verify toast error message
        assertEquals(loginPage.getErrorToast(), "Incorrect email or password.");
    }

    @Test
    public void verifyLogIn() {
        // pre-requisite: assumes valid registered user credentials
        LoginPage loginPage = doLogIn();
        // verify toast login successfully message
        assertEquals(loginPage.getErrorToast(), "Login Successfully");
        assertEquals(loginPage.verifyHomePageTitle(), "Let's Shop");

    }
}
