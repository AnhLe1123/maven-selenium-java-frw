package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;

public class Common_01_Register_End_User extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private String firstName, lastName;
    public static String emailAddress, password;

    @Parameters("browser")
    @BeforeTest(description = "Create new common user for all class test")
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@mail.com";
        password = "123456";

        log.info("Pre-condition - Step 01: Open Register page");
        registerPage = homePage.clickToRegisterLink();

        log.info("Pre-condition - Step 02: Input to Firstname textbox with value: " + firstName);
        registerPage.inputToFirstNameTextbox(firstName);

        log.info("Pre-condition - Step 03: Input to Lastname textbox with value: " + lastName);
        registerPage.inputToLastNameTextbox(lastName);

        log.info("Pre-condition - Step 04: Input to Email textbox with value: " + emailAddress);
        registerPage.inputToEmailTextbox(emailAddress);

        log.info("Pre-condition - Step 05: Input to Password textbox with value: " + password);
        registerPage.inputToPasswordTextbox(password);

        log.info("Pre-condition - Step 06: Input to Confirm Password textbox with value: " + password);
        registerPage.inputToConfirmPasswordTextbox(password);

        log.info("Pre-condition - Step 07: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("Pre-condition - Step 08: Verify register success message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        log.info("Pre-condition - Step 09: Click to Logout link");
        homePage = registerPage.clickUserLogoutLink(driver);

        driver.quit();
    }

    public long generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
