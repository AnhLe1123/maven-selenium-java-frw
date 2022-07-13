package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminPostCategoriesPO extends BasePage {
    private WebDriver driver;

    public AdminPostCategoriesPO(WebDriver driver) {
        this.driver = driver;
    }
}
