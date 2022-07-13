package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

import java.util.Random;

public class Level_14_Log_ReportNG extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;
    private UserCustomerInfoPageObject customerInfoPage;
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
    }

    @Test
    public void User_01_Register() {
        log.info("Register - Step 01: Open Register page");
        registerPage = homePage.clickToRegisterLink();

        log.info("Register - Step 02: Input to Firstname textbox with value: " + firstName);
        registerPage.inputToFirstNameTextbox(firstName);

        log.info("Register - Step 03: Input to Lastname textbox with value: " + lastName);
        registerPage.inputToLastNameTextbox(lastName);

        log.info("Register - Step 04: Input to Email textbox with value: " + emailAddress);
        registerPage.inputToEmailTextbox(emailAddress);

        log.info("Register - Step 05: Input to Password textbox with value: " + password);
        registerPage.inputToPasswordTextbox(password);

        log.info("Register - Step 06: Input to Confirm Password textbox with value: " + password);
        registerPage.inputToConfirmPasswordTextbox(password);

        log.info("Register - Step 07: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("Register - Step 08: Verify register success message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");

        log.info("Register - Step 09: Click to Logout link");
        homePage = registerPage.clickUserLogoutLink(driver);
    }

    @Test
    public void User_02_Login() {
        log.info("Login - Step 01: Open Login page");
        loginPage = homePage.clickToLoginLink();

        log.info("Login - Step 02: Input to Email textbox with value: " + emailAddress);
        loginPage.inputToEmailTextbox(emailAddress);

        log.info("Login - Step 03: Input to Password textbox with value: " + password);
        loginPage.inputToPasswordTextbox(password);

        log.info("Login - Step 04: Click to Login button");
        homePage = loginPage.clickToLoginButton();

        log.info("Login - Step 05: Verify My Account link displayed");
        verifyTrue(homePage.isMyAccountLinkDisplayed());

        log.info("Login - Step 06: Click to My Account link");
        customerInfoPage = homePage.clickToMyAccountLink();

        log.info("Login - Step 07: Verify Customer info page displayed");
        verifyEquals(customerInfoPage.getPageHeader(), "My account - Customer info");
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
