package stepDefinations;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class StepDef extends BaseTest {
	
	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CartPage cartPage;
	PaymentPage paymentPage;
	
	 @Given("I landed on Ecommerce page")
	    public void  I_landed_on_Ecommerce_page() throws Throwable {
		 landingPage=LaunchApp();
	       
	    }
	 
	 @Given("^Logged in with username (.+) and password (.+)$")
	    public void logged_in_with_username_and_password(String username, String password) throws Throwable {
		productCatalogue=landingPage.LoginApp(username,password);
	       
	    }

	    @When("^Add product (.+) to cart$")
	    public void add_product_to_cart(String itemname) throws Throwable {
	    	List<WebElement> items=productCatalogue.getItems();
			productCatalogue.addToCart(itemname);	 
	    }
	    
	    @And("^Checkout (.+) and place the order$")
	    public void checkout_and_place_the_order(String itemname) throws Throwable {
	    	List<WebElement> cartItems=cartPage.getCartItems();
			Boolean match=cartPage.verifyProductDisplay(itemname);
			Assert.assertTrue(match);
			paymentPage=cartPage.checkOutButton();
	        
			paymentPage.selectCoutryOtions("India");
			List<WebElement> options=paymentPage.getOptions();
			WebElement ele=paymentPage.placeOrder();
	    }

	    @Then("^\"([^\"]*)\" message is displayed on confirmation page.$")
	    public void something_message_is_displayed_on_confirmation_page(String strArg1) throws Throwable {
	    	ConfirmationPage confirmationPage=new ConfirmationPage(driver);
			String confirmMsg=confirmationPage.SuccessMessage();
			Assert.assertTrue(confirmMsg.equalsIgnoreCase(strArg1));
	    }

	   
}
