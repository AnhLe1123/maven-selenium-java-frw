package com.nopcommerce.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_01_Register_DRY {
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
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void User_01_Register_With_Empty_Data() {
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "Password is required.");
    }

    @Test
    public void User_02_Register_With_Invalid_Email() {
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("#Email")).sendKeys("abc@");
        driver.findElement(By.cssSelector("#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Wrong email");
    }

    @Test
    public void User_03_Register_With_Valid_Info() {
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("#LastName")).sendKeys("Testing");
        driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        driver.findElement(By.cssSelector(".ico-logout")).click();
    }

    @Test
    public void User_04_Register_With_Existing_Email() {
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("#FirstName")).sendKeys("New Automation");
        driver.findElement(By.cssSelector("#LastName")).sendKeys("Testing");
        driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
    }

    @Test
    public void User_05_Register_With_Password_Less_Than_6_Chars() {
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("#Password")).sendKeys("123");
        driver.findElement(By.cssSelector("#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void User_06_Register_With_Unmatched_Confirm_Password() {
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("654321");
        driver.findElement(By.cssSelector("#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
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
