package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.BasePage;

public class OrdersPage extends BasePage {
	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tbody//tr//td[2]")
	private List<WebElement> OrderName;
	
	public boolean checkProduct(String product) {
		boolean value=false;
		for(WebElement w: OrderName) {
			if(w.getText().equalsIgnoreCase(product)) {
				value= true;
				break;
				
			}
			
		}return value;
	}
	
	
		
	}