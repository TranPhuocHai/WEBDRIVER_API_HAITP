package selenium;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css {
    WebDriver driver;
    String firstName = "HaiAuto";
    String middleName = "TranMation";
    String lastName = "API";
    String password = "123456789";
    String emailAddress = "haitranautomation" + randomNumber() + "@gmail.com";


	@BeforeTest
	public void beforeTest() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		driver.get("http://live.guru99.com/");
		Thread.sleep(5000);
		
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
	
	
	@Test
	public void TC_05_Register_Account() throws Exception {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middleName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);		
		driver.findElement(By.xpath("//button[@title='Register']")).click();			
		String Congrat_message = driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span")).getText();
		Assert.assertEquals(Congrat_message, "Thank you for registering with Main Website Store.");				
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[contains(text(),'Account')]")).click();			
		Thread.sleep(1000);		
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();				
		Assert.assertTrue(driver.findElement(By.xpath("//div [@class='page-title']//img[contains(@src,'logo.png')]")).isDisplayed());		
				
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(99999);
		System.out.println("Random Number for email is:" + number);
		return number;
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}