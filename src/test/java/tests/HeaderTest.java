package tests;

import base.BaseTests;
import pages.HeaderPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class HeaderTest extends BaseTests {

    // This test class navigates E2E exclusive through the
    // header links: Home/Orders/Cart/SignOut

    @Test
    public void navigateHeaderLinks() {
        HeaderPage headerPage = new HeaderPage(driver);
        doLogIn();

        // click on Orders
        headerPage.clickOrdersBtn();
        // verify page navigates to Orders page
        //System.out.println(headerPage.getOrdersPageTxt());
        assertEquals(headerPage.getOrdersPageTxt(),
                "You have No Orders to show at this time.\nPlease Visit Back Us");

        // click on Home
        headerPage.clickHomeBtn();
        // verify back at home app page
        assertEquals(headerPage.getHomePageTxt(),
                "User can only see maximum 9 products on a page");

        // click on Cart
        headerPage.clickCartBtn();
        // verify navigates to Cart page
        assertEquals(headerPage.getCartToastTxt(), "No Product in Your Cart");
        assertEquals(headerPage.getCartPageTxt(), "No Products in Your Cart !");

        // click on Home
        headerPage.clickHomeBtn();
        // verify back at home app page
        assertEquals(headerPage.getHomePageTxt(),
                "User can only see maximum 9 products on a page");

        // click sign out btn
        headerPage.clickSignOutBtn();
        // verify at login page
        assertEquals(getPageURL(),appLoginPageURL);
    }

}
