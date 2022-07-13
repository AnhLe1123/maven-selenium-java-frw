package pageFactory.nopCommerce;

import commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePageFactory {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='ico-register']")
    private WebElement registerLink;

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(id = "FirstName-error")
    private WebElement firstNameErrorMessage;

    @FindBy(id = "LastName-error")
    private WebElement lastNameErrorMessage;

    @FindBy(id = "Email-error")
    private WebElement emailErrorMessage;

    @FindBy(id = "Password-error")
    private WebElement passwordErrorMessage;

    @FindBy(id = "ConfirmPassword-error")
    private WebElement confirmPasswordErrorMessage;

    @FindBy(css = "div[class='result']")
    private WebElement registerSuccessMessage;

    @FindBy(xpath = "//div[contains(@class, 'message-error')]")
    private WebElement existingEmailErrorMessage;

    @FindBy(xpath = "//a[@class='ico-logout']")
    private WebElement logoutLink;

    public void clickToRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(driver, registerButton);
    }

    public String getFirstNameErrorMessage() {
        waitForElementVisible(driver, firstNameErrorMessage);
        return getWebElementText(driver, firstNameErrorMessage);
    }

    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, lastNameErrorMessage);
        return getWebElementText(driver, lastNameErrorMessage);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, emailErrorMessage);
        return getWebElementText(driver, emailErrorMessage);
    }

    public String getPasswordErrorMessage() {
        waitForElementVisible(driver, passwordErrorMessage);
        return getWebElementText(driver, passwordErrorMessage);
    }

    public String getConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, confirmPasswordErrorMessage);
        return getWebElementText(driver, confirmPasswordErrorMessage);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(driver, emailTextbox, emailAddress);
    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendkeyToElement(driver, firstNameTextbox, firstName);
    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendkeyToElement(driver, lastNameTextbox, lastName);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendkeyToElement(driver, passwordTextbox, password);
    }

    public void inputToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendkeyToElement(driver, confirmPasswordTextbox, password);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMessage);
        return getWebElementText(driver, registerSuccessMessage);
    }

    public String getExistingEmailErrorMessage() {
        waitForElementVisible(driver, existingEmailErrorMessage);
        return getWebElementText(driver, existingEmailErrorMessage);
    }

    public void clickToLogoutLink() {
        waitForElementClickable(driver, logoutLink);
        clickToElement(driver, logoutLink);
    }

    public void clickToRegisterLink() {
        waitForElementClickable(driver, registerLink);
        clickToElement(driver, registerLink);
    }
}
