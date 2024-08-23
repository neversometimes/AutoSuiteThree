package tests;

import base.BaseTests;
import pages.RegistrationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegistrationTest extends BaseTests{

    @Test
    public void registrationFail() {
        // negative test : NO required info was provided to register
        RegistrationPage regPage = new RegistrationPage(driver);

        regPage.clickRegisterHereBtn();
        regPage.clickRegisterBtn();
        assertEquals(regPage.getRegisterErrorText(), "*Please check above checkbox");
    }
    @Test
    public void missingRequiredInfo() {
        //TODO: add negative test : partial required info provided
    }
    @Test
    public void userAlreadyRegistered() {
        // TODO: add negative test : no go - already registered!
        // username@ms.com
        //
    }
    @Test
    public void basicRegistrationCreation() {
        RegistrationPage regPage = new RegistrationPage(driver);

        // using only minimum required data to register
        regPage.clickRegisterHereBtn();

        // TODO : Need to introduce random data here to ensure registration succeeds
        // first, last, email, phone, password/confirm pw, 18+ checkbox
        regPage.enterFirstName("Mister");
        regPage.enterLastName("Rogers");     //BUG: last name required, but doesn't show required via UI
        regPage.enterEmail("mrrogers@gmail.com");
        regPage.enterPhone("4155551112");
        regPage.enterPassword("WordPass99");
        regPage.enterConfirmPW("WordPass99");
        regPage.selectAgeCheckBox();
        regPage.clickRegisterBtnToast();

        // verify Account Created Successfully
        assertEquals(regPage.getAccountCreatedLoginBtn(), "Login");

        // if successful, Account Created Successfully Login page appears
        regPage.clickAccountCreatedLoginBtn();

        // wait for page with Login button to appear
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-block.login-btn")));

        // "Login" button routes to generic Log-in Page
        assertEquals(regPage.getLoginBtnValue(), "Login");

    }
}
