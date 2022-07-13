package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static LoginPageOject getLoginPage(WebDriver driver) {
        return new LoginPageOject(driver);
    }

    public static ProductPageOject getProductPage(WebDriver driver) {
        return new ProductPageOject(driver);
    }
}
