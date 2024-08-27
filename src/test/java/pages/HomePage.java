package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);     // initialize PF elements with driver and locators
    }

    // Page Factory Declarations
    @FindBy(css="div[class='py-2 border-bottom ml-3'] input[name='search']")
    WebElement searchBtn;

    @FindBy(css=".mb-3")
    List<WebElement> productTable;

    By prodName = By.cssSelector("h5 b");

    @FindBy(css="div[class='col-md-6'] input[name='minPrice']")
    WebElement minPriceInput;
    @FindBy(xpath="/html[1]/body[1]/app-root[1]/app-dashboard[1]/section[1]/form[1]/div[2]/div[1]/div[2]/input[1]")
    WebElement maxPriceInput;

    @FindBy(xpath="(//input[@type='checkbox'])[12]")
    WebElement fashionChkbx;
    @FindBy(xpath="(//input[@type='checkbox'])[13]")
    WebElement electronicsChkbx;
    @FindBy(xpath="(//input[@type='checkbox'])[14]")
    WebElement householdChkbx;

    @FindBy(xpath="(//input[@type='checkbox'])[20]")
    WebElement menChkbx;
    @FindBy(xpath="(//input[@type='checkbox'])[21]")
    WebElement womenChkbx;

    @FindBy(css="#toast-container")
    WebElement toastAppears;
    @FindBy(css=".ng-animating")
    WebElement toastVanishes;

    @FindBy(css="div[id='res']")
    WebElement showResultsTxt;


    // Defined Page Actions
    public void enterSearchTxt(String str) throws Exception{
        searchBtn.sendKeys(str + Keys.ENTER);
    }

    public List<WebElement> getProductTable() throws Exception{
        Thread.sleep(500);                      // 500ms sleep here after search/filter executes
        //waitForWebElementToAppear(productTableWE);  // inconsistent wait - flaky test
        return productTable;
    }
    public WebElement getProductByName(String productName) throws Exception {

       return  getProductTable().stream()
                .filter(p->p.findElement(prodName).getText()
                .equals(productName)).findFirst().orElse(null);

    }

    public void enterMinPrice(String str) {
        minPriceInput.sendKeys(str);
    }
    public void enterMaxPrice(String str) {
        maxPriceInput.sendKeys(str + Keys.ENTER);
    }
    public void selectFashionChkbx() throws Exception{
        fashionChkbx.click();
        Thread.sleep(300);
    }
    public void selectElectronicsChkbx() throws Exception{
        electronicsChkbx.click();
        Thread.sleep(300);
    }
    public void selectHouseholdChkbox() throws Exception{
        householdChkbx.click();
        Thread.sleep(300);
    }

    public void selectMenChkbx() throws Exception {
        menChkbx.click();
        Thread.sleep(300);
    }
    public void selectWomenChkbx() throws Exception {
        womenChkbx.click();
        Thread.sleep(300);
    }

    public String getFilterErrorToast() {
        waitForWebElementToAppear(toastAppears);
        String s = toastAppears.getText();
        waitForWebElementToDisappear(toastVanishes);
        return s;
    }

    public String getResultsCount() throws Exception{
        return showResultsTxt.getText();
    }


}
