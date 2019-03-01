package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css {
    WebDriver driver;


	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		driver.get("http://live.guru99.com/");
		
	}

	@Test
	public void TC_01_Login_Empty() throws Exception {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailRequired = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailRequired, "This is a required field.");
		
		
		String passRequired = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passRequired, "This is a required field.");
	}
	
	@Test
	public void TC_02_Login_With_Email_Invalid() throws Exception {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailAdvice = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailAdvice, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_Login_With_PW_Lessthan_6Characters() throws Exception {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String pwAdvice = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(pwAdvice, "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void TC_04_Login_With_PW_Incorrect() throws Exception {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String invalidPW = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(invalidPW, "Invalid login or password.");
		
	}
	


	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}