package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Preferences {
	public Preferences(AppiumDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
	private WebElement dependencies;
	
	@AndroidFindBy(id="android:id/checkbox")
	private WebElement wifiCheckbox;
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout)[2]")
	private WebElement wifiSettings;
	
	@AndroidFindBy(className="android.widget.EditText")
	private WebElement wifiSettingsText;

	@AndroidFindBy(className="android.widget.Button")
	private List<WebElement> closeButton;

	public void clickPreferenceDependencies()
	{
		dependencies.click();
	}
	
	public void clickWifiCheckbox()
	{
		wifiCheckbox.click();
	}
	
	public void clickWifiSettings()
	{
		wifiSettings.click();
	}
	
	public WebElement getWifiSettingsText()
	{
		return wifiSettingsText;
	}
	
	public void clickCloseButton()
	{
		closeButton.get(1).click();
	}
	
	
}
