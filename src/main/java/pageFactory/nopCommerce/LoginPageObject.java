package pageFactory.nopCommerce;

import commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='ico-login']")
    private WebElement loginLink;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    @FindBy(id = "Email-error")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//div[contains(@class, 'message-error')]")
    private WebElement unsuccessErrorMessage;

    public void clickToLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(driver, loginButton);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, emailErrorMessage);
        return getWebElementText(driver, emailErrorMessage);
    }

    public void clickToLoginLink() {
        waitForElementClickable(driver, loginLink);
        clickToElement(driver, loginLink);
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(driver, emailTextbox, email);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendkeyToElement(driver, passwordTextbox, password);
    }

    public String getUnsuccessfulErrorMessage() {
        waitForElementVisible(driver, unsuccessErrorMessage);
        return getWebElementText(driver,unsuccessErrorMessage);
    }
}
