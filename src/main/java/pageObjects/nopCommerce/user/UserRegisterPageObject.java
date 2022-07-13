package pageObjects.nopCommerce.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Register button")
    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    @Step("Get FirstName error message")
    public String getFirstNameErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
        return getWebElementText(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    @Step("Get LastName error message")
    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
        return getWebElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    @Step("Get Email error message")
    public String getEmailErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getWebElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    @Step("Get Password error message")
    public String getPasswordErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getWebElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    @Step("Get Confirm Password error message")
    public String getConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getWebElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    @Step("Input to Email textbox with value: {0}")
    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Input to First Name textbox with value: {0}")
    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Input to Last Name textbox with value: {0}")
    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Input to Password textbox with value: {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Input to Confirm Password textbox with value: {0}")
    public void inputToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    @Step("Get Register success message")
    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getWebElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    @Step("Get error message of existing email")
    public String getExistingEmailErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
        return getWebElementText(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
    }

    @Step("Click to Register link")
    public void clickToRegisterLink() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_LINK);
        clickToElement(driver, UserRegisterPageUI.REGISTER_LINK);
    }
}
