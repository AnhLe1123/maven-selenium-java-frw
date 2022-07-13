package com.jquery.uploadfile;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

import java.util.Random;

public class Level_11_Upload_Files extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    String avatarFileName = "avatar.jpg";
    String villageFileName = "village.png";
    String weatherFileName = "weather.png";
    String[] multipleFileNames = {avatarFileName, villageFileName, weatherFileName};

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        driver = getBrowserDriver(browserName, pageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Upload_01_One_File_Per_Time() {
        homePage.uploadMultipleFiles(driver, avatarFileName);
        Assert.assertTrue(homePage.isFileLoadedByName(avatarFileName));
        homePage.clickToStartButton();
        Assert.assertTrue(homePage.isFileLinkUploadedByName(avatarFileName));
        Assert.assertTrue(homePage.isFileImageUploadedByName(avatarFileName));
    }

    @Test
    public void Upload_02_Multiple_Files_Per_Time() {
        homePage.refreshCurrentPage(driver);
        homePage.uploadMultipleFiles(driver, multipleFileNames);

        Assert.assertTrue(homePage.isFileLoadedByName(avatarFileName));
        Assert.assertTrue(homePage.isFileLoadedByName(villageFileName));
        Assert.assertTrue(homePage.isFileLoadedByName(weatherFileName));

        homePage.clickToStartButton();

        Assert.assertTrue(homePage.isFileLinkUploadedByName(avatarFileName));
        Assert.assertTrue(homePage.isFileLinkUploadedByName(villageFileName));
        Assert.assertTrue(homePage.isFileLinkUploadedByName(weatherFileName));

        Assert.assertTrue(homePage.isFileImageUploadedByName(avatarFileName));
        Assert.assertTrue(homePage.isFileImageUploadedByName(villageFileName));
        Assert.assertTrue(homePage.isFileImageUploadedByName(weatherFileName));

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
