package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_PageObject_I_Register {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private String projectPath = System.getProperty("user.dir");
    private String firstName, lastName, validEmailAddress, validPassword, invalidEmail, invalidPassword;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        firstName = "Automation";
        lastName = "Testing";
        validEmailAddress = "autotest" + generateNumber() + "@mail.com";
        validPassword = "123456";
        invalidEmail = "abc@";
        invalidPassword = "123";

        driver.get("https://demo.nopcommerce.com/");
        homePage = new UserHomePageObject(driver);
    }

    @Test
    public void Register_01_Empty_Data() {
        System.out.println("Register_01 - Step 01: Click to Register link");
        homePage.clickToRegisterLink();
        registerPage = new UserRegisterPageObject(driver);

        System.out.println("Register_01 - Step 02: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_01 - Step 03: Verify error messages displayed");
        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        System.out.println("Register_02 - Step 01: Click to Register link");
        registerPage.clickToRegisterLink();
        registerPage = new UserRegisterPageObject(driver);

        System.out.println("Register_02 - Step 02: Input to Email textbox with value: " + invalidEmail);
        registerPage.inputToEmailTextbox(invalidEmail);

        System.out.println("Register_02 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_02 - Step 04: Verify error messages displayed");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
    }

    @Test
    public void Register_03_With_Valid_Info() {
        System.out.println("Register_03 - Step 01: Click to Register link");
        registerPage.clickToRegisterLink();
        registerPage = new UserRegisterPageObject(driver);

        System.out.println("Register_03 - Step 02: Input to Firstname textbox with value: " + firstName);
        registerPage.inputToFirstNameTextbox(firstName);

        System.out.println("Register_03 - Step 03: Input to LastName textbox with value: " + lastName);
        registerPage.inputToLastNameTextbox(lastName);

        System.out.println("Register_03 - Step 04: Input to Email textbox with value: " + validEmailAddress);
        registerPage.inputToEmailTextbox(validEmailAddress);

        System.out.println("Register_03 - Step 05: Input to Password textbox with value: " + validPassword);
        registerPage.inputToPasswordTextbox(validPassword);

        System.out.println("Register_03 - Step 06: Input to ConfirmPassword textbox with value: " + validPassword);
        registerPage.inputToConfirmPasswordTextbox(validPassword);

        System.out.println("Register_03 - Step 07: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_03 - Step 08: Verify register success messages displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        System.out.println("Register_03 - Step 08: Click to Logout link");
        registerPage.clickUserLogoutLink(driver);
        homePage = new UserHomePageObject(driver);
    }

    @Test
    public void Register_04_Existing_Email() {
        System.out.println("Register_04 - Step 01: Click to Register link");
        homePage.clickToRegisterLink();
        registerPage = new UserRegisterPageObject(driver);

        System.out.println("Register_04 - Step 02: Input to Firstname textbox with value: " + firstName);
        registerPage.inputToFirstNameTextbox(firstName);

        System.out.println("Register_04 - Step 03: Input to LastName textbox with value: " + lastName);
        registerPage.inputToLastNameTextbox(lastName);

        System.out.println("Register_04 - Step 04: Input to Email textbox with value: " + validEmailAddress);
        registerPage.inputToEmailTextbox(validEmailAddress);

        System.out.println("Register_04 - Step 05: Input to Password textbox with value: " + validPassword);
        registerPage.inputToPasswordTextbox(validPassword);

        System.out.println("Register_04 - Step 06: Input to ConfirmPassword textbox with value: " + validPassword);
        registerPage.inputToConfirmPasswordTextbox(validPassword);

        System.out.println("Register_04 - Step 07: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_04 - Step 08: Verify existing email error messages displayed");
        Assert.assertEquals(registerPage.getExistingEmailErrorMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_Password_Less_Than_6_Chars() {
        System.out.println("Register_05 - Step 01: Click to Register link");
        registerPage.clickToRegisterLink();
        registerPage = new UserRegisterPageObject(driver);

        System.out.println("Register_05 - Step 02: Input to Password textbox with value: " + invalidPassword);
        registerPage.inputToPasswordTextbox(invalidPassword);

        System.out.println("Register_05 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_05 - Step 04: Verify error messages displayed");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void Register_06_Unmatched_Confirm_Password() {
        System.out.println("Register_06 - Step 01: Click to Register link");
        registerPage.clickToRegisterLink();
        registerPage = new UserRegisterPageObject(driver);

        System.out.println("Register_06 - Step 02: Input to Password textbox with value: " + validPassword);
        registerPage.inputToPasswordTextbox(validPassword);

        System.out.println("Register_06 - Step 03: Input to Confirm Password textbox with value: 654321");
        registerPage.inputToConfirmPasswordTextbox("654321");

        System.out.println("Register_06 - Step 04: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_06 - Step 05: Verify error messages displayed");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
