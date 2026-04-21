package base;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;

	// ==============================
	// Load config.properties
	// ==============================
	public void loadConfig() {
		try {
			prop = new Properties();

			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config/config.properties");

			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ==============================
	// Launch Browser
	// ==============================
	public void launchBrowser() {

		// Safety check
		if (prop == null) {
			loadConfig();
		}

		String browserName = prop.getProperty("browser");

		System.out.println("🖥️ Selected Browser: " + browserName);

		// ==============================
		// Browser Handling
		// ==============================

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} 
		// ❌ Opera avoid karo (unstable)
		else {
			throw new RuntimeException("❌ Browser not supported: " + browserName);
		}

		// Safety check (important)
		if (driver == null) {
			throw new RuntimeException("❌ Driver not initialized");
		}

		// ==============================
		// Open URL
		// ==============================
		String url = prop.getProperty("url");
		driver.get(url);

		driver.manage().window().maximize();

		System.out.println("🌐 URL Launched: " + url);
	}

	// ==============================
	// PageFactory Init
	// ==============================
	public void initPageFactory(Object page) {
		PageFactory.initElements(driver, page);
	}
	
	//captureScreen method
	
	public String captureScreen(String testName, String folderPath) {
	    try {
	    	 String timestamp = String.valueOf(System.currentTimeMillis());
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        String path = folderPath + "/" + testName + "_" + timestamp + ".png";

	        File dest = new File(path);
	        dest.getParentFile().mkdirs(); // create folder

	        Files.copy(src.toPath(), dest.toPath());

	        return path;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	
	// ==============================
	// Close Browser
	// ==============================
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			System.out.println("🛑 Browser Closed");
		}
	}
}