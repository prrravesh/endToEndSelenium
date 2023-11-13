package Test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckOut;
import PageObjects.LoginPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCatalogue;
import PageObjects.ThankYouPage;
import testBase.Driver_Commans;

public class testEndToEndEach extends Driver_Commans {
	String searchProduct = "ADIDAS ORIGINAL";
	String user = "9760pks@gmail.com";
	String pass = "9760pks";
	
	
	
	

	@Test(dataProvider= "getData" )
	public void loginToApp(HashMap<String, String> map) throws InterruptedException, IOException {

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
		login.loginToApp(user, pass);
		
		ProductCatalogue menu = new ProductCatalogue(driver);
		menu.orders();
		
		OrdersPage order=new OrdersPage(driver);
		boolean status=order.checkProduct(searchProduct);
		Assert.assertTrue(status);
		
		
	}

}
