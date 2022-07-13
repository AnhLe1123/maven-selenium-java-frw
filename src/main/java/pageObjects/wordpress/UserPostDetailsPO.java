package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserPostDetailsPageUI;
import pageUIs.wordpress.UserPostSearchPageUI;

public class UserPostDetailsPO extends BasePage {
    private WebDriver driver;

    public UserPostDetailsPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPostTitleDisplayed(String postTitle) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_TITLE_BY_TEXT, postTitle);
        return isElementDisplayed(driver, UserPostDetailsPageUI.POST_TITLE_BY_TEXT, postTitle);
    }

    public boolean isPostDateByTitleDisplayed(String postTitle, String postDate) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_DATE_BY_TITLE, postTitle, postDate);
        return isElementDisplayed(driver, UserPostDetailsPageUI.POST_DATE_BY_TITLE, postTitle, postDate);
    }

    public boolean isPostAuthorByTitleDisplayed(String postTitle, String authorNickname) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_AUTHOR_BY_TITLE, postTitle, authorNickname);
        return isElementDisplayed(driver, UserPostDetailsPageUI.POST_AUTHOR_BY_TITLE, postTitle, authorNickname);
    }

    public boolean isPostContentByTitleDisplayed(String postTitle, String postBody) {
        waitForElementVisible(driver, UserPostDetailsPageUI.POST_CONTENT_BY_TITLE, postTitle, postBody);
        return isElementDisplayed(driver, UserPostDetailsPageUI.POST_CONTENT_BY_TITLE, postTitle, postBody);
    }

    public boolean isTitleDisplayed(String pageTitle) {
        waitForElementVisible(driver, UserPostSearchPageUI.PAGE_TITLE_BY_TEXT, pageTitle);
        return isElementDisplayed(driver, UserPostSearchPageUI.PAGE_TITLE_BY_TEXT, pageTitle);
    }
}
