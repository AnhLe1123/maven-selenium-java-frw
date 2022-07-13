package pageObjects.liveGuru.admin;

import org.openqa.selenium.WebDriver;

public class AdminPageGeneratorManager {

    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }

    public static AdminManageCustomersPageObject getAdminManageCustomersPage(WebDriver driver) {
        return new AdminManageCustomersPageObject(driver);
    }
}
