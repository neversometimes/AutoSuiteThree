package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryPage extends BasePage {

    WebDriver driver;

    public OrderSummaryPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations
    @FindBy(css=".btn.-teal")
    WebElement viewOrdersBtn;

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

    // Defined Page Actions
    public void clickViewOrdersBtn() throws Exception {
        scrollToElement(viewOrdersBtn);
        Thread.sleep(500);
        viewOrdersBtn.click();
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

}
