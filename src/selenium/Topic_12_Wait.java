package selenium;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_12_Wait {
    WebDriver driver;
    WebDriverWait waitExplicit;
    
    By StartButton = By.xpath("//button[text()='Start']");
    By LoadingBar = By.xpath("//div[contains(text(),'Loading')]");
    By HelloWorldText = By.xpath("//h4[text()='Hello World!']");

    @BeforeTest
	public void beforeTest() {
	System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe"); driver = new ChromeDriver();
//	System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver.exe"); driver = new FirefoxDriver();
//	System.setProperty("webdriver.ie.driver",".\\lib\\IEDriverServer.exe"); driver = new InternetExplorerDriver();

	}

/* Topic_01 +Check "Hello World!" text visible 
 * In this case, Hello World text Element is N/A in DOM (it's only visible after clicking Start Button 5s)
 * */
@Test (enabled = false)
	public void TC_01_Expli_4S_Impli_1_5_S_Visibility() {
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 4);
		System.out.println("TC_01_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_01_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
	}
	
	@Test (enabled = false)
	public void TC_02_Expli_4S_Impli_2S_Visibility() {
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 4);
		System.out.println("TC_02_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_02_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
	}
	
	@Test (enabled = false)
	public void TC_03_Expli_4S_Impli_3S_Visibility() {
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 4);
		System.out.println("TC_03_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_03_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
	}
	
	@Test (enabled = false)
	public void TC_04_Only_ImplicitWait_2S_FAILED() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(StartButton).click();
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");
		
	}
	
	@Test (enabled = false)
	public void TC_05_Only_ImplicitWait_5S_PASSED() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");

	}

