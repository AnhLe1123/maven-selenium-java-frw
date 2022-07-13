package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.*;
import pageUIs.jQuery.uploadFile.BasePageUIJqueryUpload;
import pageUIs.liveGuru.user.UserBasePageUILiveGuru;
import pageUIs.nopCommerce.admin.AdminBasePageUINopCommerce;
import pageUIs.nopCommerce.user.UserBasePageUINopCommerce;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for(String id : allWindows) {
            if(!id.equals(parentID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            String windowTitle = driver.getTitle();
            if (windowTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    private By getByLocator(String locatorType) {
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            return By.id(locatorType.substring(3));

        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            return By.name(locatorType.substring(5));

        } else if (locatorType.startsWith("classname=") || locatorType.startsWith("CLASSNAME=") || locatorType.startsWith("Classname=")) {
            return By.className(locatorType.substring(10));

        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            return By.cssSelector(locatorType.substring(4));

        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            return By.xpath(locatorType.substring(6));

        } else {
            throw new RuntimeException("Locator type is not supported");
        }
    }

    private String getDynamicXpath(String locatorType, String... dynamicValues) {
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValues);
        }
        return locatorType;
    }

    private WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator, String... dynamicValues) {
        return driver.findElements(getByLocator(getDynamicXpath(locator, dynamicValues)));
    }

    public void clickToElement(WebDriver driver, String locator) {
        if (driver.toString().toLowerCase().contains("internet explorer")) {
            clickToElementByJS(driver, locator);
            sleepInSecond(3);
        } else {
            getWebElement(driver, locator).click();
        }
    }

    public void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
        if (driver.toString().toLowerCase().contains("internet explorer")) {
            clickToElementByJS(driver, locator, dynamicValues);
        } else {
            getWebElement(driver, getDynamicXpath(locator, dynamicValues)).click();
        }
    }

    public void sendkeyToElement(WebDriver driver, String locator, String textValue) {
        WebElement element = getWebElement(driver, locator);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String textValue, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    public void clearValueInElementByPressKey(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        element.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));

    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String textItem) {
        select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(textItem);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String textItem, String... dynamicValues) {
        select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
        select.selectByVisibleText(textItem);
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator) {
        select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator, String... dynamicValues) {
        select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItem) {
        driver.findElement(By.xpath(parentXpath)).click();
        sleepInSecond(1);

        explicitWait = new WebDriverWait(driver, longTimeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for(WebElement item : allItems) {
            if(item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                break;
            }
        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getAttribute(attributeName);
    }

    public String getWebElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getWebElementText(WebDriver driver, String locator, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getText();
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getWebElementSize(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    public int getWebElementSize(WebDriver driver, String locator, String... dynamicValues) {
        return getListWebElement(driver, getDynamicXpath(locator, dynamicValues)).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... dynamicValues) {
        if (!isElementSelected(driver, locator, dynamicValues)) {
            getWebElement(driver, getDynamicXpath(locator, dynamicValues)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator, String... dynamicValues) {
        if (!isElementSelected(driver, locator, dynamicValues)) {
            getWebElement(driver, getDynamicXpath(locator, dynamicValues)).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            return getWebElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValues) {
        try {
            return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void overrideGlobalTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, locator);
        overrideGlobalTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible in UI");
            return true;
        } else {
            System.out.println("Element in DOM and visible in UI");
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... dynamicValues) {
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locator, dynamicValues));
        overrideGlobalTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrameIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... dynamicValues) {
        action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicXpath(locator, dynamicValues)), key).perform();
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... dynamicValues) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery !=null) && (jQuery.active === 0);");
            }
        };
        return explicitWait.until(jQueryLoad);
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getWebElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
        jsExecutor = (JavascriptExecutor) driver;
        xpathLocator = xpathLocator.replace("xpath=", "");
        return (String) jsExecutor.executeScript(" return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val();");
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        boolean isLoaded = false;
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        if (status) {
            isLoaded = true;
        }
        return isLoaded;
    }

    public boolean isImageLoaded(WebDriver driver, String locator, String... dynamicValues) {
        locator = getDynamicXpath(locator, dynamicValues);
        boolean isLoaded = false;
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        if (status) {
            isLoaded = true;
        }
        return isLoaded;
    }
    
    public void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
        locator = getDynamicXpath(locator, dynamicValues);
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForAllElementsVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForAllElementsVisible(WebDriver driver, String locator, String... dynamicValues) {
        locator = getDynamicXpath(locator, dynamicValues);
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        overrideGlobalTimeout(driver, longTimeout);
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
        locator = getDynamicXpath(locator, dynamicValues);
        explicitWait = new WebDriverWait(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        overrideGlobalTimeout(driver, longTimeout);
    }

    public void waitForAllElementsInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
        overrideGlobalTimeout(driver, longTimeout);
    }

    public void waitForAllElementsInvisible(WebDriver driver, String locator, String... dynamicValues) {
        locator = getDynamicXpath(locator, dynamicValues);
        explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
        overrideGlobalTimeout(driver, longTimeout);
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
        locator = getDynamicXpath(locator, dynamicValues);
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie: cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(2);
    }

    //Switch page with Page Object pattern

    public UserAddressesPageObject openAddressesPage(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.ADDRESSES_SIDEBAR_LINK);
        clickToElement(driver, UserBasePageUINopCommerce.ADDRESSES_SIDEBAR_LINK);
        return PageGeneratorManager.getUserAddressesPage(driver);
    }

    public UserOrdersPageObject openOrdersPage(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.ORDERS_SIDEBAR_LINK);
        clickToElement(driver, UserBasePageUINopCommerce.ORDERS_SIDEBAR_LINK);
        return PageGeneratorManager.getUserOrdersPage(driver);
    }

    public UserRewardPointsPageObject openRewardPointsPage(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.REWARD_POINTS_SIDEBAR_LINK);
        clickToElement(driver, UserBasePageUINopCommerce.REWARD_POINTS_SIDEBAR_LINK);
        return PageGeneratorManager.getUserRewardPointsPage(driver);
    }

    public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.CUSTOMER_INFO_SIDEBAR_LINK);
        clickToElement(driver, UserBasePageUINopCommerce.CUSTOMER_INFO_SIDEBAR_LINK);
        return PageGeneratorManager.getUserCustomerInfoPage(driver);
    }

    //Switch page with Dynamic locator (way 1 - few pages)

    public BasePage openPageByName(WebDriver driver, String pageName) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.SIDEBAR_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserBasePageUINopCommerce.SIDEBAR_LINK_BY_PAGE_NAME, pageName);
        switch (pageName) {
            case "Addresses":
                return PageGeneratorManager.getUserAddressesPage(driver);
            case "Orders":
                return PageGeneratorManager.getUserOrdersPage(driver);
            case "Reward points":
                return PageGeneratorManager.getUserRewardPointsPage(driver);
            case "Customer info":
                return PageGeneratorManager.getUserCustomerInfoPage(driver);
            default:
                throw new RuntimeException("Invalid page name");
        }
    }

    //Switch page with Dynamic locator (way 2 - many pages)
    public void openPageAtSidebarByName(WebDriver driver, String pageName) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.SIDEBAR_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserBasePageUINopCommerce.SIDEBAR_LINK_BY_PAGE_NAME, pageName);
    }

    public UserHomePageObject clickUserLogoutLink(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.LOGOUT_LINK);
        clickToElement(driver, UserBasePageUINopCommerce.LOGOUT_LINK);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public AdminLoginPageObject clickAdminLogoutLink(WebDriver driver) {
        isJQueryAjaxLoadedSuccess(driver);
        waitForElementClickable(driver, AdminBasePageUINopCommerce.LOGOUT_LINK);
        clickToElement(driver, AdminBasePageUINopCommerce.LOGOUT_LINK);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }

    //LiveGuru project
    public void clickToSubLinkByText(WebDriver driver, String linkText, String subLinkText) {
        waitForElementClickable(driver, UserBasePageUILiveGuru.LINK_AT_HEADER_BY_TEXT, linkText);
        clickToElement(driver, UserBasePageUILiveGuru.LINK_AT_HEADER_BY_TEXT, linkText);

        waitForElementClickable(driver, UserBasePageUILiveGuru.SUB_LINK_AT_HEADER_BY_TEXT, subLinkText);
        clickToElement(driver, UserBasePageUILiveGuru.SUB_LINK_AT_HEADER_BY_TEXT, subLinkText);
    }

    //JQuery Upload file
    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String projectPath = GlobalConstants.UPLOAD_FILES;
        String fullFileName = "";
        for (String file: fileNames) {
            fullFileName = fullFileName + projectPath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, BasePageUIJqueryUpload.UPLOAD_FILE).sendKeys(fullFileName);
    }

    //NopCommerce User

    /**
     * Click to dynamic link at header by partial classname
     * @param driver
     * @param partialClassName Example: Full xpath = "//a[@class='ico-register']", then pass 'register' as partialClassName
     */
    public void clickToHeaderLinkByClassName(WebDriver driver, String partialClassName) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.HEADER_LINK_BY_PARTIAL_CLASS_NAME, partialClassName);
        clickToElement(driver, UserBasePageUINopCommerce.HEADER_LINK_BY_PARTIAL_CLASS_NAME, partialClassName);
    }

    /**
     * Check to dynamic checkbox by option text
     * @param driver
     * @param optionText
     */
    public void checkToCheckboxByOptionText(WebDriver driver, String optionText) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.CHECKBOX_BY_TEXT_OPTION, optionText);
        checkToCheckboxOrRadio(driver, UserBasePageUINopCommerce.CHECKBOX_BY_TEXT_OPTION, optionText);
    }

    /**
     * Input to dynamic textbox by textboxID
     * @param driver
     * @param textboxID
     * @param value
     */
    public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
        waitForElementVisible(driver, UserBasePageUINopCommerce.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, UserBasePageUINopCommerce.TEXTBOX_BY_ID, value, textboxID);
    }

    /**
     * Select text option in default dropdown by dropdownName
     * @param driver
     * @param dropdownName
     * @param optionText
     */
    public void selectOptionInDropdownByName(WebDriver driver, String dropdownName, String optionText) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.DROPDOWN_BY_NAME, dropdownName);
        selectItemInDefaultDropdown(driver, UserBasePageUINopCommerce.DROPDOWN_BY_NAME, optionText, dropdownName);
    }

    /**
     * Click to button by buttonText
     * @param driver
     * @param buttonText
     */
    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementClickable(driver, UserBasePageUINopCommerce.BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, UserBasePageUINopCommerce.BUTTON_BY_TEXT, buttonText);
    }

    /**
     * Check that dynamic checkbox by option text is selected or not
     * @param driver
     * @param optionText
     * @return boolean
     */
    public boolean isCheckboxByOptionTextSelected(WebDriver driver, String optionText) {
        waitForElementVisible(driver, UserBasePageUINopCommerce.CHECKBOX_BY_TEXT_OPTION, optionText);
        return isElementSelected(driver, UserBasePageUINopCommerce.CHECKBOX_BY_TEXT_OPTION, optionText);
    }

    /**
     * Get value of dynamic textbox by textboxId
     * @param driver
     * @param textboxID
     * @return string - value of textbox
     */
    public String getTextboxValue(WebDriver driver, String textboxID) {
        waitForElementVisible(driver, UserBasePageUINopCommerce.TEXTBOX_BY_ID, textboxID);
        return getAttributeValue(driver, UserBasePageUINopCommerce.TEXTBOX_BY_ID, "value", textboxID);
    }

    public String getSelectedOptionAtDropdownByName(WebDriver driver, String dropdownName) {
        waitForElementVisible(driver, UserBasePageUINopCommerce.DROPDOWN_BY_NAME, dropdownName);
        return getSelectedItemInDefaultDropdown(driver, UserBasePageUINopCommerce.DROPDOWN_BY_NAME, dropdownName);
    }

    private Select select;
    private Actions action;
    private WebDriverWait explicitWait;
    private JavascriptExecutor jsExecutor;
    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
