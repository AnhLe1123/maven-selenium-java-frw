package pageObjects.jQuery.dataTable;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.dataTable.HomePageUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPagingByPageNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.PAGING_BY_PAGE_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.PAGING_BY_PAGE_NUMBER, pageNumber);
    }

    public boolean isActivePageByNumber(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.PAGING_ACTIVE_BY_PAGE_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.PAGING_ACTIVE_BY_PAGE_NUMBER, pageNumber);
    }

    public void inputToHeaderTextboxByField(String fieldName, String value) {
        waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_FIELD_NAME, fieldName);
        sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_FIELD_NAME, value, fieldName);
        pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_FIELD_NAME, Keys.ENTER, fieldName);
    }

    public Set<String> getValuesAtColumnByKey(String countryName) {
        waitForAllElementsVisible(driver, HomePageUI.PAGINATIONS);
        int pagesSize = getWebElementSize(driver, HomePageUI.PAGINATIONS);
        Set<String> valuesAtAllPages = new HashSet<>();

        for (int i = 1; i <= pagesSize; i++) {
            waitForElementClickable(driver, HomePageUI.PAGING_BY_INDEX, String.valueOf(i));
            clickToElement(driver, HomePageUI.PAGING_BY_INDEX, String.valueOf(i));

            List<WebElement> allValuesAtEachPage = getListWebElement(driver, HomePageUI.VALUES_AT_COLUMN_KEY, countryName);
            for (WebElement element: allValuesAtEachPage) {
                valuesAtAllPages.add(element.getText());
                System.out.println(element.getText());
            }
        }
        return valuesAtAllPages;
    }

    public void inputToTextboxAtColumnByRow(String columnName, String rowIndex, String value) {
        int columnIndex = getWebElementSize(driver, HomePageUI.ROW_INDEX_BY_NAME, columnName) + 1;
        waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_ROW_COLUMN_INDEX, value, rowIndex, String.valueOf(columnIndex));
    }

    public void selectDropdownAtColumnByRow(String columnName, String rowIndex, String textValue) {
        int columnIndex = getWebElementSize(driver, HomePageUI.ROW_INDEX_BY_NAME, columnName) + 1;
        waitForElementVisible(driver, HomePageUI.DROPDOWN_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_ROW_COLUMN_INDEX, textValue, rowIndex, String.valueOf(columnIndex));
    }

    public void checkToCheckboxAtColumnByRow(String columnName, String rowIndex) {
        int columnIndex = getWebElementSize(driver, HomePageUI.ROW_INDEX_BY_NAME, columnName) + 1;
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        checkToCheckboxOrRadio(driver, HomePageUI.CHECKBOX_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
    }

    public void uncheckToCheckboxAtColumnByRow(String columnName, String rowIndex) {
        int columnIndex = getWebElementSize(driver, HomePageUI.ROW_INDEX_BY_NAME, columnName) + 1;
        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        uncheckToCheckbox(driver, HomePageUI.CHECKBOX_BY_ROW_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
    }

    public void clickToLoadDataButton() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }

    public void clickToIconByTitleAndRow(String iconTitle, String rowIndex) {
        waitForElementClickable(driver, HomePageUI.BUTTON_AT_ROW_BY_TITLE, rowIndex, iconTitle);
        clickToElement(driver, HomePageUI.BUTTON_AT_ROW_BY_TITLE, rowIndex, iconTitle);
    }
}
