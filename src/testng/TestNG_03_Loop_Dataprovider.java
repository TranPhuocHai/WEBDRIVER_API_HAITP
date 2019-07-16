package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_03_Loop_Dataprovider {
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


	
  @BeforeTest
  public void preCondition() {

	  System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();			
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  
  @DataProvider (name = "userAndPass")
  public static Object[][] userAndPassword(){
	  return new Object[][] {{"mngr187515","ybEzebe"}, {"mngr190542","udAdedA"}};
  }
  
  // Using dataaProvider and Loop 3 times
  @Test(dataProvider = "userAndPass", invocationCount = 3 )
  public void TC_00_LogInToSystem(String userName, String password) {
	  driver.get("http://demo.guru99.com/v4/");
	  // Login to HomePage with Valid account	  
	  driver.findElement(By.xpath("//input[@name='uid']")).clear();
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);
	  driver.findElement(By.xpath("//input[@name='password']")).clear();
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
//	  driver.findElement(By.xpath("//a[text()='Log out']")).click();
  }
    

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }


}
