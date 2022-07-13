package com.facebook;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private String emailAddress;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        driver = getBrowserDriver(browserName, pageUrl);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        emailAddress = "autoqa@mail.com";
    }

    @Test
    public void TC_01_Element_Displayed() {
        loginPage.clickCreateAccountButton();
        loginPage.inputToEmailTextbox(emailAddress);
        verifyTrue(loginPage.isConfirmEmailTextboxDisplayed());
    }

    @Test
    public void TC_02_Element_Undisplayed_In_DOM() {
        loginPage.inputToEmailTextbox("");
        loginPage.sleepInSecond(1);
        verifyTrue(loginPage.isConfirmEmailTextboxUndisplayed());
    }

    @Test
    public void TC_03_Element_Undisplayed_Not_In_DOM() {
        loginPage.clickCloseRegisterFormIcon();
        loginPage.sleepInSecond(1);
        verifyTrue(loginPage.isConfirmEmailTextboxUndisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
