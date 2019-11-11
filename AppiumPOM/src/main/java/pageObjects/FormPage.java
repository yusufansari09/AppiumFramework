package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//*[@text='Female']")
	private WebElement femaleOption;

	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelectionDropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
	private List<WebElement> addItemsToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	
	public void sendNameFieldText(String inputText)
	{
		nameField.sendKeys(inputText);
	}
	
	public void clickFemaleRadioButton()
	{
		femaleOption.click();
	}
	
	public void clickCountrySelectionDropdown()
	{
		countrySelectionDropdown.click();
	}
	
	public void clickShopButton()
	{
		shopButton.click();
	}
	
	public void addItemsToCart()
	{
		for(int i=0;i<addItemsToCart.size();i++)
		{
			addItemsToCart.get(i).click();
		}
	}
	
	public void moveToCart()
	{
		cartButton.click();
	}
}
