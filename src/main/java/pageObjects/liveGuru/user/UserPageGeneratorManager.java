package pageObjects.liveGuru.user;

import org.openqa.selenium.WebDriver;

public class UserPageGeneratorManager {

    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserDashboardPageObject getUserDashboardPage(WebDriver driver) {
        return new UserDashboardPageObject(driver);
    }
}
