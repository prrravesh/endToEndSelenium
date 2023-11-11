package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	private WebElement userName;

	@FindBy(id = "userPassword")
	private WebElement password;

	@FindBy(name = "login")
	private WebElement login;

	public void loginToApp(String user, String pass) {

		userName.sendKeys(user);
		password.sendKeys(pass);
		login.click();

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}

}
