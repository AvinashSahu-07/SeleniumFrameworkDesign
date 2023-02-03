package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver InitializeDriver() throws IOException {
		//properties class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		//Access of browser
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//firefox code:
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//edge code:
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	public LandingPage LaunchApp() throws IOException {
		driver=InitializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.OpenUrl();
		return landingPage;
	}

}
