package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

import java.util.Random;

public class Level_09_Dynamic_Locator extends BaseTest {
    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserOrdersPageObject ordersPage;
    private UserRegisterPageObject registerPage;
    private UserAddressesPageObject addressesPage;
    private UserRewardPointsPageObject rewardPointsPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private String firstName, lastName, emailAddress, password;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@mail.com";
        password = "123456";
    }

    @Test
    public void User_01_Register_Login() {
        registerPage = homePage.clickToRegisterLink();
        registerPage.inputToFirstNameTextbox(firstName);
        registerPage.inputToLastNameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        homePage = registerPage.clickUserLogoutLink(driver);

        loginPage = homePage.clickToLoginLink();
        loginPage.inputToEmailTextbox(emailAddress);
        loginPage.inputToPasswordTextbox(password);
        homePage = loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
        customerInfoPage = homePage.clickToMyAccountLink();
        Assert.assertEquals(customerInfoPage.getPageHeader(), "My account - Customer info");
    }

    @Test
    public void User_02_Switch_Page() {
        addressesPage = customerInfoPage.openAddressesPage(driver);
        ordersPage = addressesPage.openOrdersPage(driver);
        rewardPointsPage = ordersPage.openRewardPointsPage(driver);
        addressesPage = rewardPointsPage.openAddressesPage(driver);
        customerInfoPage = addressesPage.openCustomerInfoPage(driver);
        rewardPointsPage = customerInfoPage.openRewardPointsPage(driver);
        ordersPage = rewardPointsPage.openOrdersPage(driver);
        customerInfoPage = rewardPointsPage.openCustomerInfoPage(driver);
    }

    @Test
    public void User_03_Dynamic_Locator_I() {
        addressesPage = (UserAddressesPageObject) customerInfoPage.openPageByName(driver, "Addresses");
        ordersPage = (UserOrdersPageObject) addressesPage.openPageByName(driver, "Orders");
        rewardPointsPage = (UserRewardPointsPageObject) ordersPage.openPageByName(driver, "Reward points");
        addressesPage = (UserAddressesPageObject) rewardPointsPage.openPageByName(driver, "Addresses");
        customerInfoPage = (UserCustomerInfoPageObject) addressesPage.openPageByName(driver, "Customer info");
        rewardPointsPage = (UserRewardPointsPageObject) customerInfoPage.openPageByName(driver, "Reward points");
        ordersPage = (UserOrdersPageObject) rewardPointsPage.openPageByName(driver, "Orders");
        customerInfoPage = (UserCustomerInfoPageObject) rewardPointsPage.openPageByName(driver, "Customer info");
    }

    @Test
    public void User_03_Dynamic_Locator_II_Recommended() {
        customerInfoPage.openPageAtSidebarByName(driver, "Addresses");
        addressesPage = PageGeneratorManager.getUserAddressesPage(driver);

        addressesPage.openPageAtSidebarByName(driver, "Orders");
        ordersPage = PageGeneratorManager.getUserOrdersPage(driver);

        ordersPage.openPageAtSidebarByName(driver, "Reward points");
        rewardPointsPage = PageGeneratorManager.getUserRewardPointsPage(driver);

        rewardPointsPage.openPageAtSidebarByName(driver, "Addresses");
        addressesPage = PageGeneratorManager.getUserAddressesPage(driver);

        addressesPage.openPageAtSidebarByName(driver, "Customer info");
        customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);

        customerInfoPage.openPageAtSidebarByName(driver, "Reward points");
        rewardPointsPage = PageGeneratorManager.getUserRewardPointsPage(driver);

        rewardPointsPage.openPageAtSidebarByName(driver, "Orders");
        ordersPage = PageGeneratorManager.getUserOrdersPage(driver);

        rewardPointsPage.openPageAtSidebarByName(driver, "Customer info");
        customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
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
