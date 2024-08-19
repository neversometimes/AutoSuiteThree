package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

import java.util.Base64;

public class RegistrationPage extends BasePage {

    private WebDriver driver;

    public RegistrationPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations

    @FindBy(css=".text-reset")
    WebElement registerHereLink;

    @FindBy(id="login")
    WebElement registerBtn;

    @FindBy(xpath="(//div)[35]")
    WebElement registerErrorTxt;

    @FindBy(id="firstName")
    WebElement regFirstName;

    @FindBy(id="lastName")
    WebElement regLastName;

    @FindBy(id="userEmail")
    WebElement regEmail;

    @FindBy(id="userMobile")
    WebElement regPhone;

    @FindBy(id="userPassword")
    WebElement regPW;

    @FindBy(id="confirmPassword")
    WebElement regConfirmPW;

    @FindBy(xpath="//input[@type='checkbox']")
    WebElement regAgeCheckBox;

    @FindBy(css=".btn.btn-primary")
    WebElement regAcctLoginBtn;

    @FindBy(css=".btn.btn-block.login-btn")
    WebElement loginBtn;

    // Defined Page Actions

    public void clickRegisterHereBtn() {
        registerHereLink.click();
    }
    public void clickRegisterBtn() {
        registerBtn.click();
    }
    public void clickRegisterBtnToast() {
        registerBtn.click();
        waitForWebElementToAppear(regAcctLoginBtn);
    }
    public String getRegisterErrorText() {
        return registerErrorTxt.getText();
    }
    public void enterFirstName(String inputText) {
        regFirstName.sendKeys(inputText);
    }
    public void enterLastName(String inputText) {
        regLastName.sendKeys((inputText));
    }
    public void enterEmail(String inputText) {
        regEmail.sendKeys(inputText);
    }
    public void enterPhone(String inputText) {
        regPhone.sendKeys(inputText);
    }
    public void enterPassword(String inputText) {
        regPW.sendKeys(inputText);
    }
    public void enterConfirmPW(String inputText) {
        regConfirmPW.sendKeys(inputText);
    }
    public void selectAgeCheckBox() {
        regAgeCheckBox.click();
    }
    public String getAccountCreatedLoginBtn() {
        return regAcctLoginBtn.getText();
    }
    public void clickAccountCreatedLoginBtn() {
        regAcctLoginBtn.click();
        waitForWebElementToAppear(loginBtn);
    }
    public String getLoginBtnValue() {
        return loginBtn.getDomAttribute("value");
    }

}
