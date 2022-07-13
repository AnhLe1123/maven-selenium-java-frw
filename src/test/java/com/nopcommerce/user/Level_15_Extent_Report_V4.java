package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;

public class Level_15_Extent_Report_V4 extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private String firstName, lastName, emailAddress, password;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        driver = getBrowserDriver(browserName, pageUrl);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@mail.com";
        password = "123456";
    }

    @Test
    public void User_01_Register() {
        registerPage = homePage.clickToRegisterLink();
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");
    }

    @Test
    public void User_02_Login() {
        homePage = registerPage.clickUserLogoutLink(driver);
        loginPage = homePage.clickToLoginLink();
        loginPage.inputToEmailTextbox(emailAddress);
        loginPage.inputToPasswordTextbox(password);
        homePage = loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
        customerInfoPage = homePage.clickToMyAccountLink();
        Assert.assertEquals(customerInfoPage.getPageHeader(), "My account - Customer info");
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
