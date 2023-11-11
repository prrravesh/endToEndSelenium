package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.BasePage;

public class ThankYouPage extends BasePage {
	WebDriver driver;

	public ThankYouPage(WebDriver driver) {
		super(driver);
     this.driver=driver;
		
		PageFactory.initElements(driver, this);
			}
	
	@FindBy(css=".hero-primary")
	private WebElement thankConfirmation;
	
	public String verifyThankYou() {
		
		String message=thankConfirmation.getText();
				return message;
	}
}