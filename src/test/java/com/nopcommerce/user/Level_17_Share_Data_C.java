package com.nopcommerce.user;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_End_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

import java.util.Random;

public class Level_17_Share_Data_C extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("Pre-condtion - Step 01: Open Login page");
        loginPage = homePage.clickToLoginLink();

        log.info("Pre-condtion - Step 02: Set cookie and reload page");
        loginPage.setCookies(driver, Common_01_Register_Cookie.loginCookies);
        loginPage.refreshCurrentPage(driver);

        log.info("Pre-condtion - Step 05: Verify My Account link displayed");
        verifyTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void Search_01_Empty_Data() {
    }

    @Test
    public void Search_02_Data_Not_Exist() {
    }

    @Test
    public void Search_03_Relative_Search() {
    }

    @Test
    public void Search_04_Absolute_Search() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public long generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
