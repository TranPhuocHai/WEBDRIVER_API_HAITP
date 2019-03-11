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
    
    // declare variable
    String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password, customerID;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
    
    //Element for create new customer
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
    
    //Element for edit customer
    By editAddressTextArea = By.xpath("//textarea[@name='addr']");
    By editCityTextbox = By.xpath("//input[@name='city']");
    By editStateTextbox = By.xpath("//input[@name='state']");
    By editPinTextbox = By.xpath("//input[@name='pinno']");
    By editPhoneTextbox = By.xpath("//input[@name='telephoneno']");
    By editEmailTextbox = By.xpath("//input[@name='emailid']");
    By editSubmitButton = By.xpath("//input[@name='sub']");


	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		driver.get("http://demo.guru99.com/v4/");
		
		//mapping data test for create customer
		customerName = "Tran Phuoc Hai";
		gender = "male";
		dateOfBirth =  "1988-07-31";
		address =  "01 Cau Rong Song Han";
		city = "TP Ho Chi Minh";
		state = "Quan Ba";
		pin = "700000";
		phone = "0987654321";
		email = "tranphuochai" + randomNumber() + "@gmail.com";
		password = "123456";
		
		//mapping data test for edit customer

		editAddress =  "01 Nguyen Van Linh";
		editCity = "Da Nang";
		editState = "Hai Chau";
		editPin = "550000";
		editPhone = "0123456789";
		editEmail = "tranphuochai" + randomNumber() + "@gmail.com";

		
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		
		// Log-in with valid account
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358 ");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		//Verify reaching HomePage after logging in
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		
		//send data test and submit
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
		
		//Verify actual results = expected results
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
	
	@Test
	public void TC_02_EditCustomer() throws Exception {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		//send edit data test and submit
		
		driver.findElement(editAddressTextArea).clear();
		driver.findElement(editAddressTextArea).sendKeys(editAddress);
		
		driver.findElement(editCityTextbox).clear();
		driver.findElement(editCityTextbox).sendKeys(editCity);		
		driver.findElement(editStateTextbox).clear();
		driver.findElement(editStateTextbox).sendKeys(editState);		
		driver.findElement(editPinTextbox).clear();
		driver.findElement(editPinTextbox).sendKeys(editPin);
		driver.findElement(editPhoneTextbox).clear();
		driver.findElement(editPhoneTextbox).sendKeys(editPhone);
		driver.findElement(editEmailTextbox).clear();
		driver.findElement(editEmailTextbox).sendKeys(editEmail);
		driver.findElement(editSubmitButton).click();
		Thread.sleep(3000);
		
		//Verify edit successfully Customer page is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
		
		//Verify actual results = expected results
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);

	}

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
