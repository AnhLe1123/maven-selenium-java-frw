package com.saucelab;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.saucelab.LoginPageOject;
import pageObjects.saucelab.PageGeneratorManager;
import pageObjects.saucelab.ProductPageOject;

public class Level_20_Sort_ASC_DESC extends BaseTest {
    private WebDriver driver;
    private LoginPageOject loginPage;
    private ProductPageOject productPage;
    private String username, password;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        username = "standard_user";
        password = "secret_sauce";

        driver = getBrowserDriver(browserName, pageUrl);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToUsernameTextbox(username);
        loginPage.inputToPasswordTextbox(password);
        productPage = loginPage.clickToLoginButton();
    }

    @Test
    public void TC_01_Sort_Product_Name() {
        //Sort ASC
        productPage.selectOptionInSortDropdown("Name (A to Z)");
        verifyTrue(productPage.isProductNameSortedAscending());

        //Sort DESC
        productPage.selectOptionInSortDropdown("Name (Z to A)");
        verifyTrue(productPage.isProductNameSortedDescending());
    }

    @Test
    public void TC_02_Sort_Product_Price() {
        //Sort ASC
        productPage.selectOptionInSortDropdown("Price (low to high)");
        verifyTrue(productPage.isProductPriceSortedAscending());

        //Sort DESC
        productPage.selectOptionInSortDropdown("Price (high to low)");
        verifyTrue(productPage.isProductPriceSortedDescending());
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
