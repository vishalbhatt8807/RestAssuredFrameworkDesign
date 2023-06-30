package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest test;
    String repName;
    public void onStart(ITestContext context){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report"+timeStamp+".html";
        extentSparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
        extentSparkReporter.config().setDocumentTitle("RestAssuredDocumentPaject");
        extentSparkReporter.config().setReportName("Pet Store Users API");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application","Pet Store User API");
        extentReports.setSystemInfo("Operation System",System.getProperty("os.name"));
        extentReports.setSystemInfo("User System",System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("User","Vishal Bhatt");
    }

    public void onTestSuccess(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test is Passed");
    }


    public void onTestFailure(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,"Test is Failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }


    public void onTestSkip(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,"Test is Skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context){
        extentReports.flush();
    }
}
