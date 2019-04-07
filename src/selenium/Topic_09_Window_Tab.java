package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_09_Window_Tab {
    WebDriver driver;
    JavascriptExecutor jsExecutor;


    @BeforeTest
	public void beforeTest() {		
		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
	    jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Only_2_Windows_Tabs() {
		
		// Step 01: Go to page
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		// Step 02: Click on Opening a new window: Click Here" 
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		// Step 03: Get ID of parent window
		String parentID = driver.getWindowHandle();
		
		//Step 04: switch to new tab		
		switchToChildWindows_byID(parentID);
		
		//Step 05: Verfify title of current windows is Google
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, "Google");
		
		//Step 06: Close new window/tab
		driver.close();
		
		//Step 07: swtich back to parent window
		driver.switchTo().window(parentID);
		
		//Step 08: Verify coming back parent window successfully
		String parentTitle = driver.getTitle();
		Assert.assertEquals(parentTitle, "SELENIUM WEBDRIVER FORM DEMO");		
		
	}
	
	@Test
	public void TC_02_Multiple_Windows_Tabs_HDFCbank(){

		// Step 01: Go to HDFC page
		driver.get("https://www.hdfcbank.com/");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//****************************************************************************************
		//Handle new pop-up
		List <WebElement> newPopup = driver.findElements(By.xpath("//img[@class='popupbanner']"));
		
		int newPopupSize = newPopup.size();
		if (newPopupSize == 1) {
			System.out.println("New popup is displayed");
		} else 
		{System.out.println("New popup is NOT displayed");}
		
		// size >0 means popup appears
		if (newPopupSize > 0) {

			// Use Click of javascript
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//img[@class='popupCloseButton']")));
			System.out.println("Close popup successfully");
			
			//switch back to Top-windows
			driver.switchTo().defaultContent();				
		}
		System.out.println("Passed handle popup");
		
		//****************************************************************************************
		//Step 02 - handle pop-up
		//declare notification iframe
		List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		int notificationIframeSize = notificationIframe.size();
		if (notificationIframeSize == 1) {
		System.out.println("Notification iframe is displayed");
		} else 
		{System.out.println("Notification iframe is NOT displayed");}
		
		// size >0 means popup appears
		if (notificationIframeSize > 0) {
			
			//switch to iframe
			driver.switchTo().frame(notificationIframe.get(0));
			
			//Verify image of pop-up is displayed
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
			
			//click close button to close popup
			// Use Click of javascript
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='div-close']")));
			System.out.println("Close popup successfully");
			
			//switch back to Top-windows
			driver.switchTo().defaultContent();				
		}
		System.out.println("Passed handle popup");
		
		
		String parentID = driver.getWindowHandle();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Step 03 - Click Angri link -> open new tab/window -> Switch to new tab/window
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		swithToWindows_byTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		//Step 04 - click Account Details link -> open new tab/window -> Switch to new tab/window
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		swithToWindows_byTitle("Welcome to HDFC Bank NetBanking");

		
		//Step 05 - click Privacy Policy link -> open new tab/window -> Switch to new tab/window
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		swithToWindows_byTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		
		//Step 06- Click CSR link on Privacy Policy page
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		
		//Step 07- Close all windows except parent
		Assert.assertTrue(closeAllExceptParentWindow(parentID));
	}
	
	@Test
	
	public void TC_03_Multiple_Windows_Tabs_GURU99() {
		
		//Step 01 - Go to Guru page
		driver.get("http://live.guru99.com/index.php/");
		
		//Step 02 - Click on Mobile tab then get ID of page
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		String mobilePageID = driver.getWindowHandle();
		
		//Step 03 - Add Sony Xperia to compare list
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		
		//Step 04 - Add Samsung galaxy to compare list
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		
		//Step 05 - Click to Compare button
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		//Step 06 - Switch to new window
		switchToChildWindows_byID(mobilePageID);
		String compareTitle = driver.getTitle();
		
		//Step 07 - Verify title of new window is Products Comparison List - Magento Commerce
		Assert.assertEquals(compareTitle, "Products Comparison List - Magento Commerce");
		
		//Step 08 - Close tab and comback Mobile page
		closeAllExceptParentWindow(mobilePageID);
		driver.switchTo().window(mobilePageID);		
		
	}
	

	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	//Swich to child windows using ID, apply for 2 windows/tabs only
	public void switchToChildWindows_byID (String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if(!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
		
	}
	
	public void swithToWindows_byTitle(String title) {
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWindowTitle = driver.getTitle();
			if(currentWindowTitle.equals(title)) {
				break;
			}
		
			
		}
		
	}
	public boolean closeAllExceptParentWindow (String parentWindows) {
		Set <String> allWindows = driver.getWindowHandles();
		
		//close all windows except parent windows
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindows)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindows);
		if (driver.getWindowHandles().size()==1) {
			return true;
		}
		else {
			return false;
		}
	}

		
}