package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);     // initialize PF elements with driver and locators
    }

    // Page Factory Declarations

    @FindBy(css=".btn.btn-block.login-btn")
    WebElement loginBtn;

    @FindBy(css="div[class='form-group'] div[class='invalid-feedback'] div")
    WebElement emailRequiredTxt;

    @FindBy(css="div[class='form-group mb-4'] div[class='invalid-feedback'] div")
    WebElement pwdRequiredTxt;

    @FindBy(id="userEmail")
    WebElement emailInput;

    @FindBy(id="userPassword")
    WebElement pwdInput;

    @FindBy(css="#toast-container")
    WebElement toastAppears;

    @FindBy(css=".ng-animating")
    WebElement toastVanishes;

    // Defined Page Actions

    public void clickLoginBtn(){
        loginBtn.click();
    }
    public void clickLoginBtnWait() {
        loginBtn.click();
        waitForWebElementToAppear(toastAppears);
        waitForWebElementToDisappear(toastAppears);
    }
    public String clickLoginBtnChkToast() {
        loginBtn.click();
        waitForWebElementToAppear(toastAppears);
        String s = toastAppears.getText();
        waitForWebElementToDisappear(toastAppears);
        return s;
    }
    public String getEmailErrorTxt() {
        return emailRequiredTxt.getText();
    }
    public String getPwdErrorTxt() {
        return pwdRequiredTxt.getText();
    }
    public void enterEmail(String str) {
        emailInput.sendKeys(str);
    }
    public void enterPwd(String str) {
        pwdInput.sendKeys(str);
    }

}
