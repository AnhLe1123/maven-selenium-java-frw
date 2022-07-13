package pageObjects.liveGuru.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.liveGuru.user.UserDashboardPageUI;

public class UserDashboardPageObject extends BasePage {
    private WebDriver driver;

    public UserDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
        return getWebElementText(driver, UserDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public boolean isMyDashboardHeaderDisplayed() {
        waitForElementVisible(driver, UserDashboardPageUI.DASHBOARD_HEADER);
        return isElementDisplayed(driver, UserDashboardPageUI.DASHBOARD_HEADER);
    }

    public boolean isContactInformationContains(String contactInfo) {
        waitForElementVisible(driver, UserDashboardPageUI.CONTACT_INFO);
        return getWebElementText(driver, UserDashboardPageUI.CONTACT_INFO).contains(contactInfo);
    }
}
