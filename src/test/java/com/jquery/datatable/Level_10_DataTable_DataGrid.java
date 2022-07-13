package com.jquery.datatable;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

import java.util.Random;
import java.util.Set;

public class Level_10_DataTable_DataGrid extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private Set<String> actualCountries;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String pageUrl) {
        driver = getBrowserDriver(browserName, pageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Table_01_Paging() {
        homePage.openPagingByPageNumber("5");
        Assert.assertTrue(homePage.isActivePageByNumber("5"));
        homePage.sleepInSecond(1);

        homePage.openPagingByPageNumber("10");
        Assert.assertTrue(homePage.isActivePageByNumber("10"));
        homePage.sleepInSecond(1);

        homePage.openPagingByPageNumber("15");
        Assert.assertTrue(homePage.isActivePageByNumber("15"));
        homePage.sleepInSecond(1);

        homePage.openPagingByPageNumber("20");
        Assert.assertTrue(homePage.isActivePageByNumber("20"));
        homePage.sleepInSecond(1);
    }

    @Test
    public void Table_02_Enter_To_Header() {
        homePage.refreshCurrentPage(driver);
        homePage.inputToHeaderTextboxByField("Country", "Argentina");
        homePage.inputToHeaderTextboxByField("Females", "338282");
        homePage.inputToHeaderTextboxByField("Males", "349238");
        homePage.inputToHeaderTextboxByField("Total", "687522");
        homePage.sleepInSecond(2);
    }

    @Test
    public void Table_03_Get_Table_Values() {
        homePage.refreshCurrentPage(driver);
        actualCountries = homePage.getValuesAtColumnByKey("country");
    }

    @Test
    public void Table_04_Input_Value_To_Table() {
        homePage.openPageUrl(driver, GlobalConstants.JQUERY_PAGE_URL);
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.inputToTextboxAtColumnByRow("Album", "1", "Adele 30");
        homePage.inputToTextboxAtColumnByRow("Artist", "1", "Adele");
        homePage.inputToTextboxAtColumnByRow("Year", "1", "2021");
        homePage.inputToTextboxAtColumnByRow("Price", "1", "150");
        homePage.selectDropdownAtColumnByRow("Origin", "1", "Japan");
        homePage.checkToCheckboxAtColumnByRow("With Poster?", "1");
        homePage.sleepInSecond(2);
    }

    @Test
    public void Table_05_Textbox_Dropdown_Checkbox_At_Table() {
        homePage.refreshCurrentPage(driver);
        homePage.clickToLoadDataButton();
        homePage.inputToTextboxAtColumnByRow("Album", "2", "Into the New World");
        homePage.inputToTextboxAtColumnByRow("Artist", "3", "Girl generation");
        homePage.inputToTextboxAtColumnByRow("Year", "5", "2020");
        homePage.inputToTextboxAtColumnByRow("Price", "4", "300");

        homePage.selectDropdownAtColumnByRow("Origin", "1", "Korea");
        homePage.selectDropdownAtColumnByRow("Origin", "3", "US");
        homePage.selectDropdownAtColumnByRow("Origin", "5", "Others");

        homePage.uncheckToCheckboxAtColumnByRow("With Poster?", "1");
        homePage.uncheckToCheckboxAtColumnByRow("With Poster?", "2");
        homePage.uncheckToCheckboxAtColumnByRow("With Poster?", "4");

        homePage.checkToCheckboxAtColumnByRow("With Poster?", "3");
        homePage.checkToCheckboxAtColumnByRow("With Poster?", "5");
        homePage.sleepInSecond(2);
    }

    @Test
    public void Table_06_Click_Icons_At_Table() {
        homePage.clickToLoadDataButton();
        homePage.clickToIconByTitleAndRow("Remove Current Row", "1");
        homePage.clickToIconByTitleAndRow("Insert Row Above", "1");
        homePage.clickToIconByTitleAndRow("Move Up", "2");
        homePage.clickToIconByTitleAndRow("Move Down", "1");

        homePage.clickToIconByTitleAndRow("Remove Current Row", "5");
        homePage.clickToIconByTitleAndRow("Remove Current Row", "4");
        homePage.clickToIconByTitleAndRow("Remove Current Row", "3");
        homePage.clickToIconByTitleAndRow("Remove Current Row", "2");
        homePage.clickToIconByTitleAndRow("Remove Current Row", "1");
        homePage.sleepInSecond(2);
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
