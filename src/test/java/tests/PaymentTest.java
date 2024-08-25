package tests;

import base.BaseTests;
import pages.PaymentPage;
import pages.CartPage;
import pages.HeaderPage;

import org.testng.annotations.Test;
import pages.ProductViewPage;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.*;

public class PaymentTest extends BaseTests {

    //  ** Payment Page Order Pre-requisite **
    //  ** logs in, adds one Shoe to cart, clicks BuyNow btn
    //  **  navigates to Payment Page

    public void placeOrderPrep() throws Exception {
        CartPage crtPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);

        doLogIn();

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to just one product
        // click view product button
        pvPage.clickViewShoeBtn();

        // from Product page, click Add To Product button
        crtPage.clickProdPageAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(crtPage.getCartToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(crtPage.getCartCountTxt(), "1");

        // go to Cart page
        crtPage.clickCartBtn();

        // verify item added to Cart is Shoes
        assertEquals(crtPage.getCartItemTxt(), "ADIDAS ORIGINAL");

        // click Buy Now
        crtPage.clickBuyNowBtn();

    }

    //  ** Payment Page Verifications **
    @Test
    public void paymentPlaceOrder() throws Exception {
        placeOrderPrep();

        PaymentPage paymentPage = new PaymentPage(driver);

        // verify cart product is the one being ordered
        assertEquals(paymentPage.getProdNameTxt(), "ADIDAS ORIGINAL");

        // verify shipping info name = "username@ms.com" (logged-in user email)
        assertEquals(paymentPage.getShippingInfoNameTxt(), "username@ms.com");

        // enter "Canada" in "Select Country" and click "Canada" dropdown
        paymentPage.setCountryNameInput();

        // click "PLACE ORDER" btn
        paymentPage.clickPlaceOrderBtn();
        assertEquals(paymentPage.getPaymentToastTxt(), "Order Placed Successfully");

        // verify order confirmation page
        assertEquals(paymentPage.getOrderConfirmationBannerTxt(), "THANKYOU FOR THE ORDER.");
        // verify order info reflects correct product ordered
        assertEquals(paymentPage.getOrderConfirmProductTxt(), "ADIDAS ORIGINAL");

        // click Orders History Page link
        paymentPage.clickOrdersHistoryPageLink();
        // verify myOrders Page
        assertEquals(getPageURL(), myOrdersPageURL);
    }

    @Test void orderClearsCart() throws Exception {
        placeOrderPrep();
        CartPage crtPage = new CartPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);

        // verify cart product is the one being ordered
        assertEquals(paymentPage.getProdNameTxt(), "ADIDAS ORIGINAL");

        // verify shipping info name = "username@ms.com" (logged-in user email)
        assertEquals(paymentPage.getShippingInfoNameTxt(), "username@ms.com");

        // enter "Canada" in "Select Country" and click "Canada" dropdown
        paymentPage.setCountryNameInput();

        // click "PLACE ORDER" btn
        paymentPage.clickPlaceOrderBtn();
        assertEquals(paymentPage.getPaymentToastTxt(), "Order Placed Successfully");

        // click Cart btn
        crtPage.clickCartBtn();

        // verify No Product toast
        assertEquals(crtPage.getCartToastTxt(), "No Product in Your Cart");
        // verify No Products page text
        assertEquals(crtPage.getNoProductTxt(), "No Products in Your Cart !");

        // test clean up : delete the item in the OrderList
        // click Orders Header btn
        hdrPage.clickOrdersBtn();
        // click delete btn in Orders page and click Sign Out
        paymentPage.clickOrderDeleteBtn();
        hdrPage.clickSignOutBtn();
    }

    @Test
    public void ordersPageDetails() throws Exception {
        paymentPlaceOrder();
        PaymentPage paymentPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("E MMM dd");

        // TODO : current date needs to be current UTC date, not local date

        // verify order details
        assertEquals(paymentPage.getOrderNameTxt(), "ADIDAS ORIGINAL");
        assertEquals(paymentPage.getOrderPriceTxt(), "$ 31500");
        assertEquals(paymentPage.getOrderDateTxt(), sdf.format(date));

        hdrPage.clickSignOutBtn();

    }
    @Test
    public void ordersPageDelete() throws Exception {
        paymentPlaceOrder();
        PaymentPage paymentPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);
        CartPage crtPage = new CartPage(driver);

        // click "Go Back to Shop"
        paymentPage.clickBackToShopBtn();
        // verify at home shop page
        assertEquals(getPageURL(), homePageURL);
        // click ORDERS in header to return to Orders page
        hdrPage.clickOrdersBtn();

        //click delete btn
        paymentPage.clickOrderDeleteBtn();
        // verify toast txt "Orders Deleted Successfully"
        assertEquals(crtPage.getCartToastTxt(), "Orders Deleted Successfully");
        // verify "You have No Orders to show at this time.\n Please Visit Back Us" text
        assertEquals(hdrPage.getOrdersPageTxt(), "You have No Orders to show at this time.\nPlease Visit Back Us");

        // click "Go Back to Cart"
        paymentPage.clickBackToCartBtn();
        // verify page text "No Products in Your Cart!"
        assertEquals(crtPage.getNoProductTxt(), "No Products in Your Cart !");

        // click "Go Back to Shop" (from Cart page)
        crtPage.clickContinueShopping();
        // verify at home shop page
        assertEquals(getPageURL(), homePageURL);

        hdrPage.clickSignOutBtn();

    }

}
