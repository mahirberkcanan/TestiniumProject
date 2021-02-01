package listener;

import Utils.BaseTest;
import org.apache.log4j.MDC;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener extends BaseTest implements ITestListener {
    public void onTestStart(ITestResult result) {
        driver.get(baseUrl);
        log.info("GittiGidiyor Opened");
    }


    public void onTestSuccess(ITestResult result) {
        System.out.println("success");
        log.info("SUCCESS");

    }

    public void onTestFailure(ITestResult result) {
        System.out.println("fail");
        log.error("FAIL");

    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("skipped");

        MDC.put("fail",result.getThrowable());


        log.warn("FAIL");

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "/Users/berks/Downloads/chromedriver.exe");
        driver = new ChromeDriver();

        MDC.put("name",context.getName());


        log.info("Browser Opened");

    }

    public void onFinish(ITestContext context) {
        log.info("Browser Closed");
        //driver.quit();
    }

}