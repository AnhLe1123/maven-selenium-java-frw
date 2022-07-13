package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreateAccountButton() {
        waitForElementClickable(driver, LoginPageUI.CREATE_ACCOUNT_BUTTON);
        clickToElement(driver, LoginPageUI.CREATE_ACCOUNT_BUTTON);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public boolean isConfirmEmailTextboxDisplayed() {
        waitForElementVisible(driver, LoginPageUI.CONFIRM_EMAIL_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_TEXTBOX);
    }

    public boolean isConfirmEmailTextboxUndisplayed() {
        waitForElementInvisible(driver, LoginPageUI.CONFIRM_EMAIL_TEXTBOX);
        return isElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_TEXTBOX);
    }

    public void clickCloseRegisterFormIcon() {
        waitForElementClickable(driver, LoginPageUI.CLOSE_REGISTER_FORM_ICON);
        clickToElement(driver, LoginPageUI.CLOSE_REGISTER_FORM_ICON);
    }
}
