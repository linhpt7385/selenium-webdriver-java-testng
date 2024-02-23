package Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtendReport implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Attach pass message to report");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test screen short and Attach skip message to report");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Attach skip message to report");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Action before message to report");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Action after message to report");
    }
}
