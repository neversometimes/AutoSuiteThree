package tests;

import base.BaseTests;
import pages.*;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderSummaryTest extends BaseTests {

    @Test
    public void ordersPageSummary() throws Exception {
        HeaderPage hdrPage = new HeaderPage(driver);
        CartPage crtPage = new CartPage(driver);
        ProductViewPage pvPage = new ProductViewPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        OrderSummaryPage orderSummaryPage = new OrderSummaryPage(driver);

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

        // click "View" button on Orders Page
        paymentPage.clickOrderViewBtn();

        // ** Orders Summary Page **

        // verify billing and delivery address and country
        assertEquals(orderSummaryPage.getBillingAddrNameTxt(), "username@ms.com");
        assertEquals(orderSummaryPage.getBillingCountryTxt(), "Country - Canada");
        assertEquals(orderSummaryPage.getDeliveryAddrNameTxt(), "username@ms.com");
        assertEquals(orderSummaryPage.getDeliveryCountryTxt(), "Country - Canada");

        // verify product ordered name
        assertEquals(orderSummaryPage.getOrderProdNameTxt(), "ADIDAS ORIGINAL");

        // click "View Orders" button
        orderSummaryPage.clickViewOrdersBtn();

        // verify nav to Orders Page
        assertEquals(getPageURL(), myOrdersPageURL);

        // clean up:  delete the order and sign out
        paymentPage.clickOrderDeleteBtn();
        hdrPage.clickSignOutBtn();
    }

}
