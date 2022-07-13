package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserPostSearchPO extends BasePage {
    private WebDriver driver;

    public UserPostSearchPO(WebDriver driver) {
        this.driver = driver;
    }
}
