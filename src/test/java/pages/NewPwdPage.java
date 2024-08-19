package pages;

import base.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewPwdPage {

    private WebDriver driver;

    public NewPwdPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations

    @FindBy(css="Copy string literal text to the clipboard")
    @CacheLookup
    WebElement forgotPwdLink;

    @FindBy(css="div.form-group:nth-child(1) > div:nth-child(3) > div:nth-child(1)")
    @CacheLookup
    WebElement emailReqTxt;

    @FindBy(css=".btn.btn-custom.btn-block")
    @CacheLookup
    WebElement saveNewPwdBtn;

    @FindBy(css=".d-flex.justify-content-center .text-reset:first-child")
    @CacheLookup
    WebElement loginLink;

    @FindBy(css=".d-flex.justify-content-center .text-reset:last-child")
    @CacheLookup
    WebElement registerLink;

    @FindBy(css="input[type='email']")
    @CacheLookup
    WebElement emailInput;

    @FindBy(css="input[id='userPassword']")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(css="input[id='confirmPassword']")
    @CacheLookup
    WebElement confirmInput;

    @FindBy(css="#toast-container")
    @CacheLookup
    WebElement toastAlert;

    // Defined Page Actions

    public void clickForgotPwdLink() {
        forgotPwdLink.click();
    }
    public String getEmailReqText() {
        return emailReqTxt.getText();
    }
    public void clickSaveNewPwd() {
        saveNewPwdBtn.click();
    }
    public void clickLoginLink() {
        loginLink.click();
    }
    public void clickRegisterLink() {
        registerLink.click();
    }
    public String getPageURL() {
        return driver.getCurrentUrl();
    }
    public void enterEmailText(String str) {
        emailInput.sendKeys(str);
    }
    public void enterPwdText(String str) {
        passwordInput.sendKeys(str);
    }
    public void enterConfirmText(String str) {
        confirmInput.sendKeys(str);
    }
    public String getMessageToastText() {
        return toastAlert.getText();
    }

}
