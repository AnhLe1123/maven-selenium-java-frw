package pageUIs.nopCommerce.user;

public class UserBasePageUINopCommerce {
    public static final String ADDRESSES_SIDEBAR_LINK = "XPATH=//div[contains(@class, 'block-account-navigation')]//a[text()='Addresses']";
    public static final String ORDERS_SIDEBAR_LINK = "Xpath=//div[contains(@class, 'block-account-navigation')]//a[text()='Orders']";
    public static final String REWARD_POINTS_SIDEBAR_LINK = "XPath=//div[contains(@class, 'block-account-navigation')]//a[text()='Reward points']";
    public static final String CUSTOMER_INFO_SIDEBAR_LINK = "xpath=//div[contains(@class, 'block-account-navigation')]//a[text()='Customer info']";
    public static final String LOGOUT_LINK = "css=a[class='ico-logout']";
    public static final String SIDEBAR_LINK_BY_PAGE_NAME = "xpath=//div[contains(@class, 'block-account-navigation')]//a[text()='%s']";

    public static final String HEADER_LINK_BY_PARTIAL_CLASS_NAME = "xpath=//a[@class='ico-%s']";
    public static final String CHECKBOX_BY_TEXT_OPTION = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
    public static final String BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
}
