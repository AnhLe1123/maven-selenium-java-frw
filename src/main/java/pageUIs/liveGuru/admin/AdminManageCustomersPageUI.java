package pageUIs.liveGuru.admin;

public class AdminManageCustomersPageUI {
    public static final String INCOMMING_MESSAGE_POPUP = "xpath=//div[@id='message-popup-window']";
    public static final String CLOSE_POPUP_ICON = "xpath=//div[@id='message-popup-window']//a[@title='close']";
    public static final String EMAIL_TEXTBOX_AT_HEADER = "xpath=//input[@id='customerGrid_filter_email']";
    public static final String SEARCH_BUTTON = "xpath=//span[text()='Search']";
    public static final String CUSTOMER_INFO_ROW_BY_FULLNAME_EMAIL = "xpath=//tr//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]";
}
