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
import pageObjects.OrderPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class ShoppingAppCopy extends BaseTest {
	
	String name="test17@mailinator.com";
	String pass="Test@1234";
	String itemName="ZARA COAT 3";
			

	@Test
	public void ShoppingApps() throws IOException {
	
		//LaunchApp
		LandingPage landingPage=LaunchApp();
		
		//landingPage.LoginApp(name,pass); 1st way
		ProductCatalogue productCatalogue=landingPage.LoginApp(name,pass);
		
		//Adding Products and explicit wait
		
		//ProductCatalogue productCatalogue=new ProductCatalogue(driver); 1st way
		List<WebElement> items=productCatalogue.getItems();
		productCatalogue.addToCart(itemName);	
		//productCatalogue.goToCart(); 1st way
		CartPage cartPage=productCatalogue.goToCart();
		
		//In checkout page for checkout
		//CartPage cartPage=new CartPage(driver); 1st way
		List<WebElement> cartItems=cartPage.getCartItems();
		Boolean match=cartPage.verifyProductDisplay(itemName);
		Assert.assertTrue(match);
		//cartPage.checkOutButton();1st way
		PaymentPage paymentPage=cartPage.checkOutButton();
		
		//In payment page:
		//PaymentPage paymentPage=new PaymentPage(driver);1st way
		paymentPage.selectCoutryOtions("India");
		List<WebElement> options=paymentPage.getOptions();
		WebElement ele=paymentPage.placeOrder();
		
		//In Success page:
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		String confirmMsg=confirmationPage.SuccessMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

	}
	@Test(dependsOnMethods={"ShoppingApps"})
	public void OrderHistory() {
		ProductCatalogue productCatalogue=landingPage.LoginApp("test17@mailinator.com","Test@1234");
		OrderPage orderPage=productCatalogue.goToOrder();
		Assert.assertTrue(orderPage.verifyOrderDisplay(itemName));
	}
	

}
