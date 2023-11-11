package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.BasePage;

public class CheckOut extends BasePage {
	WebDriver driver;

	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	private WebElement countryBox;

	By productNameLocator = By.tagName("h3");

	@FindBy(xpath = "(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")
	private WebElement selectCountry;

	By selectCountryLocator = By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]");

	@FindBy(css = ".action__submit")
	private WebElement placeorder;

	public void selectCountry() {
		countryBox.sendKeys("India");
		waitForVisibility(selectCountryLocator);
		selectCountry.click();

	}

	public void placeOrder() {
		javaScriptExecutor(placeorder);

	}

}
