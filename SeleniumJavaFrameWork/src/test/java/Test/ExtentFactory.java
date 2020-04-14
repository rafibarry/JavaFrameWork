package Test;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {

	public static ExtentReports createReportInstance() {
		ExtentReports extent;
		String ReportPath = "C:\\Users\\Rafi\\workspace\\SeleniumJavaFrameWork\\test-output\\HTML-Reports\\Report.html";
		extent = new ExtentReports(ReportPath, true);
		extent.addSystemInfo("Platform", "Window 10");
		
		return extent;
	}
}
