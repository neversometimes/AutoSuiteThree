package tests;

import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

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
        loginPage.clickLoginBtn();

        // wait for toast message in lower right corner to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        // verify toast error message
        assertEquals(loginPage.verifyErrorToast(), "Incorrect email or password.");
    }

    @Test
    public void verifyLogIn() {
        // pre-requisite: registered user site credentials

        LoginPage loginPage = new LoginPage(driver);

        // enter non-registered info and try to log in
        loginPage.enterEmail("username@ms.com");
        loginPage.enterPwd("passWORD123");
        loginPage.clickLoginBtn();

        // wait for toast message in lower right corner to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        // verify toast login successfully message
        assertEquals(loginPage.verifyErrorToast(), "Login Successfully");

        // wait until toast goes away then verify page Title
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        assertEquals(loginPage.verifyHomePageTitle(), "Let's Shop");
    }
}
