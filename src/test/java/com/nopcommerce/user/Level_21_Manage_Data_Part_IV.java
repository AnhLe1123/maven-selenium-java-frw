package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import data.nopcommerce.UserDataMapper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_21_Manage_Data_Part_IV extends BaseTest {
    private WebDriver driver;
    private UserDataMapper userData;
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
        userData = UserDataMapper.getUserData();
        emailAddress = userData.getEmailAddress() + generateRandomNumber() + "@mail.com";
    }

    @Test
    public void User_01_Register() {
        log.info("Register - Step 01: Open Register page");
        homePage.clickToHeaderLinkByClassName(driver, "register");
        registerPage = PageGeneratorManager.getUserRegisterPage(driver);

        log.info("Register - Step 02: Check to checkbox at option: " + userData.getGender());
        registerPage.checkToCheckboxByOptionText(driver, userData.getGender());

        log.info("Register - Step 03: Input to Firstname textbox with value: " + userData.getFirstName());
        registerPage.inputToTextboxByID(driver, "FirstName", userData.getFirstName());

        log.info("Register - Step 04: Input to Lastname textbox with value: " + userData.getLastName());
        registerPage.inputToTextboxByID(driver, "LastName", userData.getLastName());

        log.info("Register - Step 05: Select option in Date of birth dropdowns with values: " + userData.getBirthDay() + " " + userData.getBirthMonth() + ", " + userData.getBirthYear());
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthDay", userData.getBirthDay());
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthMonth", userData.getBirthMonth());
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthYear", userData.getBirthYear());

        log.info("Register - Step 06: Input to Email textbox with value: " + emailAddress);
        registerPage.inputToTextboxByID(driver, "Email", emailAddress);

        log.info("Register - Step 07: Input to Company name textbox with value: " + userData.getCompanyName());
        registerPage.inputToTextboxByID(driver, "Company", userData.getCompanyName());

        log.info("Register - Step 08: Input to Password textbox with value: " + userData.getPassword());
        registerPage.inputToTextboxByID(driver, "Password", userData.getPassword());

        log.info("Register - Step 09: Input to Confirm Password textbox with value: " + userData.getPassword());
        registerPage.inputToTextboxByID(driver, "ConfirmPassword", userData.getPassword());

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

        log.info("Login - Step 03: Input to Password textbox with value: " + userData.getPassword());
        loginPage.inputToTextboxByID(driver, "Password", userData.getPassword());

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

        log.info("Customer-Info - Step 03: Verify gender checkbox is checked with value: " + userData.getGender());
        verifyTrue(customerInfoPage.isCheckboxByOptionTextSelected(driver, userData.getGender()));

        log.info("Customer-Info - Step 04: Verify Firstname textbox has value: " + userData.getFirstName());
        verifyEquals(customerInfoPage.getTextboxValue(driver, "FirstName"), userData.getFirstName());

        log.info("Customer-Info - Step 05: Verify Lastname textbox has value: " + userData.getLastName());
        verifyEquals(customerInfoPage.getTextboxValue(driver, "LastName"), userData.getLastName());

        log.info("Customer-Info - Step 06: Verify Birth Day dropdown has value: " + userData.getBirthDay());
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthDay"), userData.getBirthDay());

        log.info("Customer-Info - Step 07: Verify Birth Month dropdown has value: " + userData.getBirthMonth());
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthMonth"), userData.getBirthMonth());

        log.info("Customer-Info - Step 08: Verify Birth Year dropdown has value: " + userData.getBirthYear());
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthYear"), userData.getBirthYear());

        log.info("Customer-Info - Step 09: Verify Email textbox has value: " + emailAddress);
        verifyEquals(customerInfoPage.getTextboxValue(driver, "Email"), emailAddress);
    }

    @Test
    public void User_04_Test_Get_JSON_Data() {
        System.out.println(userData.getSubjects().get(0).getName());
        System.out.println(userData.getSubjects().get(0).getPoint());
        System.out.println(userData.getSubjects().get(1).getName());
        System.out.println(userData.getSubjects().get(1).getPoint());
        System.out.println(userData.getSubjects().get(2).getName());
        System.out.println(userData.getSubjects().get(2).getPoint());

        System.out.println(userData.getLoginUsername());
        System.out.println(userData.getLoginPassword());
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
