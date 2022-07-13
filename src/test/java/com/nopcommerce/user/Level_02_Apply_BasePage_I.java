package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_Apply_BasePage_I {
    WebDriver driver;
    BasePage basePage;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        basePage = new BasePage();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        emailAddress = "autotest" + generateNumber() + "@mail.com";
        driver.manage().window().maximize();
        basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
    }

    @Test
    public void User_01_Register_With_Empty_Data() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        basePage.waitForElementVisible(driver, "//span[@id='FirstName-error']");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void User_02_Register_With_Invalid_Email() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementVisible(driver, "//input[@id='Email']");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", "abc@");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void User_03_Register_With_Valid_Info() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementVisible(driver, "//input[@id='FirstName']");
        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Testing");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        basePage.waitForElementVisible(driver, "//div[@class='result']");
        Assert.assertEquals(basePage.getWebElementText(driver, "//div[@class='result']"), "Your registration completed");
        basePage.clickToElement(driver, "//a[@class='ico-logout']");
    }

    @Test
    public void User_04_Register_With_Existing_Email() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementVisible(driver, "//input[@id='FirstName']");
        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "New Automation");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Testing");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getWebElementText(driver, "//div[contains(@class, 'message-error')]"), "The specified email already exists");
    }

    @Test
    public void User_05_Register_With_Password_Less_Than_6_Chars() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementVisible(driver, "//input[@id='Password']");
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void User_06_Register_With_Unmatched_Confirm_Password() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementVisible(driver, "//input[@id='Password']");
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "654321");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
