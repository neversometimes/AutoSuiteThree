package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends BasePage {

    public HeaderPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations
    @FindBy(css=".btn.btn-custom[routerlink='/dashboard/']")
    WebElement homeBtn;

    @FindBy(css=".btn.btn-custom[routerlink='/dashboard/myorders']")
    WebElement ordersBtn;

    @FindBy(css=".btn.btn-custom[routerlink='/dashboard/cart']")
    WebElement cartBtn;

    @FindBy(css="li:nth-child(5) button")
    WebElement signOutBtn;

    @FindBy(css=".mt-4.ng-star-inserted")
    WebElement ordersPageTxt;

    @FindBy(css="div[class='ng-star-inserted'] h1")
    WebElement emptyCartPageTxt;

    @FindBy(css="#toast-container")
    WebElement cartToastTxt;

    @FindBy(css="ng-animating")
    WebElement toastVanishes;

    @FindBy(css=".m-2.blink_me")
    WebElement homePageTxt;

    // Defined Page Actions
    public void clickHomeBtn() {
        homeBtn.click();
    }
    public String getHomePageTxt() {
        return homePageTxt.getText();
    }
    public void clickOrdersBtn() {
        ordersBtn.click();
        waitForWebElementToDisappear(homePageTxt);
    }
    public String getOrdersPageTxt() {
        return ordersPageTxt.getText();
    }
    public void clickCartBtn() {
        waitForElementToBeClickable(cartBtn);
        cartBtn.click();
    }
    public String getCartToastTxt() {
        waitForWebElementToAppear(cartToastTxt);
        String s = cartToastTxt.getText();
        waitForWebElementToDisappear(toastVanishes);
        return s;
    }

    public String getCartPageTxt() {
        return emptyCartPageTxt.getText();
    }
    public void clickSignOutBtn() throws Exception {
        Thread.sleep(500);
        signOutBtn.click();
    }





}
