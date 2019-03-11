package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04_TextBox_TextArea {
    WebDriver driver;
    
    // variable
    String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password, customerID;
    
    //Element
    By customerNameTextbox = By.xpath("//input[@name='name']");
    By genderRadioButton = By.xpath("//input[@value='m']");
    By dayOfBirthTextbox = By.xpath("//input[@name='dob']");
    By addressTextArea = By.xpath("//textarea[@name='addr']");
    By cityTextbox = By.xpath("//input[@name='city']");
    By stateTextbox = By.xpath("//input[@name='state']");
    By pinTextbox = By.xpath("//input[@name='pinno']");
    By phoneTextbox = By.xpath("//input[@name='telephoneno']");
    By emailTextbox = By.xpath("//input[@name='emailid']");
    By passwordTextbox = By.xpath("//input[@name='password']");
    By submitButton = By.xpath("//input[@name='sub']");
    


	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		driver.get("http://demo.guru99.com/v4/");
		
		//mapping data test
		customerName = "Selenium Testing Online";
		gender = "male";
		dateOfBirth =  "2000-10-01";
		address =  "123 Cau Rong Song Han";
		city = "Danang";
		state = "Hai Chau";
		pin = "123456";
		phone = "0987654321";
		email = "automation" + randomNumber() + "@gmail.com";
		password = "123456";
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		
		// log-in
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358 ");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		//Verify reaching HomePage after logging in
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		
		//send data test
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadioButton).click();
		driver.findElement(dayOfBirthTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();		
		
		//Verify Registered Customer page is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		//Verify info output = info input
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID in TC_01 is: " +customerID);
		
		

	}
	
//	@Test
//	public void TC_02_() {
//
//	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(99999);
				return number;
	}

}
