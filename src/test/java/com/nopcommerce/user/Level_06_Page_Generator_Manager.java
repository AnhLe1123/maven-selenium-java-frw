package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;

public class Level_06_Page_Generator_Manager extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;
    private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, validPassword, invalidPassword;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        firstName = "Automation";
        lastName = "Testing";
        invalidEmail = "abc@";
        existingEmail = "autotest" + generateRandomNumber() + "@mail.com";
        notFoundEmail = "autotest" + generateRandomNumber() + "@mail.net";
        validPassword = "123456";
        invalidPassword = "123";

        System.out.println("Pre-condition - Step 01: Click to Register link");
        registerPage = homePage.clickToRegisterLink();

        System.out.println("Pre-condition - Step 02: Input to Firstname textbox with value: " + firstName);
        registerPage.inputToFirstNameTextbox(firstName);

        System.out.println("Pre-condition - Step 03: Input to LastName textbox with value: " + lastName);
        registerPage.inputToLastNameTextbox(lastName);

        System.out.println("Pre-condition - Step 04: Input to Email textbox with value: " + existingEmail);
        registerPage.inputToEmailTextbox(existingEmail);

        System.out.println("Pre-condition - Step 05: Input to Password textbox with value: " + validPassword);
        registerPage.inputToPasswordTextbox(validPassword);

        System.out.println("Pre-condition - Step 06: Input to ConfirmPassword textbox with value: " + validPassword);
        registerPage.inputToConfirmPasswordTextbox(validPassword);

        System.out.println("Pre-condition - Step 07: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Pre-condition - Step 08: Verify register success messages displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        System.out.println("Pre-condition - Step 08: Click to Logout link");
        homePage = registerPage.clickUserLogoutLink(driver);
    }

    @Test
    public void Login_01_Empty_Data() {
        System.out.println("Login_01_Empty_Data - Step 01: Click to Login link");
        loginPage = homePage.clickToLoginLink();

        System.out.println("Login_01_Empty_Data - Step 02: Click to Login button");
        loginPage.clickToLoginButton();

        System.out.println("Login_01_Empty_Data - Step 03: Verify email error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
    }

    @Test
    public void Login_02_Invalid_Email() {
        System.out.println("Login_02_Invalid_Email - Step 01: Click to Login link");
        loginPage.clickToLoginLink();

        System.out.println("Login_02_Invalid_Email - Step 02: Input to Email textbox with value: " + invalidEmail);
        loginPage.inputToEmailTextbox(invalidEmail);

        System.out.println("Login_02_Invalid_Email - Step 03: Click to Login button");
        loginPage.clickToLoginButton();

        System.out.println("Login_02_Invalid_Email - Step 04: Verify email error message displayed");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
    }

    @Test
    public void Login_03_Email_Not_Registered() {
        System.out.println("Login_03_Email_Not_Registered - Step 01: Click to Login link");
        loginPage.clickToLoginLink();

        System.out.println("Login_03_Email_Not_Registered - Step 02: Input to Email textbox with value: " + notFoundEmail);
        loginPage.inputToEmailTextbox(notFoundEmail);

        System.out.println("Login_03_Email_Not_Registered - Step 04: Input to Password textbox with value: " + validPassword);
        loginPage.inputToPasswordTextbox(validPassword);

        System.out.println("Login_03_Email_Not_Registered - Step 05: Click to Login button");
        loginPage.clickToLoginButton();

        System.out.println("Login_03_Email_Not_Registered - Step 06: Verify unsuccessful error message displayed");
        Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void Login_04_Registered_Email_Without_Password() {
        System.out.println("Login_04_Registered_Email_Without_Password - Step 01: Click to Login link");
        loginPage.clickToLoginLink();

        System.out.println("Login_04_Registered_Email_Without_Password - Step 02: Input to Email textbox with value: " + existingEmail);
        loginPage.inputToEmailTextbox(existingEmail);

        System.out.println("Login_04_Registered_Email_Without_Password - Step 04: Input to Password textbox with empty value");
        loginPage.inputToPasswordTextbox("");

        System.out.println("Login_04_Registered_Email_Without_Password - Step 05: Click to Login button");
        loginPage.clickToLoginButton();

        System.out.println("Login_04_Registered_Email_Without_Password - Step 06: Verify unsuccessful error message displayed");
        Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_05_Registered_Email_With_Wrong_Password() {
        System.out.println("Login_05_Registered_Email_With_Wrong_Password - Step 01: Click to Login link");
        loginPage.clickToLoginLink();

        System.out.println("Login_05_Registered_Email_With_Wrong_Password - Step 02: Input to Email textbox with value: " + existingEmail);
        loginPage.inputToEmailTextbox(existingEmail);

        System.out.println("Login_05_Registered_Email_With_Wrong_Password - Step 04: Input to Password textbox with value: " + invalidPassword);
        loginPage.inputToPasswordTextbox(invalidPassword);

        System.out.println("Login_05_Registered_Email_With_Wrong_Password - Step 05: Click to Login button");
        loginPage.clickToLoginButton();

        System.out.println("Login_05_Registered_Email_With_Wrong_Password - Step 06: Verify unsuccessful error message displayed");
        Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_06_Registered_Email_With_Valid_Password() {
        System.out.println("Login_06_Registered_Email_With_Valid_Password - Step 01: Click to Login link");
        loginPage.clickToLoginLink();

        System.out.println("Login_06_Registered_Email_With_Valid_Password - Step 02: Input to Email textbox with value: " + existingEmail);
        loginPage.inputToEmailTextbox(existingEmail);

        System.out.println("Login_06_Registered_Email_With_Valid_Password - Step 04: Input to Password textbox with empty value: " + validPassword);
        loginPage.inputToPasswordTextbox(validPassword);

        System.out.println("Login_06_Registered_Email_With_Valid_Password - Step 05: Click to Login button");
        homePage = loginPage.clickToLoginButton();

        System.out.println("Login_06_Registered_Email_With_Valid_Password - Step 06: Verify My Account link displayed");
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
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
