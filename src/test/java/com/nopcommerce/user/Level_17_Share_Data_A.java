package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;

public class Level_17_Share_Data_A extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;
    private String firstName, lastName, emailAddress, password;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@mail.com";
        password = "123456";

        log.info("Pre-condtion - Step 01: Open Register page");
        registerPage = homePage.clickToRegisterLink();

        log.info("Pre-condtion - Step 02: Input to Firstname textbox with value: " + firstName);
        registerPage.inputToFirstNameTextbox(firstName);

        log.info("Pre-condtion - Step 03: Input to Lastname textbox with value: " + lastName);
        registerPage.inputToLastNameTextbox(lastName);

        log.info("Pre-condtion - Step 04: Input to Email textbox with value: " + emailAddress);
        registerPage.inputToEmailTextbox(emailAddress);

        log.info("Pre-condtion - Step 05: Input to Password textbox with value: " + password);
        registerPage.inputToPasswordTextbox(password);

        log.info("Pre-condtion - Step 06: Input to Confirm Password textbox with value: " + password);
        registerPage.inputToConfirmPasswordTextbox(password);

        log.info("Pre-condtion - Step 07: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("Pre-condtion - Step 08: Verify register success message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        log.info("Pre-condtion - Step 09: Click to Logout link");
        homePage = registerPage.clickUserLogoutLink(driver);

        log.info("Pre-condtion - Step 10: Open Login page");
        loginPage = homePage.clickToLoginLink();

        log.info("Pre-condtion - Step 11: Input to Email textbox with value: " + emailAddress);
        loginPage.inputToEmailTextbox(emailAddress);

        log.info("Pre-condtion - Step 12: Input to Password textbox with value: " + password);
        loginPage.inputToPasswordTextbox(password);

        log.info("Pre-condtion - Step 13: Click to Login button");
        homePage = loginPage.clickToLoginButton();

        log.info("Pre-condtion - Step 14: Verify My Account link displayed");
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
