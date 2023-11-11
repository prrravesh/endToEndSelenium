package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.BasePage;

public class ProductCatalogue extends BasePage {
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".mb-3")
	private List<WebElement> productCardWebElement;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	private WebElement cartButton;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	private WebElement myOrders;
	
	By productName=By.cssSelector("b");
	By tostMessage=By.id("toast-container");
	
	@FindBy(tagName="button")
	private List<WebElement> cardButtons;
	
	
	public WebElement searchProduct(String item) {
		
		for(WebElement w:productCardWebElement) {
			String currentName=w.findElement(productName).getText();
			if (currentName.equals(item)) {
				return w.findElements(By.tagName("button")).get(1);
			}
			
		}
		return null;
		
	}
	
	public void clickOnItem(String item) {
		
		WebElement el=searchProduct(item);
		
		if(el!=null) {
			el.click();
		}else {
			System.out.println("item not found");
			Assert.fail();
		
		
		}
		waitForVisibility(tostMessage);
		waitForInVisibility(tostMessage);
	}
	public void clickOnCartButton() {
		cartButton.click();
	}
	
	public void orders() {
		myOrders.click();
		
		
	}
	
}












	