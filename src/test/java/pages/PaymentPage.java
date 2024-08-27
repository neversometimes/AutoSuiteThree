package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class PaymentPage extends BasePage {

    WebDriver driver;

    public PaymentPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations
    @FindBy(css=".item__title")
    WebElement prodName;

    @FindBy(css="label[type='text']")
    WebElement shippingInfoName;

    @FindBy(css="input[placeholder$='Select Country']")
    WebElement countryNameInput;

    @FindBy(css="span[class='ng-star-inserted']")
    WebElement countryNameDropdown;

    @FindBy(css=".btnn.action__submit.ng-star-inserted")
    WebElement placeOrderBtn;

    @FindBy(css=".hero-primary")
    WebElement orderConfirmationBannerTxt;

    @FindBy(css="td[class='line-item product-info-column m-3'] div[class='title']")
    WebElement orderConfirmProductTxt;

    @FindBy(css="label[routerlink='/dashboard/myorders']")
    WebElement ordersHistoryPageLink;

    @FindBy(css="tbody tr:nth-child(4) button")
    WebElement downloadOrderCSVBtn;

    @FindBy(css="tbody tr:nth-child(5) button")
    WebElement downloadOrderXLBtn;

    @FindBy(css="#toast-container")
    WebElement cartToastTxt;

    @FindBy(css=".ng-animating")
    WebElement toastVanishes;

    @FindBy(css="tr td:nth-of-type(2)")
    WebElement orderNameTxt;

    @FindBy(css="tr td:nth-of-type(3)")
    WebElement orderPriceTxt;

    @FindBy(css="tr td:nth-of-type(4)")
    WebElement orderDateTxt;

    @FindBy(css="button[class='btn btn-primary']")
    WebElement orderViewBtn;

    @FindBy(css="tr td:nth-of-type(6) button")
    WebElement orderDeleteBtn;


    @FindBy(css=".btn.btn-primary.col-md-2.offset-md-4")
    WebElement orderGoBackToShopBtn;

    @FindBy(css="div[class='row'] button:last-of-type")
    WebElement orderGoBackToCartBtn;

    @FindBy(css="tbody tr")
    List<WebElement> orderRows;

    @FindBy(css="button[type='submit']")
    WebElement applyCouponBtn;

    // ******** Defined Page Actions ***********

    public String getProdNameTxt() {
        return prodName.getText();
    }
    public String getShippingInfoNameTxt() {
        return shippingInfoName.getText();
    }
    public void setCountryNameInput() {
        countryNameInput.sendKeys("Canada");
        countryNameDropdown.click();
    }
    public void setBogusCountryNameInput() {
        waitForWebElementToDisappear(cartToastTxt);
        countryNameInput.sendKeys("Arizona");
    }
    public void clickApplyCouponBtn() {
        waitForWebElementToAppear(applyCouponBtn);
        waitForElementToBeClickable(applyCouponBtn);
        applyCouponBtn.click();
    }
    public void clickPlaceOrderBtn() {
        waitForWebElementToAppear(placeOrderBtn);
        placeOrderBtn.click();
    }
    public String getOrderConfirmationBannerTxt() {
        return orderConfirmationBannerTxt.getText();
    }
    public String getOrderConfirmProductTxt(){
        return orderConfirmProductTxt.getText();
    }
    public void clickOrdersHistoryPageLink() {
        ordersHistoryPageLink.click();
    }
    public String getPaymentToastTxt() {
        waitForWebElementToAppear(cartToastTxt);
        String s = cartToastTxt.getText();
        waitForWebElementToDisappear(toastVanishes);  // does this even work?
        return s;
    }
    public String getOrderNameTxt() {
        return orderNameTxt.getText();
    }
    public String getOrderPriceTxt() {
        return orderPriceTxt.getText();
    }
    public String getOrderDateTxt() {
        return orderDateTxt.getText();
    }
    public void clickOrderViewBtn() {
        orderViewBtn.click();
    }
    public void clickOrderDeleteBtn() {
        orderDeleteBtn.click();
    }

    public void clickOrderDeleteBtn2(String trNum) {
        By orderDeleteBtn = By.xpath("//tbody/tr[" + trNum + "]/td[6]/button[1]");
        WebElement we = driver.findElement(orderDeleteBtn);
        we.click();
    }
    public void clickBackToShopBtn() {
        orderGoBackToShopBtn.click();
        waitForWebElementToDisappear(orderGoBackToShopBtn);
    }
    public void clickBackToCartBtn() {
        orderGoBackToCartBtn.click();
        waitForWebElementToDisappear(orderGoBackToCartBtn);
    }

    public List<WebElement> getOrderTableRows() throws Exception{
        //Thread.sleep(500);
        return orderRows;
    }
    public long getOrderCount() throws Exception{
        Thread.sleep(500);
        return getOrderTableRows().stream().count();
    }

    public void deleteOrders(Integer numOrders) throws Exception{
        for (Integer i = numOrders; i > 0; i--) {
            clickOrderDeleteBtn2(i.toString());
            waitForWebElementToAppear(cartToastTxt);
            waitForWebElementToDisappear(toastVanishes);
            //long x = getOrderCount(); //included for useful wait?
        }
    }
// tr is count of rows
//tbody/tr[2]/td[6]/button[1]
}
