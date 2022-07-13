package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_Apply_BasePage_III extends BasePage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        emailAddress = "autotest" + generateNumber() + "@mail.com";
        driver.manage().window().maximize();
        openPageUrl(driver, "https://demo.nopcommerce.com/");
    }

    @Test
    public void User_01_Register_With_Empty_Data() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        waitForElementVisible(driver, "//span[@id='FirstName-error']");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void User_02_Register_With_Invalid_Email() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementVisible(driver, "//input[@id='Email']");
        sendkeyToElement(driver, "//input[@id='Email']", "abc@");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getWebElementText(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void User_03_Register_With_Valid_Info() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementVisible(driver, "//input[@id='FirstName']");
        sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
        sendkeyToElement(driver, "//input[@id='LastName']", "Testing");
        sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendkeyToElement(driver, "//input[@id='Password']", "123456");
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        waitForElementVisible(driver, "//div[@class='result']");
        Assert.assertEquals(getWebElementText(driver, "//div[@class='result']"), "Your registration completed");
        clickToElement(driver, "//a[@class='ico-logout']");
    }

    @Test
    public void User_04_Register_With_Existing_Email() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementVisible(driver, "//input[@id='FirstName']");
        sendkeyToElement(driver, "//input[@id='FirstName']", "New Automation");
        sendkeyToElement(driver, "//input[@id='LastName']", "Testing");
        sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendkeyToElement(driver, "//input[@id='Password']", "123456");
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getWebElementText(driver, "//div[contains(@class, 'message-error')]"), "The specified email already exists");
    }

    @Test
    public void User_05_Register_With_Password_Less_Than_6_Chars() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementVisible(driver, "//input[@id='Password']");
        sendkeyToElement(driver, "//input[@id='Password']", "123");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getWebElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void User_06_Register_With_Unmatched_Confirm_Password() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementVisible(driver, "//input[@id='Password']");
        sendkeyToElement(driver, "//input[@id='Password']", "123456");
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "654321");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
