package pages;

import base.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations

    @FindBy(css=".text-reset")
    @CacheLookup
    WebElement registerHereLink;

    @FindBy(id="login")
    @CacheLookup
    WebElement registerBtn;

    @FindBy(xpath="(//div)[35]")
    @CacheLookup
    WebElement registerErrorTxt;

    @FindBy(id="firstname")
    @CacheLookup
    WebElement regFirstName;

    @FindBy(id="lastName")
    @CacheLookup
    WebElement regLastName;

    @FindBy(id="userEmail")
    @CacheLookup
    WebElement regEmail;

    @FindBy(id="userMobile")
    @CacheLookup
    WebElement regPhone;

    @FindBy(id="userPassword")
    @CacheLookup
    WebElement regPW;

    @FindBy(id="confirmPassword")
    @CacheLookup
    WebElement regConfirmPW;

    @FindBy(xpath="//input[@type='checkbox']")
    @CacheLookup
    WebElement regAgeCheckBox;

    @FindBy(css=".btn.btn-primary")
    @CacheLookup
    WebElement regAcctLoginBtn;

    @FindBy(css=".btn.btn-block.login-btn")
    @CacheLookup
    WebElement loginBtn;

    // Defined Page Actions

    public void clickRegisterHereBtn() {
        registerHereLink.click();
    }
    public void clickRegisterBtn() {
        registerBtn.click();
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
    }
    public String getLoginBtnValue() {
        return loginBtn.getDomAttribute("value");
    }

}
