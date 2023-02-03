package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractPackage.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOut;
	
	
	public List<WebElement> getCartItems() {
		return cartItems;
	}
	public Boolean verifyProductDisplay(String itemName) {
		Boolean match= getCartItems().stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(itemName));
		return match;
		
	}
	public PaymentPage checkOutButton() {
		checkOut.click();
		PaymentPage paymentPage=new PaymentPage(driver);
		return paymentPage;
	}

}
