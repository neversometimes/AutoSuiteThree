package tests;

import base.BaseTests;
import base.Retry;
import pages.CartPage;
import org.testng.annotations.Test;
import pages.ProductViewPage;
import static org.testng.Assert.*;

@Test (groups={"functional"})
public class CartTest extends BaseTests {

    @Test(groups={"bvt"})
    public void emptyCart () throws Exception {

        CartPage cartPage = new CartPage(driver);
        doLogIn();

        // click Cart button in header
        cartPage.clickHeaderCartBtn();
        // verify toast "No Product in Your Cart"
        assertEquals(cartPage.getToastTxt(), "No Product in Your Cart");
        // verify page text "No Products in Your Cart!"
        assertEquals(cartPage.getNoProductTxt(), "No Products in Your Cart !");

        // click "Continue Shopping" btn
        cartPage.clickContinueShopping();
        // verify back at site home shopping page again
        assertEquals(getPageURL(), homePageURL);
    }
    @Test(groups = {"bvt"})
    public void ATCFromHomePage() throws Exception {
        CartPage cartPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to just one product
        // click product Add to Cart button
        cartPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(cartPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(cartPage.getCartCountTxt(), "1");
    }
    @Test(groups={"bvt"})
    public void ATCFromProdPage() throws Exception {
        CartPage cartPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to just one product
        // click view product button
        pvPage.clickViewShoeBtn();

        // from Product page, click Add To Product button
        cartPage.clickProdPageAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(cartPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(cartPage.getCartCountTxt(), "1");
    }
    @Test
    public void twoCartProductsCount() throws Exception {
        CartPage cartPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for iphone
        pvPage.clickSubCatMobilesChkbx();

        // click SHOPPING PAGE Add to Cart button
        cartPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(cartPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(cartPage.getCartCountTxt(), "1");

        // unselect checkbox for iphone
        pvPage.clickSubCatMobilesChkbx();

        //  ***  ADD SECOND PRODUCT TO CART  ***

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to just one product
        // click view product button
        pvPage.clickViewShoeBtn();

        // from PRODUCT VIEW page, click Add To Product button
        cartPage.clickProdPageAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(cartPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(cartPage.getCartCountTxt(), "2");
    }
    @Test(groups={"bvt"})
    public void addShoesItemToCart() throws Exception {
        CartPage cartPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to just one product
        // click product Add to Cart button
        cartPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(cartPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(cartPage.getCartCountTxt(), "1");


        // go to Cart page
        cartPage.clickCartBtn();

        // verify item added to Cart is Shoes
        assertEquals(cartPage.getCartItemTxt(), "ADIDAS ORIGINAL");
    }
    @Test(groups={"bvt"}, retryAnalyzer = Retry.class)
    public void deleteCartItem() throws Exception {
        CartPage cartPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to just one product
        // click product Add to Cart button
        cartPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(cartPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(cartPage.getCartCountTxt(), "1");

        // go to Cart page
        cartPage.clickCartBtn();

        // delete cart item
        cartPage.clickDeleteCartItemBtn();

        // verify page text confirmation of deletion
        assertEquals(cartPage.getNoProductTxt(), "No Products in Your Cart !");
    }
    @Test(groups={"bvt"})
    public void cartBuyNowAndCheckout() throws Exception {
        // Buy Now and Checkout both go to the order page
        // this test verifies BOTH of those navigation paths

        CartPage cartPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to just one product
        // click product Add to Cart button
        cartPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(cartPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(cartPage.getCartCountTxt(), "1");

        // go to Cart page
        cartPage.clickCartBtn();

        // click Buy Now
        cartPage.clickBuyNowBtn();
        // verify on Order page
        assertEquals(cartPage.getCreditCardTxt(), "Credit Card");
        // click Cart btn to go back to Cart page
        cartPage.clickCartBtn();
        // click Checkout
        cartPage.clickCheckOutBtn();
        // verify on order page
        assertEquals(cartPage.getCreditCardTxt(), "Credit Card");
    }

}
