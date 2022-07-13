package com.liveguru.datatable;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.liveGuru.admin.AdminLoginPageObject;
import pageObjects.liveGuru.admin.AdminManageCustomersPageObject;
import pageObjects.liveGuru.admin.AdminPageGeneratorManager;
import pageObjects.liveGuru.user.UserDashboardPageObject;
import pageObjects.liveGuru.user.UserHomePageObject;
import pageObjects.liveGuru.user.UserPageGeneratorManager;
import pageObjects.liveGuru.user.UserRegisterPageObject;

import java.util.Random;

public class Level_10_DataTable_LiveGuru extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserDashboardPageObject userDashboardPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminManageCustomersPageObject adminManageCustomersPage;
    String userFirstName, userLastName, userFullName, userEmail, userPassword, adminUserName, adminPassword;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        driver = getBrowserDriver(browserName, pageUrl);
        userHomePage = UserPageGeneratorManager.getUserHomePage(driver);

        userFirstName = "Andy";
        userLastName = "Lee";
        userFullName = userFirstName + " " + userLastName;
        userEmail = "andylee" + generateNumber() + "@mail.com";
        userPassword = "123456";
        adminUserName = "user01";
        adminPassword = "guru99com";
    }

    @Test
    public void User_01_Register_From_User_To_Admin() {
        userHomePage.clickToSubLinkByText(driver, "Account", "Register");
        userRegisterPage = UserPageGeneratorManager.getUserRegisterPage(driver);
        userRegisterPage.inputToTextboxByFieldName("First Name", userFirstName);
        userRegisterPage.inputToTextboxByFieldName("Last Name", userLastName);
        userRegisterPage.inputToTextboxByFieldName("Email Address", userEmail);
        userRegisterPage.inputToTextboxByFieldName("Password", userPassword);
        userRegisterPage.inputToTextboxByFieldName("Confirm Password", userPassword);
        userDashboardPage = userRegisterPage.clickToRegisterButton();
        Assert.assertEquals(userDashboardPage.getRegisterSuccessMessage(), "Thank you for registering with Main Website Store.");
        Assert.assertTrue(userDashboardPage.isMyDashboardHeaderDisplayed());
        Assert.assertTrue(userDashboardPage.isContactInformationContains(userFullName));
        Assert.assertTrue(userDashboardPage.isContactInformationContains(userEmail));
        userDashboardPage.clickToSubLinkByText(driver, "Account", "Log Out");
        userHomePage = UserPageGeneratorManager.getUserHomePage(driver);

        userDashboardPage.openPageUrl(driver, GlobalConstants.LIVEGURU_ADMIN_PAGE_URL);
        adminLoginPage = AdminPageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.sleepInSecond(1);
        adminLoginPage.inputToUserNameTextbox(adminUserName);
        adminLoginPage.inputToPasswordTextbox(adminPassword);
        adminManageCustomersPage = adminLoginPage.clickToLoginButton();
        adminManageCustomersPage.closeIncommingMessagePopup();
        adminManageCustomersPage.inputToEmailTextboxAtHeader(userEmail);
        adminManageCustomersPage.clickToSearchButton();
        Assert.assertTrue(adminManageCustomersPage.isCustomerInfoDisplayed(userFullName, userEmail));
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
