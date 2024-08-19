package tests;

import base.BaseTests;
import pages.NewPwdPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NewPwdTest extends BaseTests {
    String loginPageURL = "https://rahulshettyacademy.com/client/auth/login";
    String registerPageURL = "https://rahulshettyacademy.com/client/auth/register";

    @Test
    public void verifyNewPwdNoCreds() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);

        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        // click Save New Password button - no creds entered
        newPwdPage.clickSaveNewPwdErr();

        // verify *Email error text shows
        assertEquals(newPwdPage.getEmailReqText(), "*Email is required");
    }
    @Test
    public void verifyNewPwdLoginLink() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);

        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        //click on login link
        newPwdPage.clickLoginLink();
        // verify navigate to login page
        assertEquals(newPwdPage.getPageURL(), loginPageURL);
    }
    @Test
    public void verifyNewPwdRegisterLink() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);
        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        // click on register link
        newPwdPage.clickRegisterLink();

        // verify navigate to register page
        assertEquals(newPwdPage.getPageURL(), registerPageURL);
    }

    @Test
    public void verifyNewPwdBadCreds() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);
        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        // enter junk creds
        newPwdPage.enterEmailText("anyone@earth.gov");
        newPwdPage.enterPwdText("asdfasdf");
        newPwdPage.enterConfirmText("asdfasdf");

        newPwdPage.clickSaveNewPwd();

        // verify error text
        assertEquals(newPwdPage.getMessageToastText(), "User Not found.");

    }
    @Test
    public void verifyCreateNewPwd() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);

        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        // enter valid registered email name
        newPwdPage.enterEmailText("username@ms.com");

        // enter new password/confirm
        newPwdPage.enterPwdText("word123PASS");
        newPwdPage.enterConfirmText("word123PASS");

        newPwdPage.clickSaveNewPwd();

        // verify success toast message text
        assertEquals(newPwdPage.getMessageToastText(), "Password Changed Successfully");

        // verify page navigates back to login page
        assertEquals(newPwdPage.getPageURL(), loginPageURL);

        // TEST CLEANUP::
        // reset password BACK to the base password ='passWORD123'
        newPwdPage.clickForgotPwdLink();
        newPwdPage.enterEmailText("username@ms.com");

        // enter new password/confirm
        newPwdPage.enterPwdText("passWORD123");
        newPwdPage.enterConfirmText("passWORD123");

        newPwdPage.clickSaveNewPwd();

    }

}
