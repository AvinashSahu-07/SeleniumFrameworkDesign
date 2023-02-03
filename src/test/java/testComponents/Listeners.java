package testComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.ReporterNg;

public class Listeners implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ReporterNg.getReport();
	
	@Override
	
	 public void onTestStart(ITestResult result) {
		    test=extent.createTest(result.getMethod().getMethodName());
		  }
	 
	 public void onTestSuccess(ITestResult result) {
		    // not implemented
		  }
	 
	 public void onTestFailure(ITestResult result) {
		    test.fail(result.getThrowable());
		  }
	 public void onFinish(ITestContext context) {
		   extent.flush();
		  }


}
