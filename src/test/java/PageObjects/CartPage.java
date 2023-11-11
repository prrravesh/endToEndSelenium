package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.BasePage;

public class CartPage extends BasePage {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	boolean productOnCart = false;
	@FindBy(css = ".cartWrap")
	private List<WebElement> cartProductWebElementCard;

	By productNameLocator = By.tagName("h3");

	@FindBy(css = "li[class=totalRow] .btn-primary")
	private WebElement checkoutBtn;

	public boolean checkProductInCart(String searchProduct) {
		boolean value = false;
		for (WebElement w : cartProductWebElementCard) {
			if (w.findElement(productNameLocator).getText().equals(searchProduct)) {
				value = true;
			}
		}
		return value;

	}

	public void checkout() {
		checkoutBtn.click();
	}

}
