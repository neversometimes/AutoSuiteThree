package tests;

import base.BaseTests;
import pages.NewPwdPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NewPwdTest extends BaseTests {

    @Test
    public void newPwdNoCreds() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);

        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        // click Save New Password button - no creds entered
        newPwdPage.clickSaveNewPwdErr();

        // verify *Email error text shows
        assertEquals(newPwdPage.getEmailReqText(), "*Email is required");
    }
    @Test
    public void newPwdLoginLink() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);

        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        //click on login link
        newPwdPage.clickLoginLink();
        // verify navigate to login page
        assertEquals(getPageURL(), appLoginPageURL);
    }
    @Test
    public void newPwdRegisterLink() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);
        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        // click on register link
        newPwdPage.clickRegisterLink();

        // verify navigate to register page
        assertEquals(getPageURL(), registerPageURL);
    }

    @Test
    public void newPwdBadCreds() {
        NewPwdPage newPwdPage = new NewPwdPage(driver);
        // click forgot pwd link
        newPwdPage.clickForgotPwdLink();

        // enter junk creds
        newPwdPage.enterEmailText("anyone@earth.gov");
        newPwdPage.enterPwdText("asdfasdf");
        newPwdPage.enterConfirmText("asdfasdf");

        newPwdPage.clickSaveNewPwd();

        // verify error text
        assertEquals(newPwdPage.getToastTxt(), "User Not found.");

    }
    @Test
    public void createNewPwd() {
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
        assertEquals(newPwdPage.getToastTxt(), "Password Changed Successfully");

        // verify page navigates back to login page
        assertEquals(getPageURL(), appLoginPageURL);

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
