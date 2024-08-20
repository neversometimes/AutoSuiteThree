package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewPwdPage extends BasePage {

    private WebDriver driver;

    public NewPwdPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations

    @FindBy(css=".forgot-password-link")
    WebElement forgotPwdLink;

    @FindBy(css="div.form-group:nth-child(1) > div:nth-child(3) > div:nth-child(1)")
    WebElement emailReqTxt;

    @FindBy(css=".btn.btn-custom.btn-block")
    WebElement saveNewPwdBtn;

    @FindBy(css=".d-flex.justify-content-center .text-reset:first-child")
    WebElement loginLink;

    @FindBy(css=".d-flex.justify-content-center .text-reset:last-child")
    WebElement registerLink;

    @FindBy(css="input[type='email']")
    WebElement emailInput;

    @FindBy(css="input[id='userPassword']")
    WebElement passwordInput;

    @FindBy(css="input[id='confirmPassword']")
    WebElement confirmInput;

    @FindBy(css="#toast-container")
    WebElement toastAlert;

    @FindBy(css="div.form-group:nth-child(1) > div:nth-child(3) > div:nth-child(1)")
    WebElement emailErrorText;

    // Defined Page Actions

    public void clickForgotPwdLink() {
        waitForWebElementToAppear(forgotPwdLink);
        forgotPwdLink.click();
    }
    public String getEmailReqText() {
        return emailReqTxt.getText();
    }
    public void clickSaveNewPwdErr() {
        saveNewPwdBtn.click();
        waitForWebElementToAppear(emailErrorText);
    }
    public void clickSaveNewPwd() {
        saveNewPwdBtn.click();
        waitForWebElementToAppear(toastAlert);
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
