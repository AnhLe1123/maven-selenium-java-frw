package reportConfig;

import commons.BaseTest;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportNGListener extends BaseTest implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");

            Object testClass = result.getInstance();
            WebDriver webDriver = ((BaseTest) testClass).getDriver();

            String screenshotPath = captureScreenshot(webDriver, result.getName());
            Reporter.getCurrentTestResult();
            //Screenshot png
            //Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");

            //Screenshot base64
            Reporter.log("<br><a href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
            Reporter.setCurrentTestResult(null);
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

//    public String captureScreenshot(WebDriver driver, String screenshotName) {
//        try {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String screenPath = GlobalConstants.REPORTNG_SCREENSHOT + File.separator + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
//            FileUtils.copyFile(source, new File(screenPath));
//            return screenPath;
//        } catch (IOException e) {
//            System.out.println("Exception while taking screenshot: " + e.getMessage());
//            return e.getMessage();
//        }
//    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
