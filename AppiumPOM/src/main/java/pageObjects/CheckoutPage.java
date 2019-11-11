package pageObjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.TestVagrant.AppiumPOM.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage{
	
	public CheckoutPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	public double getSumOfEachItemInCart()
	{
		double sumOfEachItem=0;
		for(int i=0;i<productList.size();i++)
		{
			String eachProductPriceText = productList.get(i).getText();
			
			Double itemCost = Utilities.convertStringToValue(eachProductPriceText);
			sumOfEachItem=sumOfEachItem+itemCost;
		}
		System.out.println("sum of each item: "+sumOfEachItem);
		return sumOfEachItem;
	}
	
	public double getTotalCartAmt()
	{
		String TotalText = totalAmount.getText();
		Double totalCartAmt = Utilities.convertStringToValue(TotalText);
		System.out.println(totalCartAmt);
		return totalCartAmt;
	}
	
	
	
}

