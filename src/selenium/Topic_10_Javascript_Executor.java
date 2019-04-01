package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_10_Javascript_Executor {
    WebDriver driver;

    @BeforeTest
	public void beforeTest() {	

		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
    
  

	@Test (enabled = true)
	public void TC_01_Javascript_Excecutor_JE () {
		navigateToUrlByJS("http://live.guru99.com/");
		String domain = (String) executeForBrowser("return document.domain");
		System.out.println("Domain: " + domain);
		Assert.assertEquals(domain, "live.guru99.com");
		
		String URL = (String) executeForBrowser("return document.URL");
		System.out.println("URL: " + URL);
		Assert.assertEquals(URL, "http://live.guru99.com/");
		
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		clickToElementByJS(mobileLink);
		
		WebElement SamsungAddToCart = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		highlightElement(SamsungAddToCart);
		clickToElementByJS(SamsungAddToCart);
		
		String SamsunginnerText = driver.findElement(By.xpath("//span[text()='Samsung Galaxy was added to your shopping cart.']")).getText();
		String pageinnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageinnerText.contains(SamsunginnerText));
		
		WebElement PrivacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement(PrivacyLink);
		clickToElementByJS(PrivacyLink);
		
		String PrivacyTitle = (String) executeForBrowser("return document.title");
		System.out.println("PrivacyTitle: " + PrivacyTitle);
		Assert.assertEquals(PrivacyTitle, "Privacy Policy");
		
		scrollToBottomPage();
		
		Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']")).isDisplayed());
		
	}
	
	@Test (enabled = true)
	public void TC_02_Remove_Attribute() throws Exception{
		//Step 01: Go to main page
		driver.get("http://demo.guru99.com/v4/");
		
		// Step 2 Log-in by valid account to main page
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr187515");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ybEzebe");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
			//Verify login successfully
			Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());	  
		
		//Step 03 - Click on new customer
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		
		//Step 04 - input all valid data
		
		//declare variable
		String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password, customerID;
		String dateOfBirth_out,dd, mm, yyyy;
		
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
		
		//Prepare data test for creating new customer
		  customerName = "Tran Phuoc Hai"; 
		  gender = "male"; 
		  
		  dd = "31"; mm = "07"; yyyy = "1988";		  
		  dateOfBirth = mm+"/"+dd+"/"+yyyy ;	
		  System.out.println("dateOfBirth is: " + dateOfBirth);
		  dateOfBirth_out = yyyy+"-"+mm+"-"+dd;
		  System.out.println("dateOfBirth output is: " + dateOfBirth_out);
		  
		  address = "100 Ho Guom"; 
		  city = "Ha Noi"; 
		  state = "Hoan Kiem"; 
		  pin = "600000"; 
		  phone = "0987654321"; 
		  email = "tranphuochai" + randomNumber() + "@gmail.com" ;
		  password = "automationtesting2019";
		  
		  
		  //Send data to create new customer
		  driver.findElement(customerNameTexbox).sendKeys(customerName);
		  driver.findElement(genderRadio).click();
		  
		  /* ------------------------------ */
		  /*  REMOVE ATTRIBUTE */
		  WebElement doBElement = driver.findElement(dayOfBirthTextbox);
		  highlightElement(doBElement);
		  removeAttributeInDOM(doBElement, "type");
		  Thread.sleep(3000);
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
	
	@Test (enabled = true)
	public void TC_03_Create_An_Account() {
		
	}


	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	
	
	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Object executeForBrowser(String javaSript) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
    }

    public Object clickToElementByJS(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
    }

    public Object sendkeyToElementByJS(WebElement element, String value) {
           JavascriptExecutor js = (JavascriptExecutor) driver;
           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public Object removeAttributeInDOM(WebElement element, String attribute) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
    }

    public Object scrollToBottomPage() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public Object navigateToUrlByJS(String url) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
    }
    
	public int randomNumber() {
		Random ran = new Random();
		int number = ran.nextInt(999999);
		return number;
	}

}