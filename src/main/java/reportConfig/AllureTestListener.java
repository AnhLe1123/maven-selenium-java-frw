package reportConfig;

import commons.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

    // Screenshot attachments for Allure
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            Object testClass = iTestResult.getInstance();
            WebDriver driver = ((BaseTest) testClass).getDriver();
            saveScreenshotPNG(iTestResult.getName(), driver);
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onFinish(ITestContext arg0) {

    }

    @Override
    public void onTestStart(ITestResult arg0) {
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
    }
}