package com.TestVagrant.AppiumPOM;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	AndroidDriver<AndroidElement>  driver;
	public static MobileElement mobileElement;
	public static boolean isElementPresent;

	public Utilities(AndroidDriver<AndroidElement> driver)
	{
		this.driver=driver;
	}

	public void scrollToTextAndClick(String text)
	{
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))").click();
	}

	public boolean waitForPresence(int timeLimitInSeconds, String targetResourceId)
	{
		try{
			mobileElement =  (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\""+targetResourceId+"\")");
			WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
			wait.until(ExpectedConditions.visibilityOf(mobileElement));
			boolean isElementPresent = mobileElement.isDisplayed();
			return isElementPresent;	
		}catch(Exception e){
			isElementPresent = false;
			System.out.println(e.getMessage());
			return isElementPresent;
		} 
	}
	
	public void hideKeyBoard()
	{
		driver.hideKeyboard();
	}
	
	public static double convertStringToValue(String value)
	{
		value = value.substring(1);
		double eachItemPrice = Double.parseDouble(value);
		return eachItemPrice;
	}
}
