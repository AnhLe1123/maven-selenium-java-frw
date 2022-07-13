package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.PageGeneratorManager;
import pageUIs.wordpress.UserHomePageUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPostTitleDisplayed(String postTitle) {
        waitForElementVisible(driver, UserHomePageUI.POST_TITLE_BY_TEXT, postTitle);
        return isElementDisplayed(driver, UserHomePageUI.POST_TITLE_BY_TEXT, postTitle);
    }

    public boolean isPostDateByTitleDisplayed(String postTitle, String postDate) {
        waitForElementVisible(driver, UserHomePageUI.POST_DATE_BY_TITLE, postTitle, postDate);
        return isElementDisplayed(driver, UserHomePageUI.POST_DATE_BY_TITLE, postTitle, postDate);
    }

    public boolean isPostAuthorByTitleDisplayed(String postTitle, String authorNickname) {
        waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_BY_TITLE, postTitle, authorNickname);
        return isElementDisplayed(driver, UserHomePageUI.POST_AUTHOR_BY_TITLE, postTitle, authorNickname);
    }

    public boolean isPostContentByTitleDisplayed(String postTitle, String postBody) {
        waitForElementVisible(driver, UserHomePageUI.POST_CONTENT_BY_TITLE, postTitle, postBody);
        return isElementDisplayed(driver, UserHomePageUI.POST_CONTENT_BY_TITLE, postTitle, postBody);
    }

    public UserPostDetailsPO clickPostTitleLink(String postTitle) {
        waitForElementClickable(driver, UserHomePageUI.POST_TITLE_BY_TEXT, postTitle);
        clickToElement(driver, UserHomePageUI.POST_TITLE_BY_TEXT, postTitle);
        return PageGeneratorManager.getUserPostDetailsPage(driver);
    }

    public void inputToSearchTextbox(String postTitle) {
        waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX);
        sendkeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, postTitle);
    }

    public UserPostSearchPO clickSearchButton() {
        waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
        clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
        return PageGeneratorManager.getUserPostSearchPage(driver);
    }
}
