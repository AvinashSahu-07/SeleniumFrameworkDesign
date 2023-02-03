package seleniumtutorial;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShoppingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username="test17@mailinator.com";
		String password="Test@1234";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
		//login
		
		
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//List of products selected:
		List<WebElement> items=driver.findElements(By.cssSelector(".mb-3"));
		WebElement ite= items.stream().filter(item->
		item.findElement(By.xpath("//h5/b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		ite.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//expllicit wait for toast and indicator
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//In checkout page for checkout
		List<WebElement> cartItems=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match=cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		
		//In payment page:
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
		
		List<WebElement> options=driver.findElements(By.cssSelector("section[class*='ta-results'] button span"));
		for(WebElement option:options) {
			if(option.getText().equals("India")) {
				option.click();
			}
		}
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		//driver.findElement(By.cssSelector(".action__submit")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMessage);



	}

}
