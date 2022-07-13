package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.lang.reflect.Method;
import java.util.Random;

public class Level_15_Extent_Report_V2_Screenshot extends BaseTest {
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
    public void User_01_Register(Method method) {
//        ExtentTestManager.startTest(method.getName(), "User_01_Register");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 01: Open Register page");
//        registerPage = homePage.clickToRegisterLink();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 02: Input to Firstname textbox with value: " + firstName);
//        registerPage.inputToFirstNameTextbox(firstName);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 03: Input to Lastname textbox with value: " + lastName);
//        registerPage.inputToLastNameTextbox(lastName);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 04: Input to Email textbox with value: " + emailAddress);
//        registerPage.inputToEmailTextbox(emailAddress);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 05: Input to Password textbox with value: " + password);
//        registerPage.inputToPasswordTextbox(password);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 06: Input to Confirm Password textbox with value: " + password);
//        registerPage.inputToConfirmPasswordTextbox(password);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 07: Click to Register button");
//        registerPage.clickToRegisterButton();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 08: Verify register success message displayed");
//        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");
//        ExtentTestManager.endTest();
    }

    @Test
    public void User_02_Login(Method method) {
//        ExtentTestManager.startTest(method.getName(), "User_02_Login");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Click to Logout link");
//        homePage = registerPage.clickUserLogoutLink(driver);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Open Login page");
//        loginPage = homePage.clickToLoginLink();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Input to Email textbox with value: " + emailAddress);
//        loginPage.inputToEmailTextbox(emailAddress);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Input to Password textbox with value: " + password);
//        loginPage.inputToPasswordTextbox(password);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 05: Click to Login button");
//        homePage = loginPage.clickToLoginButton();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 06: Verify My Account link displayed");
//        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 07: Click to My Account link");
//        customerInfoPage = homePage.clickToMyAccountLink();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 08: Verify Customer info page displayed");
//        Assert.assertEquals(customerInfoPage.getPageHeader(), "My account - Customer info");
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
