package FrameworkPractice.SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkPractice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import FrameworkPractice.SeleniumFrameworkDesign.pageObjects.CartPage;
import FrameworkPractice.SeleniumFrameworkDesign.pageObjects.LoginPage;
import FrameworkPractice.SeleniumFrameworkDesign.pageObjects.OrderPage;
import FrameworkPractice.SeleniumFrameworkDesign.pageObjects.PlaceOrderPage;
import FrameworkPractice.SeleniumFrameworkDesign.pageObjects.ProductCatalog;

public class SubmitOrderr extends BaseTest{
	
	//String ProductTitle = "BANARSI SAREE";

	@Test(dataProvider="getData")
    public void SubmitOrderTest(HashMap<String,String> input) throws IOException {


        LoginPage loginpage = new LoginPage(driver);
        loginpage.loginApplication(input.get("email"), input.get("password"));
        
		ProductCatalog productCatalog = new ProductCatalog(driver);
		productCatalog.getProductsList();
		productCatalog.getProductByName(input.get("productName"));
		productCatalog.addProductToCart(input.get("productName"));

		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyItemInCart(input.get("productName"));
		Assert.assertTrue(match);		
		cartPage.Checkout();
		
		// assertions should not be part of page object model - it should only have actions.

		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		placeOrderPage.placeOrder("pak", "Pakistan");
		
	}
	
	@Test(dependsOnMethods={"SubmitOrderTest"}, dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String,String> input)
	{
		
		    LoginPage loginpage = new LoginPage(driver);
	        loginpage.loginApplication(input.get("email"), input.get("password"));
		    OrderPage orderPage = new OrderPage(driver);
		    Assert.assertTrue(orderPage.verifyItemInOrdersList(input.get("productName")));
		

	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"test@mahnoor.com","nazMah@123","BANARSI SAREE"},{"anshika@gmail.com","Iamking@000","IPHONE 13 PRO"}}; // object is a general data type so it can be string float etc any kind
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "test@mahnoor.com");
//		map.put("password", "nazMah@123");
//		map.put("productName", "BANARSI SAREE");
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "IPHONE 13 PRO");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "/src/test/java/SeleniumFrameworkDesign/data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}}; // object is a general data type so it can be string float etc any kind
	}
	
}

// driver.quit();
