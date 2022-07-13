package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminPostSearchPageUI;
import pageUIs.wordpress.PageGeneratorManager;

public class AdminPostSearchPO extends BasePage {
    private WebDriver driver;

    public AdminPostSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminPostAddNewPO clickAddNewButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }

    public void inputToSearchTextbox(String postTitle) {
        waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
        sendkeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
    }

    public void clickSearchPostsButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
    }

    public String getSearchResultsMessage() {
        waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_RESULTS_MESSAGE);
        return getWebElementText(driver, AdminPostSearchPageUI.SEARCH_RESULTS_MESSAGE);
    }

    public String getValueByHeaderId(String headerId) {
        int columnIndex = getWebElementSize(driver, AdminPostSearchPageUI.COLUMN_INDEX_BY_HEADER_ID, headerId) + 1;
        waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex));
        return getWebElementText(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex));
    }

    public AdminPostAddNewPO clickPostTitle(String postTitle) {
        waitForElementClickable(driver, AdminPostSearchPageUI.POST_TITLE_AT_TABLE, postTitle);
        clickToElement(driver, AdminPostSearchPageUI.POST_TITLE_AT_TABLE, postTitle);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }

    public void checkToCheckboxByPostTitle(String editPostTitle) {
        waitForElementClickable(driver, AdminPostSearchPageUI.CHECKBOX_BY_POST_TITLE, editPostTitle);
        checkToCheckboxOrRadio(driver, AdminPostSearchPageUI.CHECKBOX_BY_POST_TITLE, editPostTitle);
    }

    public void selectOptionInActionDropdown(String optionText) {
        waitForElementClickable(driver, AdminPostSearchPageUI.BULK_ACTIONS_DROPDOWN);
        selectItemInDefaultDropdown(driver, AdminPostSearchPageUI.BULK_ACTIONS_DROPDOWN, optionText);
    }

    public void clickApplyButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
    }

    public boolean isActionMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPostSearchPageUI.ACTION_MESSAGE_BY_TEXT, message);
        return isElementDisplayed(driver, AdminPostSearchPageUI.ACTION_MESSAGE_BY_TEXT, message);
    }

    public boolean isTableItemDisplayed(String itemText) {
        waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ITEM_BY_TEXT, itemText);
        return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ITEM_BY_TEXT, itemText);
    }
}
