package tests;

import base.BaseTests;
import pages.PaymentPage;
import pages.CartPage;
import pages.HeaderPage;
import pages.ProductViewPage;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.testng.Assert.*;

public class PaymentTest extends BaseTests {

    //  ** Payment Page Order Pre-requisite: **
    //   -- logs in, adds one Shoe to cart,
    //   -- clicks BuyNow btn, navigates to Payment Page
    public void buyNowPrep() throws Exception {
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
        assertEquals(crtPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(crtPage.getCartCountTxt(), "1");

        // go to Cart page
        crtPage.clickCartBtn();

        // verify item added to Cart is Shoes
        assertEquals(crtPage.getCartItemTxt(), "ADIDAS ORIGINAL");

        // click Buy Now
        crtPage.clickBuyNowBtn();
    }

    // ** Place Order Pre-requisite: **
    // -- does buyNowPrep()
    // -- verifies order details to be purchased
    // -- sets "Canada" as the shipping country before "PLACE ORDER" button
    public void placeOrderPrep() throws Exception {
        buyNowPrep();
        PaymentPage paymentPage = new PaymentPage(driver);

        // verify cart product is the one being ordered
        assertEquals(paymentPage.getProdNameTxt(), "ADIDAS ORIGINAL");

        // verify shipping info name = "username@ms.com" (logged-in user email)
        assertEquals(paymentPage.getShippingInfoNameTxt(), "username@ms.com");

        // enter "Canada" in "Select Country" and click "Canada" dropdown
        paymentPage.setCountryNameInput();
    }

    //  ********** Payment Page Order Verification Tests ***********

    @Test
    public void paymentPlaceOrderFail() throws Exception {
        // NEGATIVE TEST - enter bogus country name - check error handling
        buyNowPrep();

        PaymentPage paymentPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);

        // verify cart product is the one being ordered
        assertEquals(paymentPage.getProdNameTxt(), "ADIDAS ORIGINAL");

        // verify shipping info name = "username@ms.com" (logged-in user email)
        assertEquals(paymentPage.getShippingInfoNameTxt(), "username@ms.com");

        // simple click "COUPON" btn
        paymentPage.clickApplyCouponBtn();
        assertEquals(paymentPage.getToastTxt(), "Please Enter Coupon");

        // set BOGUS country name input
        paymentPage.setBogusCountryNameInput();

        // click "PLACE ORDER" btn - verify error text - no order is placed
        paymentPage.clickPlaceOrderBtn();
        assertEquals(paymentPage.getToastTxt(), "Please Enter Full Shipping Information");

    }

    @Test
    public void paymentPlaceOrder() throws Exception {
        // happy path single order
        placeOrderPrep();
        PaymentPage payPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);

        // click "PLACE ORDER" btn
        payPage.clickPlaceOrderBtn();
        assertEquals(payPage.getToastTxt(), "Order Placed Successfully");

        // verify order confirmation page
        assertEquals(payPage.getOrderConfirmationBannerTxt(), "THANKYOU FOR THE ORDER.");
        // verify order info reflects correct product ordered
        assertEquals(payPage.getOrderConfirmProductTxt(), "ADIDAS ORIGINAL");

        // click Orders History Page link
        payPage.clickOrdersHistoryPageLink();
        // verify myOrders Page
        assertEquals(getPageURL(), myOrdersPageURL);

        // TODO : move this into BaseTests.
        // test clean up : delete the item(s) purchased in the OrderList
        hdrPage.clickOrdersBtn();
        payPage.deleteOrders((int)payPage.getOrderCount());

