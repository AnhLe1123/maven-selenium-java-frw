package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.*;

import java.util.Random;

public class Level_08_Switch_Role extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserLoginPageObject userLoginPage;
    private UserOrdersPageObject userOrdersPage;
    private UserRegisterPageObject userRegisterPage;
    private UserAddressesPageObject userAddressesPage;
    private UserRewardPointsPageObject userRewardPointsPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
    private String firstName, lastName, userEmail, userPassword, adminEmail, adminPassword;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowserDriver(browserName, environmentName);
        firstName = "Automation";
        lastName = "Testing";
        userEmail = "autotest" + generateRandomNumber() + "@mail.com";
        userPassword = "123456";
        adminEmail = "admin@yourstore.com";
        adminPassword = "admin";

        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userRegisterPage = userHomePage.clickToRegisterLink();
        userRegisterPage.inputToFirstNameTextbox(firstName);
        userRegisterPage.inputToLastNameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(userEmail);
        userRegisterPage.inputToPasswordTextbox(userPassword);
        userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
        userRegisterPage.clickToRegisterButton();
        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
        userHomePage = userRegisterPage.clickUserLogoutLink(driver);
    }

    @Test
    public void Login_01_User_To_Admin() {
        userLoginPage = userHomePage.clickToLoginLink();
        userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
        userCustomerInfoPage = userHomePage.clickToMyAccountLink();
        userHomePage = userCustomerInfoPage.clickUserLogoutLink(driver);
        Assert.assertTrue(userHomePage.isHomePageSliderDisplayed());

        userHomePage.openPageUrl(driver, GlobalConstants.DEV_ADMIN_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
        adminLoginPage = adminDashboardPage.clickAdminLogoutLink(driver);
        Assert.assertTrue(adminLoginPage.isAdminLoginHeaderDisplayed());
    }

    @Test
    public void Login_02_Admin_To_User() {
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
        adminLoginPage = adminDashboardPage.clickAdminLogoutLink(driver);

        adminLoginPage.openPageUrl(driver, GlobalConstants.DEV_USER_PAGE_URL);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        Assert.assertTrue(userHomePage.isHomePageSliderDisplayed());
        userLoginPage = userHomePage.clickToLoginLink();
        userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
        userHomePage.clickUserLogoutLink(driver);
        Assert.assertTrue(userHomePage.isHomePageSliderDisplayed());
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
