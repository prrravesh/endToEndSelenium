package Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
import testBase.Driver_Commans;
import testBase.JsonToMap;

public class testStartToEnd extends Driver_Commans {

	@Test(priority=1,dataProvider = "getData")
	public void loginToApp(HashMap<String, String> map) throws InterruptedException, IOException {

		LoginPage login = new LoginPage(driver);
		login.goTo();
		login.loginToApp(map.get("email"), map.get("pass"));
		Assert.assertEquals(login.loginSuccess(), true);
		
	}
	//@Test(priority=2,dependsOnMethods= {"loginToApp"},dataProvider = "getData")
	
	public void addItemToCart(HashMap<String,String> map) {
		ProductCatalogue menu = new ProductCatalogue(driver);

		menu.clickOnItem(map.get("searchProduct"));
		menu.clickOnCartButton();

	}

	//@Test(priority=3,dataProvider = "getData")
	public void checkout(HashMap<String,String> map) {
		CartPage cart = new CartPage(driver);

		boolean productOnCart = cart.checkProductInCart(map.get("searchProduct"));
		Assert.assertTrue(productOnCart);
		cart.checkout();

	}

	//@Test(priority=4,dataProvider = "getData")
	public void placeOrder(HashMap<String,String> map) {

		CheckOut checkout = new CheckOut(driver);
		ThankYouPage thankyou = new ThankYouPage(driver);

		checkout.selectCountry();
		checkout.placeOrder();

		String message = thankyou.verifyThankYou();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

	}

	//@Test(priority=5,dataProvider = "getData")
	public void checkOrder(HashMap<String,String> map) {

		ProductCatalogue menu = new ProductCatalogue(driver);
		menu.orders();

		OrdersPage order = new OrdersPage(driver);
		boolean status = order.checkProduct(map.get("searchProduct"));
		Assert.assertTrue(status);

	}

	@DataProvider
	public Object[][] getData() throws StreamReadException, DatabindException, IOException {
		JsonToMap dataProvider=new JsonToMap();
		

		List<HashMap<String, String>> data1 = dataProvider.getDataFromJson(
				System.getProperty("user.dir") + "//src//test//resources//data.json");
		System.out.println(data1);

		return new Object[][] { { data1.get(0) } };

	}

}
