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
		Assert.assertEquals(driver.getCurrentUrl(), "http://http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	@Test (enabled = false)
	public void TC_01_Button_SeleniumClick() {
		
		driver.get("http://live.guru99.com/");
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']"));
		myAccountLink.click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
				
		WebElement CreateAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		CreateAccountLink.click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	@Test (enabled = true)
	public void TC_02() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		By DualZoneXpath = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		WebElement DualZoneCheckbox = driver.findElement(DualZoneXpath);
		jsExecutor.executeScript("agruments[0].click();" , DualZoneCheckbox);
		isElementSelected(DualZoneXpath);
		
	}
	
	public boolean isElementSelected(By byValue) {
		if (driver.findElement(byValue).isSelected()){
			return true;			
		} else {
			return false;
		}
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}