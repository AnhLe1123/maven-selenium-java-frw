package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import data.nopcommerce.UserTestData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class Level_21_Manage_Data_Part_III extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private String emailAddress;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        driver = getBrowserDriver(browserName, pageUrl);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        this.emailAddress = UserTestData.Register.EMAIL_ADDRESS + generateRandomNumber() + "@mail.com";
    }

    @Test
    public void User_01_Register() {
        log.info("Register - Step 01: Open Register page");
        homePage.clickToHeaderLinkByClassName(driver, "register");
        registerPage = PageGeneratorManager.getUserRegisterPage(driver);

        log.info("Register - Step 02: Check to checkbox at option: " + UserTestData.Register.GENDER);
        registerPage.checkToCheckboxByOptionText(driver, UserTestData.Register.GENDER);

        log.info("Register - Step 03: Input to Firstname textbox with value: " + UserTestData.Register.FIRST_NAME);
        registerPage.inputToTextboxByID(driver, "FirstName", UserTestData.Register.FIRST_NAME);

        log.info("Register - Step 04: Input to Lastname textbox with value: " + UserTestData.Register.LAST_NAME);
        registerPage.inputToTextboxByID(driver, "LastName", UserTestData.Register.LAST_NAME);

        log.info("Register - Step 05: Select option in Date of birth dropdowns with values: " + UserTestData.Register.BIRTH_DAY + " " + UserTestData.Register.BIRTH_MONTH + ", " + UserTestData.Register.BIRTH_YEAR);
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthDay", UserTestData.Register.BIRTH_DAY);
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthMonth", UserTestData.Register.BIRTH_MONTH);
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthYear", UserTestData.Register.BIRTH_YEAR);

        log.info("Register - Step 06: Input to Email textbox with value: " + emailAddress);
        registerPage.inputToTextboxByID(driver, "Email", emailAddress);

        log.info("Register - Step 07: Input to Company name textbox with value: " + UserTestData.Register.COMPANY_NAME);
        registerPage.inputToTextboxByID(driver, "Company", UserTestData.Register.COMPANY_NAME);

        log.info("Register - Step 08: Input to Password textbox with value: " + UserTestData.Register.PASSWORD);
        registerPage.inputToTextboxByID(driver, "Password", UserTestData.Register.PASSWORD);

        log.info("Register - Step 09: Input to Confirm Password textbox with value: " + UserTestData.Register.PASSWORD);
        registerPage.inputToTextboxByID(driver, "ConfirmPassword", UserTestData.Register.PASSWORD);

        log.info("Register - Step 10: Click to Register button");
        registerPage.clickToButtonByText(driver, "Register");

        log.info("Register - Step 11: Verify register success message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        log.info("Register - Step 12: Click to Logout link");
        registerPage.clickToHeaderLinkByClassName(driver, "logout");
        homePage = PageGeneratorManager.getUserHomePage(driver);
    }

    @Test
    public void User_02_Login() {
        log.info("Login - Step 01: Open Login page");
        homePage.clickToHeaderLinkByClassName(driver, "login");
        loginPage = PageGeneratorManager.getUserLoginPage(driver);

        log.info("Login - Step 02: Input to Email textbox with value: " + emailAddress);
        loginPage.inputToTextboxByID(driver, "Email", emailAddress);

        log.info("Login - Step 03: Input to Password textbox with value: " + UserTestData.Register.PASSWORD);
        loginPage.inputToTextboxByID(driver, "Password", UserTestData.Register.PASSWORD);

        log.info("Login - Step 04: Click to Login button");
        loginPage.clickToButtonByText(driver, "Log in");
        homePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("Login - Step 05: Verify My Account link displayed");
        verifyTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_Customer_Info() {
        log.info("Customer-Info - Step 01: Click to My Account link");
        homePage.clickToHeaderLinkByClassName(driver, "account");
        customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);

        log.info("Customer-Info - Step 02: Verify Customer info page displayed");
        verifyEquals(customerInfoPage.getPageHeader(), "My account - Customer info");

        log.info("Customer-Info - Step 03: Verify gender checkbox is checked with value: " + UserTestData.Register.GENDER);
        verifyTrue(customerInfoPage.isCheckboxByOptionTextSelected(driver, UserTestData.Register.GENDER));

        log.info("Customer-Info - Step 04: Verify Firstname textbox has value: " + UserTestData.Register.FIRST_NAME);
        verifyEquals(customerInfoPage.getTextboxValue(driver, "FirstName"), UserTestData.Register.FIRST_NAME);

        log.info("Customer-Info - Step 05: Verify Lastname textbox has value: " + UserTestData.Register.LAST_NAME);
        verifyEquals(customerInfoPage.getTextboxValue(driver, "LastName"), UserTestData.Register.LAST_NAME);

        log.info("Customer-Info - Step 06: Verify Birth Day dropdown has value: " + UserTestData.Register.BIRTH_DAY);
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthDay"), UserTestData.Register.BIRTH_DAY);

        log.info("Customer-Info - Step 07: Verify Birth Month dropdown has value: " + UserTestData.Register.BIRTH_MONTH);
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthMonth"), UserTestData.Register.BIRTH_MONTH);

        log.info("Customer-Info - Step 08: Verify Birth Year dropdown has value: " + UserTestData.Register.BIRTH_YEAR);
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthYear"), UserTestData.Register.BIRTH_YEAR);

        log.info("Customer-Info - Step 09: Verify Email textbox has value: " + emailAddress);
        verifyEquals(customerInfoPage.getTextboxValue(driver, "Email"), emailAddress);
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
