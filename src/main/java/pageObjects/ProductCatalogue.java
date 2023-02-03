package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractPackage.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
WebDriver driver;

public ProductCatalogue(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
//List<WebElement> items=driver.findElements(By.cssSelector(".mb-3"));
@FindBy(css=".mb-3")
List<WebElement> items;

@FindBy(css=".ng-animating")
WebElement spinner;

By itemsBy=By.cssSelector(".mb-3");
By addTocart=By.cssSelector(".card-body button:last-of-type");
By toast=By.cssSelector("#toast-container");

public List<WebElement> getItems() {
	waitElements(itemsBy);
	return items;
}
public WebElement getItemList(String itemName) {
	WebElement ite= getItems().stream().filter(item->
	item.findElement(By.xpath("//h5/b")).getText().equals(itemName)).findFirst().orElse(null);
	return ite;
	
}
public void addToCart(String itemName) {
	WebElement ite=getItemList(itemName);
	ite.findElement(addTocart).click();
	waitElements(toast);
	waitElementsInvisible(spinner);

}

}