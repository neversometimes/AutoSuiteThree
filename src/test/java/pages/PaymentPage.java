package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BasePage {

    public PaymentPage (WebDriver driver) {
        super(driver);
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

    @FindBy(xpath="//div[@class='email-wrapper ng-star-inserted']//div[1]//div[1]//p[1]")
    WebElement billingAddrNameTxt;

    @FindBy(xpath="//div[@class='email-wrapper ng-star-inserted']//div[1]//div[1]//p[2]")
    WebElement billingCountryTxt;

    @FindBy(xpath="//div[@class='email-container']//div[2]//div[1]//p[1]")
    WebElement deliveryAddrNameTxt;

    @FindBy(xpath="//div[@class='email-container']//div[2]//div[1]//p[2]")
    WebElement deliveryCountryTxt;

    @FindBy(css=".title")
    WebElement orderProdName;

    @FindBy(css=".btn.-teal")
    WebElement viewOrdersBtn;

    // ** Defined Page Actions **

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
    public void clickPlaceOrderBtn() {
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
    public WebElement clickDownloadOrderCSVBtn() {
        downloadOrderCSVBtn.click();
        return downloadOrderCSVBtn;
    }
    public void clickDownloadOrderXLBtn() {
        downloadOrderXLBtn.click();
    }
    public String getPaymentToastTxt() {
        waitForWebElementToAppear(cartToastTxt);
        String s = cartToastTxt.getText();
        waitForWebElementToDisappear(toastVanishes);
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
    public void clickBackToShopBtn() {
        orderGoBackToShopBtn.click();
        waitForWebElementToDisappear(orderGoBackToShopBtn);
    }
    public void clickBackToCartBtn() {
        orderGoBackToCartBtn.click();
        waitForWebElementToDisappear(orderGoBackToCartBtn);
    }
    public String getBillingAddrNameTxt() {
        return billingAddrNameTxt.getText();
    }
    public String getBillingCountryTxt(){
        return billingCountryTxt.getText();
    }
    public String getDeliveryAddrNameTxt() {
        return deliveryAddrNameTxt.getText();
    }
    public String getDeliveryCountryTxt() {
        return deliveryCountryTxt.getText();
    }
    public String getOrderProdNameTxt() {
        return orderProdName.getText();
    }
    public void clickViewOrdersBtn() {
        viewOrdersBtn.click();
    }



}
