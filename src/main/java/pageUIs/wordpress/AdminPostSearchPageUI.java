package pageUIs.wordpress;

public class AdminPostSearchPageUI {
    public static final String ADD_NEW_BUTTON = "xpath=//a[@class='page-title-action' and text()='Add New']";
    public static final String SEARCH_TEXTBOX = "xpath=//input[@id='post-search-input']";
    public static final String SEARCH_POSTS_BUTTON = "xpath=//input[@id='search-submit']";
    public static final String SEARCH_RESULTS_MESSAGE = "xpath=//span[@class='subtitle']";
    public static final String COLUMN_INDEX_BY_HEADER_ID = "xpath=//thead//th[@id='%s']/preceding-sibling::*";
    public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "xpath=//table[contains(@class, 'table-view-list')]//tbody//tr/*[%s]";
    public static final String POST_TITLE_AT_TABLE = "xpath=//a[@class='row-title' and text()='%s']";
    public static final String CHECKBOX_BY_POST_TITLE = "xpath=//th//label[contains(text(),'%s')]/following-sibling::input";
    public static final String BULK_ACTIONS_DROPDOWN = "xpath=//select[@name='action']";
    public static final String APPLY_BUTTON = "xpath=//input[@id='doaction']";
    public static final String ACTION_MESSAGE_BY_TEXT = "xpath=//div[@id='message']//p[contains(text(),'%s')]";
    public static final String TABLE_ITEM_BY_TEXT = "xpath=//tr[@class='no-items']//td[text()='%s']";
}
