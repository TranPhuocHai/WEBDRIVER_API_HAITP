package selenium;

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

public class Topic_05_Button_Radio_Checkbox_Alert {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 

	}

	@Test (enabled = false)
	public void TC_01_Button_JSclick() {
		
		driver.get("http://live.guru99.com/");
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']"));
		jsExecutor.executeScript("arguments[0].click();", myAccountLink);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
		WebElement CreateAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		jsExecutor.executeScript("arguments[0].click();", CreateAccountLink);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	
	@Test (enabled = true)
	public void TC_02_KendoUi_CheckBox() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		WebElement DualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		jsExecutor.executeScript("arguments[0].click();", DualZoneCheckbox);	
		Assert.assertTrue(DualZoneCheckbox.isSelected());
		Thread.sleep(2000);
		
		jsExecutor.executeScript("arguments[0].click();", DualZoneCheckbox);	
		Assert.assertFalse(DualZoneCheckbox.isSelected());
		Thread.sleep(2000);
		
	}
	


	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}