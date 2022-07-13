package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
    private WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageHeader() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.PAGE_TITLE);
        return getWebElementText(driver, UserCustomerInfoPageUI.PAGE_TITLE);
    }
}
