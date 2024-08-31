package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class EventReporterNG extends BaseTests {

    public static ExtentReports getReportObject () {
        // EXTENT automation reporting config

        reportDate = setReportDate();  // get report date for unique run path name

        String path = System.getProperty("user.dir") + "/reports/" + reportDate + "/report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("AutoSuite3 Results");  //button text in report
        reporter.config().setDocumentTitle("Test Results");     //title in report
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);                        // connects current extent results with reporter engine
        extent.setSystemInfo("Tester", "Brian W.");
        return extent;
    }

}
