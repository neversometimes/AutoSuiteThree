package tests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.ProductViewPage;
import static org.testng.Assert.*;

@Test(groups={"functional"})
public class ProductViewTest extends BaseTests{

    @Test(groups={"bvt"})
    public void productPage() throws Exception{
        // Verifies entry point and various product details data
        ProductViewPage pvPage = new ProductViewPage(driver);

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
    @Test(groups={"bvt"})
    public void continueShopping() throws Exception{
        ProductViewPage pvPage = new ProductViewPage(driver);
        // login to site
        doLogIn();
        pvPage.clickSubCatShoesChkbx();
        pvPage.clickViewShoeBtn();

        // click "continue shopping" btn
        pvPage.clickContShoppingBtn();

        // verify page navigates to main app shopping page
        assertEquals(getPageURL(), homePageURL);
    }

}
