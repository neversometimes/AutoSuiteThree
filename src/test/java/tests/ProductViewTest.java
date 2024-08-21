package tests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.ProductViewPage;
import static org.testng.Assert.*;

public class ProductViewTest extends BaseTests{
    @Test
    public void verifyProductPage() throws Exception{
        ProductViewPage pvPage = new ProductViewPage(driver);
        // Verifies entry point and various product details data

        // login to site
        doLogIn();
        pvPage.clickSubCatShoesChkbx();
        pvPage.clickViewShoeBtn();

        assertEquals(pvPage.getProdNameTxt(), "ADIDAS ORIGINAL");
        //grab product price & verify
        assertEquals(pvPage.getProdPriceTxt(), "$ 31500");
        //grab product description & verify
        assertEquals(pvPage.getProdDetails(), "Adidas shoes for Men");
    }
    @Test
    public void verifyShoppingBtn() throws Exception{
        ProductViewPage pvPage = new ProductViewPage(driver);
        // login to site
        doLogIn();
        pvPage.clickSubCatShoesChkbx();
        pvPage.clickViewShoeBtn();

        // click "continue shopping" btn
        pvPage.clickContShoppingBtn();

        // verify page navigates to main app shopping page
        // TODO: move this method into BasePage
        assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/client/dashboard/dash");

    }

}