        hdrPage.clickSignOutBtn();
    }

    @Test
    public void orderClearsCart() throws Exception {
        // happy path scenario + verifies Cart is clear after product ordered
        placeOrderPrep();
        CartPage crtPage = new CartPage(driver);
        PaymentPage payPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);

        // click "PLACE ORDER" btn
        payPage.clickPlaceOrderBtn();
        assertEquals(payPage.getToastTxt(), "Order Placed Successfully");

        // click Cart btn
        crtPage.clickCartBtn();

        // verify No Product toast
        assertEquals(crtPage.getToastTxt(), "No Product in Your Cart");
        // verify No Products page text
        assertEquals(crtPage.getNoProductTxt(), "No Products in Your Cart !");

        // test clean up : delete the item(s) purchased in the OrderList
        hdrPage.clickOrdersBtn();
        payPage.deleteOrders((int)payPage.getOrderCount());

        hdrPage.clickSignOutBtn();
    }

    @Test
    public void ordersPageDetails() throws Exception {
        // happy path scenario verifying product details
        placeOrderPrep();
        PaymentPage payPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);

        // click "PLACE ORDER" btn
        payPage.clickPlaceOrderBtn();
        assertEquals(payPage.getToastTxt(), "Order Placed Successfully");

        // verify order confirmation page
        assertEquals(payPage.getOrderConfirmationBannerTxt(), "THANKYOU FOR THE ORDER.");
        // verify order info reflects correct product ordered
        assertEquals(payPage.getOrderConfirmProductTxt(), "ADIDAS ORIGINAL");

        // click Orders History Page link (navigates to myOrdersPage)
        payPage.clickOrdersHistoryPageLink();

        // verify myOrders Page
        assertEquals(getPageURL(), myOrdersPageURL);

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("E MMM dd");

        // TODO : current date needs to be current UTC date, not local date
        // TODO : uncomment date assert below once fixed
        // verify order details
        assertEquals(payPage.getOrderNameTxt(), "ADIDAS ORIGINAL");
        assertEquals(payPage.getOrderPriceTxt(), "$ 31500");
       // assertEquals(paymentPage.getOrderDateTxt(), sdf.format(date));

        // test clean up : delete the item(s) purchased in the OrderList
        hdrPage.clickOrdersBtn();
        payPage.deleteOrders((int)payPage.getOrderCount());

        hdrPage.clickSignOutBtn();

    }
    @Test
    public void ordersPageDelete() throws Exception {
        // verifies the ability to delete previously placed order
        // also verifies back to shopping and back to cart buttons
        placeOrderPrep();
        PaymentPage paymentPage = new PaymentPage(driver);
        HeaderPage hdrPage = new HeaderPage(driver);
        CartPage crtPage = new CartPage(driver);

        // click "PLACE ORDER" btn
        paymentPage.clickPlaceOrderBtn();
        assertEquals(paymentPage.getToastTxt(), "Order Placed Successfully");

        // click Orders History Page link
        paymentPage.clickOrdersHistoryPageLink();
        // verify myOrders Page
        assertEquals(getPageURL(), myOrdersPageURL);

        // click "Go Back to Shop" on Orders page
        paymentPage.clickBackToShopBtn();
        // verify at home shop page
        assertEquals(getPageURL(), homePageURL);
        // click ORDERS in header to return to Orders page
        hdrPage.clickOrdersBtn();

        //click delete btn
        paymentPage.clickOrderDeleteBtn();
        // verify toast txt "Orders Deleted Successfully"
        assertEquals(crtPage.getToastTxt(), "Orders Deleted Successfully");
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

        // TODO : move this to BaseTests?
        hdrPage.clickSignOutBtn();
    }
    @Test
    public void twoItemsOrder() throws Exception {
        // verifies two cart items together are included together in one order

        PaymentPage payPage = new PaymentPage(driver);
        CartPage crtPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for iphone
        pvPage.clickSubCatMobilesChkbx();  // isolate to iphone

        // click on product Add to Cart button
        crtPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(crtPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(crtPage.getCartCountTxt(), "1");

        // unselect checkbox for iphone
        pvPage.clickSubCatMobilesChkbx();

        //  ***  ADD SECOND PRODUCT TO CART  ***

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to shoes
        // click view product button

        // click on product Add To Cart button
        crtPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(crtPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(crtPage.getCartCountTxt(), "2");

        // click on header Cart button
        crtPage.clickCartBtn();
        // click CHECKOUT button *** This includes BOTH cart items in the order ***
        crtPage.clickCheckOutBtn();
        // add country
        payPage.setCountryNameInput();
        // click Place Order
        payPage.clickPlaceOrderBtn();

        // navigates to order confirm page
        // click ORDERSHistoryPage link
        payPage.clickOrdersHistoryPageLink();

        assertEquals(payPage.getOrderCount(), 2);

        //  ****  test clean up - delete the count of items found present in the order
        payPage.deleteOrders((int)payPage.getOrderCount());

    }
    @Test
    public void twoItemsOneOrder() throws Exception {
        // verify adding two items into the cart
        // verify ability to order only one item (second added) from the cart

        PaymentPage payPage = new PaymentPage(driver);
        CartPage crtPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        doLogIn();

        // select checkbox for iphone
        pvPage.clickSubCatMobilesChkbx();  // isolate to iphone

        // click on product Add to Cart button
        crtPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(crtPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(crtPage.getCartCountTxt(), "1");  // iPhone in cart

        // unselect checkbox for iphone
        pvPage.clickSubCatMobilesChkbx();

        //  ***  ADD SECOND PRODUCT TO CART  ***

        // select checkbox for shoes
        pvPage.clickSubCatShoesChkbx();     //isolate to shoes
        // click view product button

        // click on product Add To Cart button
        crtPage.clickProdAddToCartBtn();

        // wait and verify toast message "Product Added To Cart"
        assertEquals(crtPage.getToastTxt(), "Product Added To Cart");

        // verify cart button shows 1 item
        assertEquals(crtPage.getCartCountTxt(), "2");  // 1.iPhone and 2.Shoes in cart

        // click on header Cart button
        crtPage.clickCartBtn();

        // click BUY NOW button from last item added to cart (SHOES)
        crtPage.clickSecondBuyNowBtn();
        // add country
        payPage.setCountryNameInput();
        // click Place Order
        payPage.clickPlaceOrderBtn();

        // PlaceOrder navigates to order confirm page
        // click ORDERSHistoryPage link
        payPage.clickOrdersHistoryPageLink();
        // verify count in order is 1
        assertEquals(payPage.getOrderCount(), 1);

        // **** test clean up - delete the count of items found present in the order
        payPage.deleteOrders((int)payPage.getOrderCount());
    }

}
