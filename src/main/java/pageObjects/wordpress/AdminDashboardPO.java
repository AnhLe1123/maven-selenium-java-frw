package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminDashboardPageUI;
import pageUIs.wordpress.PageGeneratorManager;

public class AdminDashboardPO extends BasePage {
    private WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminPostSearchPO clickPostsMenuLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.POSTS_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.POSTS_MENU_LINK);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }
}
