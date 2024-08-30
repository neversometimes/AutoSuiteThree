package tests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.HomePage;
import static org.testng.Assert.*;

@Test(groups={"functional"})
public class HomeFilterTest extends BaseTests {

    @Test
    public void textSearchFail() throws Exception{
        // negative test: search - no results
        HomePage homePage = new HomePage(driver);
        // login to site
        doLogIn();

        // search for "cheerios"
        homePage.enterSearchTxt("cheerios");
        // verify "No Products Found" toast appears
        assertEquals(homePage.getToastTxt(), "No Products Found");
        assertTrue(homePage.getResultsCount().contains("0"));
    }

    @Test(groups={"bvt"})
    public void textSearch() throws Exception{
        HomePage homePage = new HomePage(driver);
        // login to site
        doLogIn();

        // search for IPHONE
        homePage.enterSearchTxt("IPHONE");
        // verify "IPHONE" returns iphone
        assertTrue(homePage.getProductByName("IPHONE 13 PRO").getText().contains("IPHONE"),
                "IPHONE result");
        //System.out.println(homePage.getResultsCount());
        assertTrue(homePage.getResultsCount().contains("1"), "count=1");
    }
    @Test(groups={"bvt"})
    public void priceFilter() throws Exception{
        HomePage homePage = new HomePage(driver);
        // login to site
        doLogIn();

        // price filter low=0, hi=50000
        homePage.enterMinPrice("0");
        homePage.enterMaxPrice("50000");
        // verify result count = 2, coat and shoes
        assertTrue(homePage.getProductByName("ZARA COAT 3").getText().contains("ZARA"));
        assertTrue(homePage.getProductByName("ADIDAS ORIGINAL").getText().contains("ADIDAS"));
        assertTrue(homePage.getResultsCount().contains("2"), "count=2");
    }
    @Test(groups={"bvt"})
    public void categoriesFilter() throws Exception{
        HomePage homePage = new HomePage(driver);
        // login to site
        doLogIn();

        //select fashion
        homePage.selectFashionChkbx();
        assertTrue(homePage.getProductByName("ZARA COAT 3").getText().contains("ZARA"));
        assertTrue(homePage.getResultsCount().contains("1"));
        homePage.selectFashionChkbx();

        //select electronics
        homePage.selectElectronicsChkbx();
        assertTrue(homePage.getProductByName("IPHONE 13 PRO").getText().contains("IPHONE"));
        assertTrue(homePage.getResultsCount().contains("1"));
        homePage.selectElectronicsChkbx();

        //select household
        homePage.selectHouseholdChkbox();
        assertTrue(homePage.getProductByName("ADIDAS ORIGINAL").getText().contains("ADIDAS"));
        assertTrue(homePage.getResultsCount().contains("1"));
    }

    @Test(groups={"bvt"})
    public void genderFilter() throws Exception {
        HomePage homePage = new HomePage(driver);
        // login to site
        doLogIn();

        //System.out.println(homePage.getResultsCount());

        // select men
        homePage.selectMenChkbx();
        // verify count and items returned
        assertTrue(homePage.getProductByName("ADIDAS ORIGINAL").getText().contains("ADIDAS"));
        assertTrue(homePage.getProductByName("IPHONE 13 PRO").getText().contains("IPHONE"));
        assertTrue(homePage.getResultsCount().contains("2"));

        // unselect men
        homePage.selectMenChkbx();

        // select women
        homePage.selectWomenChkbx();
        // verify count and item returned
        assertTrue(homePage.getProductByName("ZARA COAT 3").getText().contains("ZARA"));
        assertTrue(homePage.getResultsCount().contains("1"));
    }
}
