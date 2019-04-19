package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestNG_02_MultiBrowser {
WebDriver driver;
	
	
	//declare variable
	String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password, customerID;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	String dd, mm, yyyy, dateOfBirth_out;
	
	
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


  @Parameters("browser")	
	
  @BeforeTest
  public void preCondition(String browserName) {
	  
	  if (browserName.equals("chrome")) {
		  
		  System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
		  driver = new ChromeDriver();		
		  
	  } else if (browserName.equals("firefox")) {	
		  
		  System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver.exe");
		  driver = new FirefoxDriver();
	  } else if (browserName.equals("ie")){
		  
		  System.setProperty("webdriver.ie.driver",".\\lib\\IEDriverServer.exe");
		  driver = new InternetExplorerDriver();
		  
	  }		


		
	  driver.get("http://demo.guru99.com/v4/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
		//Prepare data test 
		customerName = "Hai Auto"; 
		gender = "male"; 
		
		dd = "31"; mm = "07"; yyyy = "1988";		  
		dateOfBirth = mm+"/"+dd+"/"+yyyy ;	
		dateOfBirth_out = yyyy+"-"+mm+"-"+dd;
		
		address = "100 Ho Guom"; 
		city = "Ha Noi"; 
		state = "Hoan Kiem"; 
		pin = "600000"; 
		phone = "0987654321"; 
		email = "tranph07" + randomNumber() + "@gmail.com" ;
		password = "autotest2019";
	  
	  
	  //Prepare data test for editing customer
	  editAddress = "100 Nguyen Van Linh"; 
	  editCity = "Da Nang"; 
	  editState = "Hai Chau"; 
	  editPin = "550000"; 
	  editPhone = "0123456789"; 
	  editEmail = "hait31" + randomNumber() + "@gmail.com" ;	  
  }	  

  
  @Test
  public void TC_01_CreateNewCustomer() {
	  // Login to HomePage with Valid account	  
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr187515");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ybEzebe");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  //Verify login successfully
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());	  
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  //Send data to create new customer
	  driver.findElement(customerNameTexbox).sendKeys(customerName);
	  driver.findElement(genderRadio).click();	  
	  
	  /* ------------------------------ */
	  /*  REMOVE ATTRIBUTE */
	  WebElement doBElement = driver.findElement(dayOfBirthTextbox);
	  removeAttributeInDOM(doBElement, "type");
	  driver.findElement(dayOfBirthTextbox).sendKeys(dateOfBirth);
	  /* ------------------------------ */
	  
	  
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
	  Assert.assertEquals(driver.findElement(outputDateOfBirth).getText(), dateOfBirth_out);
	  Assert.assertEquals(driver.findElement(outputAddress).getText(), address);
	  Assert.assertEquals(driver.findElement(outputCity).getText(), city);
	  Assert.assertEquals(driver.findElement(outputState).getText(), state);
	  Assert.assertEquals(driver.findElement(outputPin).getText(), pin);
	  Assert.assertEquals(driver.findElement(outputPhone).getText(), phone);
	  Assert.assertEquals(driver.findElement(outputEmail).getText(), email);

  }
  
  @Test (enabled = true, dependsOnMethods ="TC_01_CreateNewCustomer")
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

  @Test(dependsOnMethods ="TC_02_EditCustomer")
  public void TC_03_deleteCustomer() throws Exception {
	  driver.findElement(By.xpath("//a[text()='Delete Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  
	  Thread.sleep(1000);
	  if(!driver.toString().contains("internet explorer")) {
		  String alertText = driver.switchTo().alert().getText();	
		  Assert.assertEquals(alertText, "Do you really want to delete this Customer?");
		  System.out.println(alertText);
		  driver.switchTo().alert().accept();		  
	  } else {
		  driver.switchTo().alert().accept();		  
	  }
	  
	  Thread.sleep(1000);
	  if(!driver.toString().contains("internet explorer")) {
	  String alertText = driver.switchTo().alert().getText();	
	  Assert.assertEquals(alertText, "Customer deleted Successfully");
	  System.out.println(alertText);
	  driver.switchTo().alert().accept();		  
	  } else {
		  driver.switchTo().alert().accept();		  
	  }
  	  
	  
	  driver.findElement(By.xpath("//a[text()='Delete Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  
	  Thread.sleep(1000);
	  if(!driver.toString().contains("internet explorer")) {
		  String alertText = driver.switchTo().alert().getText();	
		  Assert.assertEquals(alertText, "Do you really want to delete this Customer?");
		  System.out.println(alertText);
		  driver.switchTo().alert().accept();		  
	  } else {
		  driver.switchTo().alert().accept();		  
	  }  	  
	  
	  Thread.sleep(1000);
	  if(!driver.toString().contains("internet explorer")) {
	  String alertText = driver.switchTo().alert().getText();	
	  Assert.assertEquals(alertText, "Customer does not exist!!");
	  System.out.println(alertText);
	  driver.switchTo().alert().accept();		  
	  } else {
		  driver.switchTo().alert().accept();		  
	  }
  }
  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  public Object removeAttributeInDOM(WebElement element, String attribute) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
}

	public int randomNumber() {
		Random ran = new Random();
		int number = ran.nextInt(999999);
		return number;
	}

}
