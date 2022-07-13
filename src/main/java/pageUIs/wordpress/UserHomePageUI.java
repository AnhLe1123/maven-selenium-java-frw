package pageUIs.wordpress;

public class UserHomePageUI {
    public static final String POST_TITLE_BY_TEXT = "xpath=//article//h2[@class='entry-title']//a[text()='%s']";
    public static final String POST_DATE_BY_TITLE = "xpath=//article//h2[@class='entry-title']//a[text()='%s']/parent::h2/following-sibling::div//time[text()='%s']";
    public static final String POST_AUTHOR_BY_TITLE = "xpath=//article//h2[@class='entry-title']//a[text()='%s']/parent::h2/following-sibling::div//span[@class='author vcard']//a[text()='%s']";
    public static final String POST_CONTENT_BY_TITLE = "xpath=//article//h2[@class='entry-title']//a[text()='%s']/ancestor::header/following-sibling::div//p[text()='%s']";
    public static final String SEARCH_TEXTBOX = "xpath=//input[contains(@class,'wp-block-search')]";
    public static final String SEARCH_BUTTON = "xpath=//button[contains(@class,'wp-block-search')]";
}
