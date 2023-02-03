package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractPackage.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//tbody//td[2]")
	List<WebElement> orderItems;
	
//	@FindBy(xpath="//li[@class='totalRow']/button")
//	WebElement checkOut;
	
	
	public List<WebElement> getOrderItems() {
		return orderItems;
	}
	public Boolean verifyOrderDisplay(String itemName) {
		Boolean match= getOrderItems().stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(itemName));
		return match;
		
	}
	

}
