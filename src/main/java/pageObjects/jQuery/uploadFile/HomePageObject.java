package pageObjects.jQuery.uploadFile;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.uploadFile.HomePageUI;

import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_LOADED_BY_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_LOADED_BY_NAME, fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtons = getListWebElement(driver, HomePageUI.START_BUTTON_AT_TABLE);
        for (WebElement button: startButtons) {
            button.click();
            sleepInSecond(1);
        }
    }

    public boolean isFileLinkUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.LINK_UPLOADED_BY_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.LINK_UPLOADED_BY_FILE_NAME, fileName);
    }

    public boolean isFileImageUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.IMAGE_LINK_UPLOADED_BY_FILE_NAME, fileName);
        return isImageLoaded(driver, HomePageUI.IMAGE_LINK_UPLOADED_BY_FILE_NAME, fileName);
    }
}
