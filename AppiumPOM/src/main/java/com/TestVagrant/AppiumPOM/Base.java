package com.TestVagrant.AppiumPOM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public static AppiumDriverLocalService startServer()
	{
		boolean serverInUse=checkIfServerIsRunnning(4723);
		if(!serverInUse)
		{
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/java/resources/yusufEmulator");
		normalSleep(6000);
	}

	public static AndroidDriver<AndroidElement> getCapabilities(String appName) throws IOException, InterruptedException
	{
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/TestVagrant/AppiumPOM/global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src");
		File app = new File(appDir,(String) prop.get(appName));

		DesiredCapabilities d=new DesiredCapabilities();
		String device=(String) prop.get("deviceName");
//		String device = prop.getProperty("deviceName"); //for running through Terminal
		
		if(device.contains("Emulator"))
		{
			startEmulator();
		}
		d.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		d.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		d.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
		d.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), d);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void getScreenShot(String testName) throws IOException
	{
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"/FailureScreenShots/"+testName+".png"));
	}
	
	public static void normalSleep(int timeInMilliSeconds) throws InterruptedException
	{
		Thread.sleep(timeInMilliSeconds);
	}
}
