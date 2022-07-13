package pageUIs.jQuery.dataTable;

public class HomePageUI {
    public static final String PAGING_BY_PAGE_NUMBER = "xpath=//li[@class='qgrd-pagination-page']//a[text()='%s']";
    public static final String PAGING_ACTIVE_BY_PAGE_NUMBER = "xpath=//a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String HEADER_TEXTBOX_BY_FIELD_NAME = "xpath=//div[@class='qgrd-header-text' and text()='%s']//parent::div//following-sibling::input";
    public static final String PAGINATIONS = "xpath=//ul[@class='qgrd-pagination-ul']//li";
    public static final String PAGING_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']//li[%s]//a";
    public static final String VALUES_AT_COLUMN_KEY = "xpath=//tr//td[@data-key='%s']";

    public static final String ROW_INDEX_BY_NAME = "xpath=//tr//td[@class='ui-widget-header' and text()='%s']//preceding-sibling::td";
    public static final String TEXTBOX_BY_ROW_COLUMN_INDEX = "xpath=//tbody//tr[%s]//td[%s]//input";
    public static final String DROPDOWN_BY_ROW_COLUMN_INDEX = "xpath=//tbody//tr[%s]//td[%s]//select";
    public static final String CHECKBOX_BY_ROW_COLUMN_INDEX = "xpath=//tbody//tr[%s]//td[%s]//input[@type='checkbox']";
    public static final String LOAD_DATA_BUTTON = "xpath=//span[contains(text(), 'Demo: Load Data')]";
    public static final String BUTTON_AT_ROW_BY_TITLE = "xpath=//tbody//tr[%s]//button[@title='%s']";
}
