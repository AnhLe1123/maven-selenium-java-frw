package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    protected final Logger log;

    protected BaseTest() {
        log = LogManager.getLogger(Log.class);
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        if (browser == BrowserList.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();

            //Disable browser log in Console
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.BROWSER_LOG + "/FirefoxLog.log");

            FirefoxOptions options = new FirefoxOptions();
            //Install plugin for Firefox
            //FirefoxProfile profile = new FirefoxProfile();
            //File addBlocker = new File(GlobalConstants.BROWSER_EXTENSION + "/adblocker_ultimate-3.7.15.xpi");
            //profile.addExtension(addBlocker);
            //options.setProfile(profile);

            //Disable SSL
            options.setAcceptInsecureCerts(true);
            //Auto download
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FILES);
            options.addPreference("browser.download.useDownloadDir", true);
            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed" +
                    "application/msword,application/csv,text/csv,image/png,image/jpeg,application/pdf,text/html,text/plain,application/excel,application/vnd.ms-excel" +
                    "application/x-excel,application/x-msexcel,application/octet-stream");
            options.addPreference("pdfjs.disabled", true);
            //Browser in private mode
            //options.addArguments("-private");

            driver = new FirefoxDriver(options);

        } else if (browser == BrowserList.CHROME) {
            WebDriverManager.chromedriver().setup();

            //Disable browser log in Console
            System.setProperty("webdriver.chrome.args", "--disable-logging");
            System.setProperty("webdriver.chrome.silentOutput", "true");

            ChromeOptions options = new ChromeOptions();
            //install plugin for Chrome
            //File file = new File(GlobalConstants.BROWSER_EXTENSION + "/adblock_4_41_0.crx");
            //options.addExtensions(file);

            //Disable SSL
            options.setAcceptInsecureCerts(true);
            //Disable developer mode
            options.addArguments("--disable-infobars");
            //Disable notification popup
            options.addArguments("--disable-notifications");
            //Disable location popup
            options.addArguments("--disable-geolocation");
            //browser incognito mode
            options.addArguments("--incognito");

            Map<String, Object> prefs = new HashMap<String, Object>();
            //Save password in Chrome
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            //Auto download
            prefs.put("profile.default_content_settings.popup", 0);
            prefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILES);

            options.setExperimentalOption("prefs", prefs);
            //Disable automation info bar
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.EDGE) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else if (browser == BrowserList.OPERA) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();

        } else if (browser == BrowserList.BRAVE) {
            WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
            ChromeOptions options = new ChromeOptions();
            if (GlobalConstants.OS_NAME.contains("Mac")) {
                options.setBinary("/Applications/Brave Browser.app/Contents/MacOS/Brave Browser");
            }
            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.COCCOC) {
            WebDriverManager.chromedriver().driverVersion("101.0.4951.15").setup();
            ChromeOptions options = new ChromeOptions();
            if (GlobalConstants.OS_NAME.contains("Mac")) {
                options.setBinary("/Applications/CocCoc.app/Contents/MacOS/CocCoc");
            }
            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.H_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);

        } else if (browser == BrowserList.SAFARI) {
            driver = new SafariDriver();

        } else if (browser == BrowserList.IE) {
            WebDriverManager.iedriver().arch32().setup();
            driver = new InternetExplorerDriver();

        } else {
            throw new RuntimeException("Invalid browser driver");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String pageUrl) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        if (browser == BrowserList.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();

            //Disable browser log in Console
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.BROWSER_LOG + "/FirefoxLog.log");

            FirefoxOptions options = new FirefoxOptions();
            //Install plugin for Firefox
            //FirefoxProfile profile = new FirefoxProfile();
            //File addBlocker = new File(GlobalConstants.BROWSER_EXTENSION + "/adblocker_ultimate-3.7.15.xpi");
            //profile.addExtension(addBlocker);
            //options.setProfile(profile);

            //Disable SSL
            options.setAcceptInsecureCerts(true);
            //Auto download
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FILES);
            options.addPreference("browser.download.useDownloadDir", true);
            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed" +
                    "application/msword,application/csv,text/csv,image/png,image/jpeg,application/pdf,text/html,text/plain,application/excel,application/vnd.ms-excel" +
                    "application/x-excel,application/x-msexcel,application/octet-stream");
            options.addPreference("pdfjs.disabled", true);
            //Browser in private mode
            //options.addArguments("-private");

            driver = new FirefoxDriver(options);

        } else if (browser == BrowserList.CHROME) {
            WebDriverManager.chromedriver().setup();

            //Disable browser log in Console
            System.setProperty("webdriver.chrome.args", "--disable-logging");
            System.setProperty("webdriver.chrome.silentOutput", "true");

            ChromeOptions options = new ChromeOptions();
            //install plugin for Chrome
            //File file = new File(GlobalConstants.BROWSER_EXTENSION + "/adblock_4_41_0.crx");
            //options.addExtensions(file);

            //Disable SSL
            options.setAcceptInsecureCerts(true);
            //Disable developer mode
            options.addArguments("--disable-infobars");
            //Disable notification popup
            options.addArguments("--disable-notifications");
            //Disable location popup
            options.addArguments("--disable-geolocation");
            //browser incognito mode
            options.addArguments("--incognito");

            Map<String, Object> prefs = new HashMap<String, Object>();
            //Save password in Chrome
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            //Auto download
            prefs.put("profile.default_content_settings.popup", 0);
            prefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILES);

            options.setExperimentalOption("prefs", prefs);
            //Disable automation info bar
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.EDGE) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else if (browser == BrowserList.OPERA) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();

        } else if (browser == BrowserList.BRAVE) {
            WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
            ChromeOptions options = new ChromeOptions();
            if (GlobalConstants.OS_NAME.contains("Mac")) {
                options.setBinary("/Applications/Brave Browser.app/Contents/MacOS/Brave Browser");
            }
            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.COCCOC) {
            WebDriverManager.chromedriver().driverVersion("101.0.4951.15").setup();
            ChromeOptions options = new ChromeOptions();
            if (GlobalConstants.OS_NAME.contains("Mac")) {
                options.setBinary("/Applications/CocCoc.app/Contents/MacOS/CocCoc");
            }
            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);

        } else if (browser == BrowserList.H_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);

        } else if (browser == BrowserList.SAFARI) {
            driver = new SafariDriver();

        } else if (browser == BrowserList.IE) {
            WebDriverManager.iedriver().arch32().setup();
            driver = new InternetExplorerDriver();

        } else {
            throw new RuntimeException("Invalid browser driver");
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(pageUrl);

        return driver;
    }

    private String getPageUrlByEnv(String environmentName) {
        switch (environmentName) {
            case "DEV":
                return GlobalConstants.DEV_USER_PAGE_URL;
            case "TESTING":
                return GlobalConstants.TESTING_USER_PAGE_URL;
            case "STAGING":
                return GlobalConstants.STAGING_USER_PAGE_URL;
            default:
                throw new RuntimeException("Invalid environment");
        }
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;

            // Add issues in ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    @BeforeSuite
    public void initBeforeSuite() {
        deleteAllFilesInFolder(GlobalConstants.REPORTNG_SCREENSHOT);
        deleteAllFilesInFolder(GlobalConstants.ALLURE_REPORT);
    }

    private void deleteAllFilesInFolder(String folderPath) {
        try {
            File file = new File(folderPath);
            File[] listOFiles = file.listFiles();
            for (int i = 0; i < listOFiles.length; i++) {
                if (listOFiles[i].isFile()) {
                    new File(listOFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    protected void closeBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = GlobalConstants.OS_NAME.toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long generateRandomNumber() {
        return Calendar.getInstance().getTimeInMillis() % 100000;
    }

    protected String getCurrentDay() {
        DateTime nowUTC = new DateTime();
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            return "0" + day;
        }
        return String.valueOf(day);
    }

    protected String getCurrentMonth() {
        DateTime now = new DateTime();
        int month = now.getMonthOfYear();
        if (month < 10) {
            return "0" + month;
        }
        return String.valueOf(month);
    }

    protected String getCurrentYear() {
        DateTime now = new DateTime();
        return String.valueOf(now.getYear());
    }

    protected String getToday() {
        return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
    }

    protected void showBrowserConsoleLogs(WebDriver driver) {
        if (driver.toString().contains("chrome")) {
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> logList = logs.getAll();
            for (LogEntry logging: logList) {
                log.info("------------" + logging.getLevel().toString() + "------------\n" + logging.getMessage());
            }
        }
    }
}
