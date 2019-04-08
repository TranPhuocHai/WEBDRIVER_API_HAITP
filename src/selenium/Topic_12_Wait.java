package selenium;

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
 * 	If using only Implicit Wait: at least 5s for passed result
 * 	If using Implicit
 * */
@Test (enabled = true)
	public void TC_04_Expli_4S_Impli_1_5_S_Visibility() {
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 4);
		System.out.println("TC_01_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_01_Wait ends:"+new Date());
		System.out.println("-----------------------------");
	}
	
	@Test (enabled = true)
	public void TC_02_Expli_4S_Impli_2S_Visibility() {
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 4);
		System.out.println("TC_02_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_02_Wait ends:"+new Date());
		System.out.println("-----------------------------");
	}
	
	@Test (enabled = true)
	public void TC_03_Expli_4S_Impli_3S_Visibility() {
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 4);
		System.out.println("TC_03_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_03_Wait ends:"+new Date());
		System.out.println("-----------------------------");
		
	}
	
	@Test (enabled = true)
	public void TC_04_Only_ImplicitWait_2S_FAILED() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(StartButton).click();
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");
		
	}
	
	@Test (enabled = true)
	public void TC_05_Only_ImplicitWait_5S_PASSED() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");

	}

/* Topic_02: Check Loading bar invisible before Hello World! text displays 
 * In this case, check Loading bar invisible only Explicit Wait
 * */
	@Test (enabled = true)
	public void TC_06_FAILED() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 2);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar));
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");
		
	}
	
	@Test (enabled = true)
	public void TC_07_PASSED() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 5);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingBar));
		Assert.assertEquals(driver.findElement(HelloWorldText).getText(), "Hello World!");
		
	}
	
	@Test (enabled = true)
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
	@Test (enabled = true)
	public void TC_09_Expli_2S_Impli_5S_PASSED() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit = new WebDriverWait(driver, 2);
		System.out.println("TC_01_Wait starts:"+new Date());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
		System.out.println("TC_01_Wait ends:"+new Date());
		System.out.println("-----------------------------");
		Assert.assertTrue(driver.findElement(HelloWorldText).isDisplayed());
	}
	
	@Test (enabled = true)
		public void TC_10_Expli_2S_Impli_3S_FALED() {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
			driver.findElement(StartButton).click();
			waitExplicit = new WebDriverWait(driver, 2);
			System.out.println("TC_01_Wait starts:"+new Date());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloWorldText)); 
			System.out.println("TC_01_Wait ends:"+new Date());
			System.out.println("-----------------------------");
			Assert.assertTrue(driver.findElement(HelloWorldText).isDisplayed());
		}
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	

}