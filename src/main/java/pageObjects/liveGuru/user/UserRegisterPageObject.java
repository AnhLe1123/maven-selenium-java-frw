package pageObjects.liveGuru.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToTextboxByFieldName(String fieldName, String value) {
        waitForElementVisible(driver, UserRegisterPageUI.TEXTBOX_BY_FIELD_NAME, fieldName);
        sendkeyToElement(driver, UserRegisterPageUI.TEXTBOX_BY_FIELD_NAME, value, fieldName);
    }

    public UserDashboardPageObject clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
        return UserPageGeneratorManager.getUserDashboardPage(driver);
    }
}
