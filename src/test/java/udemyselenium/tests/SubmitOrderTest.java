package udemyselenium.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import udemyselenium.pageobjects.CartPage;
import udemyselenium.pageobjects.CheckoutPage;
import udemyselenium.pageobjects.ConfirmationPage;
import udemyselenium.pageobjects.OrderPage;
import udemyselenium.pageobjects.ProductCatalog;
import udemyselenium.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout(); 
		checkoutPage.selectCountry("india");
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//To Verify ZARA COAT 3 is displaying in order page
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		//"ZARA COAT 3"
		ProductCatalog productCatalog = landingPage.loginApplication("usersatu@mailinator.com", "!Test1234");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//Call function for Read JSON to String
		List<HashMap<String,String>> data = getJsonDataToMap("D:\\Eclipse-Workspace\\SeleniumFrameworkDesign\\src\\test\\java\\udemyselenium\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "usersatu@mailinator.com");
//		map.put("password", "!Test1234");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "userdua@mailinator.com");
//		map1.put("password", "!Test1234");
//		map1.put("product", "ADIDAS ORIGINAL");

//		return new Object[][] {{map}, {map1}};
//	}
}
