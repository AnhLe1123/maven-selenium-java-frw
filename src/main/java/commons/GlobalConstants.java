package commons;

import java.io.File;

public class GlobalConstants {
    public static final String DEV_USER_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String DEV_ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final String TESTING_USER_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String TESTING_ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final String STAGING_USER_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String STAGING_ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final String JQUERY_PAGE_URL = "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/";
    public static final String LIVEGURU_ADMIN_PAGE_URL = "http://live.techpanda.org/index.php/backendlogin";
    public static final String LIVEGURU_USER_PAGE_URL = "http://live.techpanda.org/";

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String TEST_RESOURCES = PROJECT_PATH + File.separator + "src" + File.separator + "test" + File.separator + "resources"+ File.separator + File.separator;
    public static final String UPLOAD_FILES = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_FILES = PROJECT_PATH + File.separator + "downloadFiles";
    public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
    public static final String BROWSER_EXTENSION = PROJECT_PATH + File.separator + "browserExtensions";
    public static final String DRAG_AND_DROP_HTML5 = PROJECT_PATH + File.separator + "dragAndDrop";
    public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGScreenshot";
    public static final String ALLURE_REPORT = PROJECT_PATH + File.separator + "allure-results";
    public static final String EXTENT_REPORT_V3 = PROJECT_PATH + File.separator + "ExtentReportV3" + File.separator;
    public static final String EXTENT_REPORT_V4 = PROJECT_PATH + File.separator + "ExtentReportV4" + File.separator;
    public static final String EXTENT_REPORT_V5 = PROJECT_PATH + File.separator + "ExtentReportV5" + File.separator;

    public static final int LONG_TIMEOUT = 30;
    public static final int SHORT_TIMEOUT = 5;
    public static final int RETRY_TEST_FAIL = 2;

    public static final String DB_DEV_URL = "32.18.252.185:9860";
    public static final String DB_DEV_USERNAME = "autotest";
    public static final String DB_DEV_PASSWORD = "aq1234@456%&@SFG1";
    public static final String DB_TEST_URL = "32.18.185.20:6000";
    public static final String DB_TEST_USERNAME = "autotest";
    public static final String DB_TEST_PASSWORD = "aq1234@456%&@SFG1";
}
