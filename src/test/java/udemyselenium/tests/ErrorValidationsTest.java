package udemyselenium.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import udemyselenium.pageobjects.CartPage;
import udemyselenium.pageobjects.CheckoutPage;
import udemyselenium.pageobjects.ConfirmationPage;
import udemyselenium.pageobjects.ProductCatalog;
import udemyselenium.testcomponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{
	
	@Test (groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException{
				
		landingPage.loginApplication("usersatu@mailinator.com", "!Test1234");
			
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	} 
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("userdua@mailinator.com", "!Test1234");

		
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
