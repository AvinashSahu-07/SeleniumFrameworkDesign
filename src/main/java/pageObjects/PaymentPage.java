package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractPackage.AbstractComponents;

public class PaymentPage  extends AbstractComponents {
	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css="section[class*='ta-results'] button span")
	List<WebElement> options;
	
	By optionsBy=By.cssSelector("[class*='ta-results']");
	By orderClick=By.cssSelector(".action__submit");
	
	public void selectCoutryOtions(String country) {
		selectCountry.sendKeys(country);
		waitElements(optionsBy);
	}
	
	public List<WebElement> getOptions() {
		
		for(WebElement option:options) {
			if(option.getText().equals("India")) {
				option.click();
			}
		}
		return options;
	}
	public WebElement placeOrder() {
		WebElement ele=waitElementsClickable(orderClick);//By.cssSelector(".action__submit"))
		//driver.findElement(By.cssSelector(".action__submit")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		return ele;
	}

}
