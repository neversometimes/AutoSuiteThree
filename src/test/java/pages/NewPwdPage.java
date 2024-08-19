package pages;

import base.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewPwdPage {

    private WebDriver driver;

    public NewPwdPage (WebDriver driver) {
        this.driver = driver;
    }

    // page element references
    private By forgotPwdLink = By.cssSelector(".forgot-password-link");
    private By emailReqTxt = By.cssSelector("div.form-group:nth-child(1) > div:nth-child(3) > div:nth-child(1)");
    private By saveNewPwdBtn = By.cssSelector(".btn.btn-custom.btn-block");
    private By loginLink = By.cssSelector(".d-flex.justify-content-center .text-reset:first-child");
    private By registerLink = By.cssSelector(".d-flex.justify-content-center .text-reset:last-child");
    private By emailInput = By.cssSelector("input[type='email'");
    private By passwordInput = By.cssSelector("input[id='userPassword']");
    private By confirmInput = By.cssSelector("input[id='confirmPassword']");
    private By toastAlert = By.cssSelector("#toast-container");


    // defined page actions
    public void clickForgotPwdLink() {
        driver.findElement(forgotPwdLink).click();
    }
    public String getEmailReqText() {
        return driver.findElement(emailReqTxt).getText();
    }

    public void clickSaveNewPwd() {
        driver.findElement(saveNewPwdBtn).click();
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }
    public String getPageURL() {
        return driver.getCurrentUrl();
    }
    public void enterEmailText(String str) {
        driver.findElement(emailInput).sendKeys(str);
    }
    public void enterPwdText(String str) {
        driver.findElement(passwordInput).sendKeys(str);
    }
    public void enterConfirmText(String str) {
        driver.findElement(confirmInput).sendKeys(str);
    }
    public String getMessageToastText() {
        return driver.findElement(toastAlert).getText();
    }


}
