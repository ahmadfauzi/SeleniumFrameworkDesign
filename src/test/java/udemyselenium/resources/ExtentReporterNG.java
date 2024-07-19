package udemyselenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		
		//String path = System.getProperty("D:\\Eclipse-Workspace\\SeleniumFrameworkDesign\\reports\\index.html");
		String path =System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Autmation Result");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent =  new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Selenium Framerwork Design");
		
		return extent;
	}
}
