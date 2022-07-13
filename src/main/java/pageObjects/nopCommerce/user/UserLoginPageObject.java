package pageObjects.nopCommerce.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    private WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Login button")
    public UserHomePageObject clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    @Step("Get email error message")
    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
        return getWebElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    @Step("Click to Login link")
    public void clickToLoginLink() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_LINK);
        clickToElement(driver, UserLoginPageUI.LOGIN_LINK);
    }

    @Step("Input to Email textbox with value: {0}")
    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
    }

    @Step("Input to Password textbox with value: {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Get unsuccessful login error message")
    public String getUnsuccessfulErrorMessage() {
        waitForElementVisible(driver, UserLoginPageUI.UNSUCCESS_ERROR_MESSAGE);
        return getWebElementText(driver, UserLoginPageUI.UNSUCCESS_ERROR_MESSAGE);
    }

    @Step("Login as User with email: {0} and password: {1}")
    public UserHomePageObject loginAsUser(String email, String password) {
        inputToEmailTextbox(email);
        inputToPasswordTextbox(password);
        return clickToLoginButton();
    }
}
