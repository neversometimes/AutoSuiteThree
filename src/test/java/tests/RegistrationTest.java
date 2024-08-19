package tests;

import base.BaseTests;
import pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

public class RegistrationTest extends BaseTests{



    @Test
    public void verifyRegistrationFail() {
        // negative test : NO required info was provided to register
        RegistrationPage regPage = new RegistrationPage(driver);

        regPage.clickRegisterHereBtn();
        regPage.clickRegisterBtn();
        assertEquals("*Please check above checkbox", regPage.getRegisterErrorText());
    }
    @Test
    public void verifyMissingRequiredInfo() {
        //TODO: add negative test : only SOME required info provided to register
    }
    @Test
    public void verifyBasicRegistrationCreation() {
        // TODO: pre-req: clear previous registration data to ensure new user can register
        RegistrationPage regPage = new RegistrationPage(driver);

        // using only minimum required data to register
        regPage.clickRegisterHereBtn();

        // first, last, email, phone, password/confirm pw, 18+ checkbox
        regPage.enterFirstName("Mister");
        regPage.enterLastName("Goodbar");     //BUG: last name required, but doesn't show required via UI
        regPage.enterEmail("mrgoodbar@gmail.com");
        regPage.enterPhone("4155551112");
        regPage.enterPassword("WordPass99");
        regPage.enterConfirmPW("WordPass99");
        regPage.selectAgeCheckBox();
        regPage.clickRegisterBtn();

        // wait for Account Created Successfully Login page
        // TODO: make wait part of AbstractComponent class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-primary")));

        // verify Account Created Successfully
        assertEquals(regPage.getAccountCreatedLoginBtn(), "Login");

        // if successful, Account Created Successfully Login page appears
        regPage.clickAccountCreatedLoginBtn();

        // wait for page with Login button to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-block.login-btn")));
        // "Login" button routes to generic Log-in Page
        assertEquals(regPage.getLoginBtnValue(), "Login");

    }
}
