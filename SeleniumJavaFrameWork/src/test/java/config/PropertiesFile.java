package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import Test.TestNG_googleSearch;

public class PropertiesFile {

	static Properties prop = new Properties();
	static String projectPath = System.getProperty("usr.dir");

	public static void main(String[] args) {

		getProperties();
		setProperties();
		getProperties();
	}

	public static void getProperties() {
		
		try {

			InputStream input = new FileInputStream("C:\\Users\\Rafi\\workspace\\SeleniumJavaFramework\\src\\test\\java\\config\\config.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			System.out.println(browser);
			TestNG_googleSearch.UsedBrowser = browser;
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
			
		
			
	}

	public static void setProperties() {

		try {
			OutputStream output = new FileOutputStream("C:\\Users\\Rafi\\workspace\\SeleniumJavaFramework\\src\\test\\java\\config\\config.properties");
			prop.setProperty("Result", "pass");
			prop.store(output, null);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
}
