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
import utilities.DataHelper;

public class Level_21_Manage_Data_Part_I extends BaseTest {
    private WebDriver driver;
    private DataHelper fakeData;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private String firstName, lastName, emailAddress, password, gender, birthDay, birthMonth, birthYear, companyName;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        driver = getBrowserDriver(browserName, pageUrl);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        fakeData = DataHelper.getData();
        firstName = fakeData.getFirstName();
        lastName = fakeData.getLastName();
        emailAddress = fakeData.getEmailAddress();
        companyName = fakeData.getCompanyName();
        password = fakeData.getPassword();
        gender = "Male";
        birthDay = "25";
        birthMonth = "September";
        birthYear = "1992";
    }

    @Test
    public void User_01_Register() {
        log.info("Register - Step 01: Open Register page");
        homePage.clickToHeaderLinkByClassName(driver, "register");
        registerPage = PageGeneratorManager.getUserRegisterPage(driver);

        log.info("Register - Step 02: Check to checkbox at option: " + gender);
        registerPage.checkToCheckboxByOptionText(driver, gender);

        log.info("Register - Step 03: Input to Firstname textbox with value: " + firstName);
        registerPage.inputToTextboxByID(driver, "FirstName", firstName);

        log.info("Register - Step 04: Input to Lastname textbox with value: " + lastName);
        registerPage.inputToTextboxByID(driver, "LastName", lastName);

        log.info("Register - Step 05: Select option in Date of birth dropdowns with values: " + birthDay + " " + birthMonth + ", " + birthYear);
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthDay", birthDay);
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthMonth", birthMonth);
        registerPage.selectOptionInDropdownByName(driver, "DateOfBirthYear", birthYear);

        log.info("Register - Step 06: Input to Email textbox with value: " + emailAddress);
        registerPage.inputToTextboxByID(driver, "Email", emailAddress);

        log.info("Register - Step 07: Input to Company name textbox with value: " + companyName);
        registerPage.inputToTextboxByID(driver, "Company", companyName);

        log.info("Register - Step 08: Input to Password textbox with value: " + password);
        registerPage.inputToTextboxByID(driver, "Password", password);

        log.info("Register - Step 09: Input to Confirm Password textbox with value: " + password);
        registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

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

        log.info("Login - Step 03: Input to Password textbox with value: " + password);
        loginPage.inputToTextboxByID(driver, "Password", password);

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

        log.info("Customer-Info - Step 03: Verify gender checkbox is checked with value: " + gender);
        verifyTrue(customerInfoPage.isCheckboxByOptionTextSelected(driver, gender));

        log.info("Customer-Info - Step 04: Verify Firstname textbox has value: " + firstName);
        verifyEquals(customerInfoPage.getTextboxValue(driver, "FirstName"), firstName);

        log.info("Customer-Info - Step 05: Verify Lastname textbox has value: " + lastName);
        verifyEquals(customerInfoPage.getTextboxValue(driver, "LastName"), lastName);

        log.info("Customer-Info - Step 06: Verify Birth Day dropdown has value: " + birthDay);
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthDay"), birthDay);

        log.info("Customer-Info - Step 07: Verify Birth Month dropdown has value: " + birthMonth);
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthMonth"), birthMonth);

        log.info("Customer-Info - Step 08: Verify Birth Year dropdown has value: " + birthYear);
        verifyEquals(customerInfoPage.getSelectedOptionAtDropdownByName(driver, "DateOfBirthYear"), birthYear);

        log.info("Customer-Info - Step 09: Verify Email textbox has value: " + emailAddress);
        verifyEquals(customerInfoPage.getTextboxValue(driver, "Email"), emailAddress);
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
