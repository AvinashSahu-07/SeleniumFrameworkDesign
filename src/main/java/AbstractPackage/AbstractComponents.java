package AbstractPackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	public void waitElements(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitElements(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public CartPage goToCart() {
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrder() {
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
	public void waitElementsInvisible(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public WebElement waitElementsClickable(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(findBy));
		return ele;
	}
}
