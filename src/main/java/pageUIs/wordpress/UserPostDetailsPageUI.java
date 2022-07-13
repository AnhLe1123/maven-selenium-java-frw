package pageUIs.wordpress;

public class UserPostDetailsPageUI {
    public static final String POST_TITLE_BY_TEXT = "xpath=//h1[@class='entry-title' and text()='%s']";
    public static final String POST_DATE_BY_TITLE = "xpath=//h1[@class='entry-title' and text()='%s']/following-sibling::div//time[text()='%s']";
    public static final String POST_AUTHOR_BY_TITLE = "xpath=//h1[@class='entry-title' and text()='%s']/following-sibling::div//span[@class='author vcard']//a[text()='%s']";
    public static final String POST_CONTENT_BY_TITLE = "xpath=//h1[@class='entry-title' and text()='%s']/parent::header/following-sibling::div//p[text()='%s']";
}
