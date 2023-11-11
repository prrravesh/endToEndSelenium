package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCatalogue;
import testBase.TestBase;

public class verifyOrder extends TestBase {
	String searchProduct = "ADIDAS ORIGINAL";
	String user = "9760pks@gmail.com";
	String pass = "9760pks";
	
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
