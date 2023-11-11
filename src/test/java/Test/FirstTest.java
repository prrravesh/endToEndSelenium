package Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import PageObjects.CartPage;
import PageObjects.CheckOut;
import PageObjects.LoginPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCatalogue;
import PageObjects.ThankYouPage;
import testBase.TestBase;

public class FirstTest extends TestBase {



	//@Test(dataProvider= "getData" )
	public void submitOrder(HashMap<String ,String> map) throws InterruptedException, IOException {

		LoginPage login = new LoginPage(driver);
		ProductCatalogue menu = new ProductCatalogue(driver);
		CartPage cart = new CartPage(driver);
		CheckOut checkout = new CheckOut(driver);
		ThankYouPage thankyou = new ThankYouPage(driver);

		login.goTo();
		login.loginToApp(map.get("email"), map.get("pass"));

		menu.clickOnItem(map.get("searchProduct"));
		menu.clickOnCartButton();

		boolean productOnCart = cart.checkProductInCart(map.get("searchProduct"));
		Assert.assertTrue(productOnCart);
		cart.checkout();

		checkout.selectCountry();
		checkout.placeOrder();

		String message = thankyou.verifyThankYou();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

	}

@Test
	public void checkOrder() {
		LoginPage login = new LoginPage(driver);
		login.goTo();
		login.loginToApp("9760pks@gmail.com","9760pks");

		ProductCatalogue menu = new ProductCatalogue(driver);
		menu.orders();

		OrdersPage order = new OrdersPage(driver);
		boolean status = order.checkProduct("ADIDAS ORIGINAL");
		Assert.assertTrue(status);

	}
	@DataProvider
	public Object[][] getData() throws StreamReadException, DatabindException, IOException {
		
		List<HashMap<String, String>> data1=	getDataFromJson(System.getProperty("user.dir") +"//src//test//java//Resources//data.json" );
		
		return new Object[][] {{data1.get(0)},{data1.get(1)}};
		
	}
	

}
