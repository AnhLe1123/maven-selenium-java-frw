package pageObjects.saucelab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPageOject extends BasePage {
    private WebDriver driver;

    public ProductPageOject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectOptionInSortDropdown(String sortOption) {
        waitForElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDefaultDropdown(driver, ProductPageUI.SORT_DROPDOWN, sortOption);
    }

    public boolean isProductNameSortedAscending() {
        waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_NAMES);
        List<String> productNameUIList = new ArrayList<>();
        List<WebElement> productNames = getListWebElement(driver, ProductPageUI.PRODUCT_NAMES);
        for (WebElement productName: productNames) {
            productNameUIList.add(productName.getText());
        }
        List<String> productNameSortList = new ArrayList<>(productNameUIList);
        Collections.sort(productNameSortList);
        return productNameUIList.equals(productNameSortList);
    }

    public boolean isProductNameSortedDescending() {
        waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_NAMES);
        List<String> productNameUIList = new ArrayList<>();
        List<WebElement> productNames = getListWebElement(driver, ProductPageUI.PRODUCT_NAMES);
        for (WebElement productName: productNames) {
            productNameUIList.add(productName.getText());
        }
        List<String> productNameSortList = new ArrayList<>(productNameUIList);
        Collections.sort(productNameSortList);
        Collections.reverse(productNameSortList);
        return productNameUIList.equals(productNameSortList);
    }

    public boolean isProductPriceSortedAscending() {
        waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_PRICES);
        List<Float> productPriceUIList = new ArrayList<>();
        List<WebElement> productPrices = getListWebElement(driver, ProductPageUI.PRODUCT_PRICES);
        for (WebElement productPrice: productPrices) {
            productPriceUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
        }
        List<Float> productPriceSortList = new ArrayList<>(productPriceUIList);
        Collections.sort(productPriceSortList);
        return productPriceUIList.equals(productPriceSortList);
    }

    public boolean isProductPriceSortedDescending() {
        waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_PRICES);
        List<Float> productPriceUIList = new ArrayList<>();
        List<WebElement> productPrices = getListWebElement(driver, ProductPageUI.PRODUCT_PRICES);
        for (WebElement productPrice: productPrices) {
            productPriceUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
        }
        List<Float> productPriceSortList = new ArrayList<>(productPriceUIList);
        Collections.sort(productPriceSortList);
        Collections.reverse(productPriceSortList);
        return productPriceUIList.equals(productPriceSortList);
    }
}
