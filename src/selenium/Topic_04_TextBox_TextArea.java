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
	
	//Random Number
	public int randomNumber() {
		Random ran = new Random();
		int number = ran.nextInt(999999);
		return number;
	}
	
	//declare variable
	String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password, customerID;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	
	
	//Locate Element of input data for creating new customer
	By customerNameTexbox = By.xpath("//input[@name='name']");
	By genderRadio = By.xpath("//input[@value='m']");
	By dayOfBirthTextbox = By.xpath("//input[@name='dob']");
	By addressTextarea = By.xpath("//textarea[@name='addr']");
	By cityTexbox = By.xpath("//input[@name='city']");
	By stateTexbox = By.xpath("//input[@name='state']");
	By pinTexbox = By.xpath("//input[@name='pinno']");
	By phoneTexbox = By.xpath("//input[@name='telephoneno']");
	By emailTexbox = By.xpath("//input[@name='emailid']");
	By passwordTexbox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@value='Submit']");
	
	//Locate Element of output data. Output Element used for both cases: Create and Edit
	By outputCustomerName = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By outputGender = By.xpath("//td[text()='Gender']/following-sibling::td");
	By outputDateOfBirth = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By outputAddress = By.xpath("//td[text()='Address']/following-sibling::td");
	By outputCity = By.xpath("//td[text()='City']/following-sibling::td");
	By outputState = By.xpath("//td[text()='State']/following-sibling::td");
	By outputPin = By.xpath("//td[text()='Pin']/following-sibling::td");
	By outputPhone = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By outputEmail = By.xpath("//td[text()='Email']/following-sibling::td");
	
	//Locate Element of edit data for editing customer
	By editAddressTextarea = By.xpath("//textarea[@name='addr']");
	By editCityTexbox = By.xpath("//input[@name='city']");
	By editStateTexbox = By.xpath("//input[@name='state']");
	By editPinTexbox = By.xpath("//input[@name='pinno']");
	By editPhoneTexbox = By.xpath("//input[@name='telephoneno']");
	By editEmailTexbox = By.xpath("//input[@name='emailid']");
	By editSubmitButton = By.xpath("//input[@value='Submit']");

	
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://demo.guru99.com/v4/");
	  
	  //Prepare data test for creating new customer
	  customerName = "Tran Phuoc Hai"; 
	  gender = "male"; 
	  dateOfBirth = "1988-07-31"; 
	  address = "100 Ho Guom"; 
	  city = "Ha Noi"; 
	  state = "Hoan Kiem"; 
	  pin = "600000"; 
	  phone = "0987654321"; 
	  email = "haitran" + randomNumber() + "@gmail.com" ;
	  password = "idonknow12345678";
	  
	  //Prepare data test for editing customer
	  editAddress = "100 Nguyen Van Linh"; 
	  editCity = "Da Nang"; 
	  editState = "Hai Chau"; 
	  editPin = "550000"; 
	  editPhone = "0123456789"; 
	  editEmail = "tranphuochai" + randomNumber() + "@gmail.com" ;

	  
	  
  }	
	
  @Test
  public void TC_01_CreateNewCustomer() {
	  // Login to HomePage with Valid account	  
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358 ");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp ");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  //Verify login successfully
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());	  
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  //Send data to create new customer
	  driver.findElement(customerNameTexbox).sendKeys(customerName);
	  driver.findElement(genderRadio).click();
	  driver.findElement(dayOfBirthTextbox).sendKeys(dateOfBirth);
	  driver.findElement(addressTextarea).sendKeys(address);
	  driver.findElement(cityTexbox).sendKeys(city);
	  driver.findElement(stateTexbox).sendKeys(state);
	  driver.findElement(pinTexbox).sendKeys(pin);
	  driver.findElement(phoneTexbox).sendKeys(phone);
	  driver.findElement(emailTexbox).sendKeys(email);
	  driver.findElement(passwordTexbox).sendKeys(password);
	  driver.findElement(submitButton).click();
	  
	  //Verify Registered Customer page is displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
	  
	  //Get Customer ID
	  customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  System.out.println("Customer ID has just created is: " + customerID);
	  
	  //Verify actual result = expected result
	  Assert.assertEquals(driver.findElement(outputCustomerName).getText(), customerName);
	  Assert.assertEquals(driver.findElement(outputGender).getText(), gender);
	  Assert.assertEquals(driver.findElement(outputDateOfBirth).getText(), dateOfBirth);
	  Assert.assertEquals(driver.findElement(outputAddress).getText(), address);
	  Assert.assertEquals(driver.findElement(outputCity).getText(), city);
	  Assert.assertEquals(driver.findElement(outputState).getText(), state);
	  Assert.assertEquals(driver.findElement(outputPin).getText(), pin);
	  Assert.assertEquals(driver.findElement(outputPhone).getText(), phone);
	  Assert.assertEquals(driver.findElement(outputEmail).getText(), email);

  }
  
  @Test
  public void TC_02_EditCustomer() {
	  driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  
	  //Send data to edit customer
	  driver.findElement(editAddressTextarea).clear();
	  driver.findElement(editAddressTextarea).sendKeys(editAddress);
	  driver.findElement(editCityTexbox).clear();
	  driver.findElement(editCityTexbox).sendKeys(editCity);
	  driver.findElement(editStateTexbox).clear();  
	  driver.findElement(editStateTexbox).sendKeys(editState);	  
	  driver.findElement(editPinTexbox).clear();
	  driver.findElement(editPinTexbox).sendKeys(editPin);
	  driver.findElement(editPhoneTexbox).clear();
	  driver.findElement(editPhoneTexbox).sendKeys(editPhone);
	  driver.findElement(editEmailTexbox).clear();
	  driver.findElement(editEmailTexbox).sendKeys(editEmail);
	  driver.findElement(editSubmitButton).click();
	  
	  //Verify Edit Customer page is displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
	  
	  //Verify actual result = expected result
	  Assert.assertEquals(driver.findElement(outputAddress).getText(), editAddress);
	  Assert.assertEquals(driver.findElement(outputCity).getText(), editCity);
	  Assert.assertEquals(driver.findElement(outputState).getText(), editState);
	  Assert.assertEquals(driver.findElement(outputPin).getText(), editPin);
	  Assert.assertEquals(driver.findElement(outputPhone).getText(), editPhone);
	  Assert.assertEquals(driver.findElement(outputEmail).getText(), editEmail);
	  
  }

  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
