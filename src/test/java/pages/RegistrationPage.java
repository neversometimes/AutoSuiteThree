package pages;

import base.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    // page element references
    private By registerHereLink = By.cssSelector(".text-reset");
    private By registerBtn = By.id("login");
    private By registerErrorText = By.xpath("(//div)[35]");
    private By regFirstName = By.id("firstName");
    private By regLastName = By.id("lastName");
    private By regEmail = By.id("userEmail");
    private By regPhone = By.id("userMobile");
    private By regPW = By.id("userPassword");
    private By regConfirmPW = By.id("confirmPassword");
    private By regAgeCheckBox = By.xpath("//input[@type='checkbox']");
    private By regAcctLoginBtn = By.cssSelector(".btn.btn-primary");
    private By loginBtn = By.cssSelector(".btn.btn-block.login-btn");

    // defined page actions

    public void clickRegisterHereBtn() {
        driver.findElement(registerHereLink).click();
    }
    public void clickRegisterBtn() {
        driver.findElement(registerBtn).click();
    }
    public String getRegisterErrorText() {
        return driver.findElement(registerErrorText).getText();
    }

    public void enterFirstName(String inputText) {
        driver.findElement(regFirstName).sendKeys(inputText);
    }
    public void enterLastName(String inputText) {
        driver.findElement(regLastName).sendKeys((inputText));
    }
    public void enterEmail(String inputText) {
        driver.findElement(regEmail).sendKeys(inputText);
    }
    public void enterPhone(String inputText) {
        driver.findElement(regPhone).sendKeys(inputText);
    }
    public void enterPassword(String inputText) {
        driver.findElement(regPW).sendKeys(inputText);
    }
    public void enterConfirmPW(String inputText) {
        driver.findElement(regConfirmPW).sendKeys(inputText);
    }
    public void selectAgeCheckBox() {
        driver.findElement(regAgeCheckBox).click();
    }
    public String getAccountCreatedLoginBtn() {
        return driver.findElement(regAcctLoginBtn).getText();
    }
    public void clickAccountCreatedLoginBtn() {
        driver.findElement(regAcctLoginBtn).click();
    }
    public String getLoginBtnValue() {
        return driver.findElement(loginBtn).getDomAttribute("value");
    }

}
