package pageUIs.wordpress;

public class AdminPostAddNewPageUI {
    public static final String WELCOME_POPUP = "xpath=//picture[@class='edit-post-welcome-guide__image']";
    public static final String CLOSE_POPUP_BUTTON = "xpath=//button[@aria-label='Close dialog']";
    public static final String TITLE_TEXTBOX = "xpath=//h1[contains(@class,'editor-post-title')]";
    public static final String BODY_BUTTON = "xpath=//p[@aria-label='Add block']";
    public static final String BODY_TEXTBOX = "xpath=//p[contains(@class,'block-editor-rich-text')]";
    public static final String PUBLISH_OR_UPDATE_BUTTON = "xpath=//button[contains(@class,'editor-post-publish-button')]";
    public static final String PRE_PUBLISH_BUTTON = "xpath=//div[@class='editor-post-publish-panel']//button[text()='Publish']";
    public static final String POST_PUBLISHED_OR_UPDATED_MESSAGE = "xpath=//div[@class='components-snackbar__content' and text()='%s']";
}
