package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class EventReporterNG {

    public static ExtentReports getReportObject () {
        // EXTENT automation reporting config

        String path = System.getProperty("user.dir") + "/reports/" + "autosuite3.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("AutoSuite3 Results");  //button text in report
        reporter.config().setDocumentTitle("Test Results");     //title in report
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);                        // connects current extent results with reporter engine
        extent.setSystemInfo("Tester", "Brian W.");
        return extent;
    }

}
