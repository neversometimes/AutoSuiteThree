package pages;

import base.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);     // initialize PF elements with driver and locators
    }

    // Page Factory Declarations

    @FindBy(css=".btn.btn-block.login-btn")
    @CacheLookup
    WebElement loginBtn;

    @FindBy(css="div[class='form-group'] div[class='invalid-feedback'] div")
    @CacheLookup
    WebElement emailRequiredTxt;

    @FindBy(css="div[class='form-group mb-4'] div[class='invalid-feedback'] div")
    @CacheLookup
    WebElement pwdRequiredTxt;

    @FindBy(id="userEmail")
    @CacheLookup
    WebElement emailInput;

    @FindBy(id="userPassword")
    @CacheLookup
    WebElement pwdInput;

    @FindBy(css="#toast-container")
    @CacheLookup
    WebElement toastAlert;

    // Defined Page Actions

    public void clickLoginBtn() {
        loginBtn.click();
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
    public String getErrorToast() {
        return toastAlert.getText();
    }
    public String verifyHomePageTitle() {
        return driver.getTitle();
    }

}
