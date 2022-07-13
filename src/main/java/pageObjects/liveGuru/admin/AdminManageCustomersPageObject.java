package pageObjects.liveGuru.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.admin.AdminManageCustomersPageUI;

public class AdminManageCustomersPageObject extends BasePage {
    private WebDriver driver;

    public AdminManageCustomersPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void closeIncommingMessagePopup() {
        waitForElementVisible(driver, AdminManageCustomersPageUI.INCOMMING_MESSAGE_POPUP);
        clickToElement(driver, AdminManageCustomersPageUI.CLOSE_POPUP_ICON);
    }

    public void inputToEmailTextboxAtHeader(String userEmail) {
        waitForElementVisible(driver, AdminManageCustomersPageUI.EMAIL_TEXTBOX_AT_HEADER);
        sendkeyToElement(driver, AdminManageCustomersPageUI.EMAIL_TEXTBOX_AT_HEADER, userEmail);
    }

    public void clickToSearchButton() {
        waitForElementClickable(driver, AdminManageCustomersPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminManageCustomersPageUI.SEARCH_BUTTON);
    }

    public boolean isCustomerInfoDisplayed(String userFullName, String userEmail) {
        waitForElementVisible(driver, AdminManageCustomersPageUI.CUSTOMER_INFO_ROW_BY_FULLNAME_EMAIL, userFullName, userEmail);
        return isElementDisplayed(driver, AdminManageCustomersPageUI.CUSTOMER_INFO_ROW_BY_FULLNAME_EMAIL, userFullName, userEmail);
    }
}
