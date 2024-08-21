package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductViewPage extends BasePage {

    private WebDriver driver;

    public ProductViewPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);     // initialize PF elements with driver and locators
    }

    // Page Factory Declarations
    @FindBy(xpath="(//input[@type='checkbox'])[17]")
    WebElement subCatShoesChkbx;

    @FindBy(css=".card-body button:first-of-type")
    WebElement viewShoeBtn;

    @FindBy(css="a[class='continue']")
    WebElement contShoppingBtn;

    @FindBy(xpath="div[class='product-buttons'] button")
    WebElement prodAddToCartBtn;

    @FindBy(tagName="h2")
    WebElement prodNameTxt;

    @FindBy(css="div[class='col-lg-6 rtl-text'] div h3")
    WebElement prodPriceTxt;

    @FindBy(css="div[class='border-product'] p")
    WebElement prodDetails;

    // Defined Page Actions
    public void clickSubCatShoesChkbx() throws Exception {
        subCatShoesChkbx.click();
        Thread.sleep(300);  // seems necessary
        //waitForWebElementToAppear(viewShoeBtn);
    }
    public void clickViewShoeBtn() {
        viewShoeBtn.click();
        waitForWebElementToDisappear(viewShoeBtn);
    }
    public void clickContShoppingBtn() {
        contShoppingBtn.click();
    }
    public String getProdNameTxt() {
        waitForWebElementToAppear(prodNameTxt);
        return prodNameTxt.getText();
    }
    public String getProdPriceTxt() {
        return prodPriceTxt.getText();
    }
    public String getProdDetails() {
        return prodDetails.getText();
    }



}