/* Topic_02: Check Loading bar invisible before Hello World! text displays 
 * In this case, check Loading bar is available in DOM so Implicit does not affect to wait, only Explicit
 * */
	@Test (enabled = false)
	public void TC_06_FAILED() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 2);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar));
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");
		
	}
	
	@Test (enabled = false)
	public void TC_07_PASSED() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 5);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar));
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");
		
	}
	
	@Test (enabled = false)
	public void TC_08_FAILED() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 4);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar));
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");

	}

	/* Topic_03 
	 * */
	@Test (enabled = false)
	public void TC_09_Expli_2S_Impli_5S_PASSED() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 2);
		System.out.println("TC_09_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_09_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		Assert.assertTrue(driver.findElement(HelloWorldText).isDisplayed());
	}
	
	@Test (enabled = false)
		public void TC_10_Expli_2S_Impli_3S_FALED() {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
			driver.findElement(StartButton).click();
			waitExplicit = new WebDriverWait(driver, 2);
			System.out.println("TC_10_Wait starts:"+new Date());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
			System.out.println("TC_10_Wait ends:"+new Date());
			System.out.println("-----------------------------------------------------");
			Assert.assertTrue(driver.findElement(HelloWorldText).isDisplayed());
		}
	
	/* Topic_04
	 * */
	@Test (enabled = false)
	public void TC_11_ExplicitWait_Hello_Invisible_NOTAvailableInDOM(){
		
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		waitExplicit = new WebDriverWait(driver, 1);
		
		driver.findElement(StartButton).click();
		//Check Time of Hello World Text Invisible but NOT available in DOM
		System.out.println("TC_11_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_11_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
		/*If Implicit <5, TC always PASSED
		 * If Implicit >5, TC always FAILED
		 * */
		
	}
	
	@Test (enabled = false)
	public void TC_12_ExplicitWait_Hello_Visible_AvailableInDOM(){
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		waitExplicit = new WebDriverWait(driver, 5);
		
		driver.findElement(StartButton).click();
		//Check Time of Loading Bar invisible
		System.out.println("TC_12_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar)); 
		System.out.println("TC_12_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
		//Check Time of Hello World Text Invisible but Available in DOM
		System.out.println("TC_12_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_12_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
		/*Hello world Text is checked by ExplicitWait only, in this case Test Case always FAILED 
		 * 
		 * */ 
		
	}
	
	@Test (enabled = false)
	public void TC_13_ExplicitWait_Test04_PASSED(){
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		waitExplicit = new WebDriverWait(driver, 5);
		//Check Time of Hello World Text Invisible -> Only Impplicit time max 10s
		System.out.println("TC_13_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_13_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		//Check Time of Loading Bar invisible-> Only Impplicit time max 10s
		System.out.println("TC_13_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar)); 
		System.out.println("TC_13_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
		driver.findElement(StartButton).click();
		//Check Time of Loading Bar invisible -> Only Explicit 5S, so failed if explicit <5S
		System.out.println("TC_13_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar)); 
		System.out.println("TC_13_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
		//Check Time of Loading Bar invisible -> Explicit 0S, because start button does not available anymore
		System.out.println("TC_13_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(StartButton)); 
		System.out.println("TC_13_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
		
	}
	
	@Test (enabled = false)
	public void TC_14_ExplicitWait_Test04_PASSED() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		waitExplicit = new WebDriverWait(driver, 5);
		//Check Time of Hello World Text Invisible -> Only Impplicit time max 10s
		System.out.println("TC_14_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_14_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		//Check Time of Loading Bar invisible-> Only Impplicit time max 10s
		System.out.println("TC_14_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar)); 
		System.out.println("TC_14_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
				
		driver.findElement(StartButton).click();
		//Check Time of Loading Bar invisible -> Only Explicit 5S, so failed if explicit <5S
		System.out.println("TC_14_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar)); 
		System.out.println("TC_14_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		
		//Check Time of Loading Bar invisible -> Explicit 0S, because start button does not available anymore
		System.out.println("TC_14_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(StartButton)); 
		System.out.println("TC_14_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
	
		
	}
	
	/*Full Steps with normal case follow flow
	 * -> click on Start -> wait for Loading bar disappear (after 5s)
	 * -> verify Hello World visible
	 * */
	
	@Test (enabled = false)
	public void TC_15_Full() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		waitExplicit = new WebDriverWait(driver, 5);
		driver.findElement(StartButton).click();
		System.out.println("TC_15_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(LoadingBar)); //0s
		System.out.println("TC_15_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		//Wait for Loading bar disappear
		System.out.println("TC_15_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar)); //5S
		System.out.println("TC_15_Wait ends:"+new Date());
		System.out.println("-----------------------------------------------------");
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");	
		
	}
	
	@Test (enabled = true)
	public void TC_16_Excercise_05() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		waitExplicit = new WebDriverWait(driver, 5);
		
		By DateTimePicker = By.xpath("//span[@class='rcTitle']");
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(DateTimePicker));
		
		By SelectDateText = By.xpath("//legend[text()='Selected Dates:']/following-sibling::div[@class='RadAjaxPanel']/span");
		System.out.println(driver.findElement(SelectDateText).getText());	
		
		By CurentDatePicked = By.xpath("//td[contains(@title,'" + getCurrentDate() + "')]");	
		driver.findElement(CurentDatePicked).click();
		
		By loaderAjax = By.xpath("//div[@class='raDiv']");
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loaderAjax));

		By selectedDate = By.xpath("//td[contains(@class,'rcSelected')]//a[text()='" + getCurrentDayofMonth() + "']");
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(selectedDate));
		
		Assert.assertEquals(driver.findElement(SelectDateText).getText(), getCurrentDate());
		System.out.println("Current Date is: " + getCurrentDate());		
	}	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public String getCurrentWeekDay(){		
		String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};		
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);		
		return daysArray[weekday-1];		
	}
	
	public String getCurrentMonth(){		
		String monthsArray[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};		
		Calendar calendar = Calendar.getInstance();
		int monthInText = calendar.get(Calendar.MONTH);		
		return monthsArray[monthInText];		
	}
	
	public String getCurrentDayofMonth(){		
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {String realday = ("0"+day); return realday;}	
		else {String realday = String.valueOf(day); return realday;}		
	}	
	
	public String getCurrentDate(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String date = (getCurrentWeekDay() + ", " + getCurrentMonth() + " " + getCurrentDayofMonth() + ", " + year);
	    return date;
	}

}
























