package com.TestVagrant.AppiumPOM;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;

public class GeneralStore extends Base{
	
	@Test
	public void validateTotalCost() throws IOException, InterruptedException
	{
		driver = getCapabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		FormPage fp = new FormPage(driver);
		Utilities u=new Utilities(driver);
		CheckoutPage c=new CheckoutPage(driver);
		
		fp.sendNameFieldText("abc");
		u.hideKeyBoard();
		fp.clickFemaleRadioButton();
		fp.clickCountrySelectionDropdown();
		u.scrollToTextAndClick("Brazil");
		fp.clickShopButton();
		fp.addItemsToCart();
		fp.moveToCart();

		if(u.waitForPresence(4,"com.androidsample.generalstore:id/productPrice"))
		{
			double sumOfEachItems = c.getSumOfEachItemInCart();
			double totalCartAmt = c.getTotalCartAmt();
			AssertJUnit.assertEquals(totalCartAmt, sumOfEachItems);
		}
		else
		{
			System.out.println("Element not visible");
		}
	}
	
//	@BeforeClass
//	public void startServerForTests()
//	{
//		service = startServer();
//	}
//	
//	@AfterClass
//	public void stopServer()
//	{
//		service.stop();
//	}

}
