package tests;

import base.BaseTests;
import pages.RegistrationPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Test(groups={"functional"})
public class RegistrationTest extends BaseTests{

    @Test(groups={"bvt"})
    public void registrationFail() {
        // NEGATIVE TEST : NO required info was provided to register
        RegistrationPage regPage = new RegistrationPage(driver);

        regPage.clickRegisterHereBtn();
        regPage.clickRegisterBtn();
        assertEquals(regPage.getRegisterErrorText(), "*Please check above checkbox");
    }
    @Test(groups={"bvt"})
    public void missingRequiredInfo() throws Exception {
        // NEGATIVE TEST : partial required info provided (no last name provided)
        RegistrationPage regPage = new RegistrationPage(driver);

        regPage.clickRegisterHereBtn();

        regPage.enterFirstName("Foo");
        //  no last name provided
        regPage.enterEmail("foobar@snafu.gov");
        regPage.enterPhone("4155551112");
        regPage.enterPassword("WordPass99");
        regPage.enterConfirmPW("WordPass99");
        regPage.selectAgeCheckBox();
        regPage.clickRegisterBtn();
        assertEquals(regPage.getToastTxt(), "Last Name is required!");
        // Product BUG: last name required, but no notification via UI error text

    }
    @Test(groups={"bvt"})
    public void userAlreadyRegistered() throws Exception {
        // NEGATIVE TEST : no go - Email user already registered!
        // username@ms.com
        //
        RegistrationPage regPage = new RegistrationPage(driver);

        regPage.clickRegisterHereBtn();

        regPage.enterFirstName("User");
        regPage.enterLastName("Name");     //BUG: last name required, but doesn't show required via UI
        regPage.enterEmail("username@ms.com");
        regPage.enterPhone("4155551112");
        regPage.enterPassword("WordPass99");
        regPage.enterConfirmPW("WordPass99");
        regPage.selectAgeCheckBox();
        regPage.clickRegisterBtn();
        // BUG: typo in string: "exisits" should be "exists"
        assertEquals(regPage.getToastTxt(), "User already exisits with this Email Id!");

    }
    @Test(groups={"bvt"})
    public void basicRegistrationCreation() throws Exception {
        RegistrationPage regPage = new RegistrationPage(driver);

        // using only minimum required data to register
        regPage.clickRegisterHereBtn();

        // generate random strings for first/last/email
        String firstName = getRandomStr(6);
        String lastName = getRandomStr(6);
        String email = firstName+lastName+"@gmail.com";

        // first, last, email, phone, password/confirm pw, 18+ checkbox
        regPage.enterFirstName(firstName);
        regPage.enterLastName(lastName);     //BUG: last name required, but doesn't show required via UI
        regPage.enterEmail(email);
        regPage.enterPhone("4155551112");
        regPage.enterPassword("WordPass99");
        regPage.enterConfirmPW("WordPass99");
        regPage.selectAgeCheckBox();
        regPage.clickRegisterBtn();
        assertEquals(regPage.getToastTxt(), "Registered Successfully" );

        // verify Account Created Successfully
        assertEquals(regPage.getAccountCreatedLoginBtn(), "Login");

        // if successful, Account Created Successfully Login page appears
        regPage.clickAccountCreatedLoginBtn();

        // "Login" button navigates back to generic Log-in Page
        assertEquals(regPage.getLoginBtnValue(), "Login");
    }
}
