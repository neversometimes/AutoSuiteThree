package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Page Factory Declarations

    @FindBy(css="button[routerlink='/dashboard/cart']")
    WebElement headerAddToCartBtn;

    @FindBy(css="button[class$='btn btn-primary']")
    WebElement pViewAddToCartBtn;

    @FindBy(css=".card-body button:last-of-type")
    WebElement ProdAddToCartBtn;

    @FindBy(css="button[routerlink='/dashboard']")
    WebElement continueShoppingBtn;

    @FindBy(css="button[class='btn btn-custom'] label")
    WebElement cartCountTxt;

    @FindBy(css="div[class='cartSection'] h3")
    WebElement cartItemTxt;

    @FindBy(css=".btn.btn-danger")
    WebElement deleteCartBtn;

    @FindBy(css="div[class='ng-star-inserted'] h1")
    WebElement noProductTxt;

    @FindBy(css="div[class='cartSection removeWrap'] button[class='btn btn-primary']")
    WebElement buyNowBtn;

    @FindBy(css="li[class='totalRow'] button[type='button']")
    WebElement checkOutBtn;

    @FindBy(css=".payment__type.payment__type--cc.active")
    WebElement creditCardBtn;

    @FindBy(css=".cartWrap.ng-star-inserted")
    List<WebElement> cartRows;

    @FindBy (css="li[class='items odd ng-star-inserted'] button[class='btn btn-primary']")
    WebElement secondBuyNowBtn;

    // Defined Page Actions

    public void clickProdAddToCartBtn() {
        waitForWebElementToAppear(ProdAddToCartBtn);
        ProdAddToCartBtn.click();
    }
    public void clickHeaderCartBtn() {
        headerAddToCartBtn.click();
    }
    public void clickProdPageAddToCartBtn() {
        waitForWebElementToAppear(pViewAddToCartBtn);
        pViewAddToCartBtn.click();
    }

    public void clickContinueShopping() {
        continueShoppingBtn.click();
    }
    public String getCartCountTxt() throws Exception {
        Thread.sleep(500);
        return cartCountTxt.getText();
    }
    public void clickCartBtn() {
        headerAddToCartBtn.click();
    }
    public String getCartItemTxt() {
        return cartItemTxt.getText();
    }
    public void clickDeleteCartItemBtn() {
        deleteCartBtn.click();
    }
    public String getNoProductTxt() {
        waitForWebElementToAppear(noProductTxt);
        return noProductTxt.getText();
    }
    public void clickCheckOutBtn() {
        checkOutBtn.click();
    }
    public void clickBuyNowBtn() {
        buyNowBtn.click();
    }
    public String getCreditCardTxt() {
        return creditCardBtn.getText();
    }
    public void clickSecondBuyNowBtn() {
        secondBuyNowBtn.click();
    }

}
