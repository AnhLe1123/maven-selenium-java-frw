package pageObjects.liveGuru.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.admin.AdminLoginPageUILiveGuru;

public class AdminLoginPageObject extends BasePage {
    private WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToUserNameTextbox(String adminUserName) {
        waitForElementVisible(driver, AdminLoginPageUILiveGuru.USERNAME_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUILiveGuru.USERNAME_TEXTBOX, adminUserName);
    }

    public void inputToPasswordTextbox(String adminPassword) {
        waitForElementVisible(driver, AdminLoginPageUILiveGuru.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUILiveGuru.PASSWORD_TEXTBOX, adminPassword);
    }

    public AdminManageCustomersPageObject clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUILiveGuru.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUILiveGuru.LOGIN_BUTTON);
        return AdminPageGeneratorManager.getAdminManageCustomersPage(driver);
    }
}
