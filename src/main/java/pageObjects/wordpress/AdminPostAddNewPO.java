package pageObjects.wordpress;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.wordpress.AdminPostAddNewPageUI;

import java.util.List;

public class AdminPostAddNewPO extends BasePage {
    private WebDriver driver;

    public AdminPostAddNewPO(WebDriver driver) {
        this.driver = driver;
    }

    public void closeWelcomePopup() {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> popup = getListWebElement(driver, AdminPostAddNewPageUI.WELCOME_POPUP);
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (popup.size() > 0 && popup.get(0).isDisplayed()) {
            clickToElement(driver, AdminPostAddNewPageUI.CLOSE_POPUP_BUTTON);
        }
    }

    public void inputToPostTitleTextbox(String postTitle) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX, postTitle);
    }

    public void inputToPostBodyTextbox(String postBody) {
        waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.BODY_BUTTON);

        waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
    }

    public void clickPublishOrUpdateButton() {
        waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
    }

    public void clickPrePublishButton() {
        waitForElementClickable(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
    }

    public boolean isPostPublishedOrUpdatedMessageDisplayed(String messageText) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.POST_PUBLISHED_OR_UPDATED_MESSAGE, messageText);
        return isElementDisplayed(driver, AdminPostAddNewPageUI.POST_PUBLISHED_OR_UPDATED_MESSAGE, messageText);
    }

    public void editPostBodyTextbox(String editPostBody) {
        waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        clickToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);

        waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        clearValueInElementByPressKey(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, editPostBody);
    }
}
