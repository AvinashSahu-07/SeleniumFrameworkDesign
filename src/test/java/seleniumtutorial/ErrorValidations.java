package seleniumtutorial;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidations extends BaseTest {

	@Test(retryAnalyzer=Retry.class)
	public void ShoppingApps() throws IOException {
		// TODO Auto-generated method stub
		String name="test17@mailinator.com";
		String pass="Test@134";
				
		//LaunchApp
		LandingPage landingPage=LaunchApp();
		
		//landingPage.LoginApp(name,pass); 1st way
		landingPage.LoginApp(name,pass);
		Assert.assertEquals("Incorrect email or password.",landingPage.getError());
		
	}

}
