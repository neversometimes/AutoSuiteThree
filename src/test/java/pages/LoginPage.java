package pages;

import base.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    // page element references
    private By loginBtn = By.cssSelector(".btn.btn-block.login-btn");
    private By emailRequiredTxt = By.cssSelector("div[class='form-group'] div[class='invalid-feedback'] div");
    private By pwdRequiredTxt = By.cssSelector("div[class='form-group mb-4'] div[class='invalid-feedback'] div");
    private By emailInput = By.id("userEmail");
    private By pwdInput = By.id("userPassword");
    private By toastAlert = By.cssSelector("#toast-container");


    // defined page actions

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public String getEmailErrorTxt() {
        return driver.findElement(emailRequiredTxt).getText();
    }
    public String getPwdErrorTxt() {
        return driver.findElement(pwdRequiredTxt).getText();
    }
    public void enterEmail(String str) {
        driver.findElement(emailInput).sendKeys(str);
    }
    public void enterPwd(String str) {
        driver.findElement(pwdInput).sendKeys(str);
    }
    public String verifyErrorToast() {
        return driver.findElement(toastAlert).getText();
    }
    public String verifyHomePageTitle() {
        return driver.getTitle();
    }


}
