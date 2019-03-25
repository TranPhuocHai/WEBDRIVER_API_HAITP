package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_Frame_Iframe_Popup {
    WebDriver driver;


	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
	}

	@Test
	public void TC_01() {
		driver.get("https://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//declare notification iframe
		List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		int notificationIframeSize = notificationIframe.size();
		System.out.println("");
		
		// size >0 means popup appears
		if (notificationIframeSize > 0) {
			
			//switch to iframe
			driver.switchTo().frame(notificationIframe.get(0));
			
			//Verify image of pop-up is displayed
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
			
			//click close button to close popup
			driver.findElement(By.xpath("//div[@id='div-close']")).click();	
			System.out.println("Close popup successfully");
			
			//switch back to Top-windows
			driver.switchTo().defaultContent();				
		}
		System.out.println("Passed handle popup");
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[contains(@id,'viz_iframe')] ")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
		
		driver.switchTo().defaultContent();	
		
		WebElement slidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(slidingBannerIframe);
		
		List <WebElement> slidingBannerImg = driver.findElements(By.xpath("//div[@id='productcontainer']//img"));
		Assert.assertEquals(slidingBannerImg.size(), 6);		
		//Check all images loaded successfully
		for (int i=0; i<= slidingBannerImg.size(); i++) {
			if(isImageLoadedSuccess(slidingBannerImg.get(i))) {
				System.out.println("Sliding banner Image " +(i+1)+ " loaded sucessfully");
			}
			
		}
		
		driver.switchTo().defaultContent();	
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner'])")).isDisplayed());		
		
		List <WebElement> flipBannerImg = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));

		Assert.assertEquals(flipBannerImg.size(), 8);
		//Check all images loaded successfully
		for (int i=0; i<= flipBannerImg.size(); i++) {
			if(isImageLoadedSuccess(flipBannerImg.get(i)) && flipBannerImg.get(i).isDisplayed()) {
				System.out.println("Flip banner Image " +(i+1)+ " loaded sucessfully");
			}
			
		}
		
		
	}
	
	public boolean isImageLoadedSuccess (WebElement loaded) {		
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				loaded);
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}