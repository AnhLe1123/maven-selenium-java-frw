package pageObjects.liveGuru.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserHomePageObject extends BasePage {
    private WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }
}
