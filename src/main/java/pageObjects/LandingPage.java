package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractPackage.AbstractComponents;

public class LandingPage extends AbstractComponents {
WebDriver driver;

public LandingPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
@FindBy(id="userEmail")
WebElement userId;

@FindBy(id="userPassword")
WebElement userPwd;

@FindBy(id="login")
WebElement submit ;

@FindBy(css="[class*='flyInOut']")
WebElement errorMessage ;

public ProductCatalogue LoginApp(String email,String password) {
	userId.sendKeys(email);
	userPwd.sendKeys(password);
	submit.click();
	ProductCatalogue productCatalogue=new ProductCatalogue(driver);
	return productCatalogue;
}
public void OpenUrl() {
	driver.get("https://rahulshettyacademy.com/client/");
}

public String getError() {
	waitElements(errorMessage);
	String errors=errorMessage.getText();
	return errors;
}

}
