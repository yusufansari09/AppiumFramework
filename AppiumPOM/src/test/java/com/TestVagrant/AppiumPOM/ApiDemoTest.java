package com.TestVagrant.AppiumPOM;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Preferences;


public class ApiDemoTest extends Base{
	
	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public void preferenceTabTest(String inputText) throws IOException, InterruptedException
	{
		driver = getCapabilities("apiDemo");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		HomePage h=new HomePage(driver);
		h.clickOnPreferences();
		Preferences p=new Preferences(driver);
		p.clickPreferenceDependencies();
		p.clickWifiCheckbox();
		p.clickWifiSettings();
		p.getWifiSettingsText().sendKeys(inputText);
		p.clickCloseButton();
	}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("killall node");
		normalSleep(3000);
	}
	
	@BeforeClass
	public void startServerForTests()
	{
		service = startServer();
	}
	
	@AfterClass
	public void stopServer()
	{
		service.stop();
	}

}
