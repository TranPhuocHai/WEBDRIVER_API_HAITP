package selenium;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_14_WorkingDays_Of_Any_Country {
    WebDriver driver;
    WebDriverWait waitExplicit;
	JavascriptExecutor javascriptExecutor;
    By StartDay = By.id("d1");
    By StartMonth = By.id("m1");
    By StartYear = By.id("y1");
    By EndDay = By.id("d2");
    By EndMonth = By.id("m2");
    By EndYear = By.id("y2");
    By CalculateButton = By.id("subbut");

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe"); driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		waitExplicit = new WebDriverWait(driver, 10);
		javascriptExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void WorkingDays_Of_Any_Country() throws Exception {
		driver.get("https://www.timeanddate.com/date/workdays.html");
		driver.findElement(By.id("chco2")).click();
		By selectCountry = By.xpath("//h2[text()='Set Default Country']");
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(selectCountry));
		String countryParent = "//select[@id='country']";
		String allCountry = "//select[@id='country']/option";
		String expected_Country = "United States";
		System.out.println(driver.getTitle()+"\n"+expected_Country);
		selectItem_In_CustomDropdown(countryParent, allCountry, expected_Country);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='tzq_save']")));
		driver.findElement(By.xpath("//button[@id='tzq_save']")).click();
		Calendar cal = Calendar.getInstance();
		int yearNumber = cal.get(Calendar.YEAR);
		String year = String.valueOf(yearNumber);
		int month[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		String monthArray[]= {"January","February","March","April","May","June","July","August","September","October","November","December"};

		String endday;
		
		for (int countMonth: month) {
			System.out.println("-----------------------------");
			waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(StartMonth));
			driver.findElement(StartMonth).clear();
			driver.findElement(StartMonth).sendKeys(String.valueOf(countMonth));
			driver.findElement(StartDay).clear();
			driver.findElement(StartDay).sendKeys("1");
			driver.findElement(StartYear).clear();
			driver.findElement(StartYear).sendKeys(year);
			
			if (countMonth == 1 ||countMonth == 3 ||countMonth == 5 ||countMonth == 7 ||countMonth == 8 ||countMonth == 10 ||countMonth == 12) {
				endday = "31";
			}else if(countMonth == 2 && (yearNumber % 4 != 0)){
				endday = "28";				
			}else if(countMonth == 2 && (yearNumber % 4 == 0)){
				endday = "29";				
			}else {
				endday = "30";		
			}
			
			driver.findElement(EndMonth).clear();
			driver.findElement(EndMonth).sendKeys(String.valueOf(countMonth));
			driver.findElement(EndDay).clear();
			driver.findElement(EndDay).sendKeys(endday);				
			driver.findElement(EndYear).clear();
			driver.findElement(EndYear).sendKeys(year);			
			driver.findElement(CalculateButton).click();
			
			String monthText = monthArray[countMonth-1];
			System.out.println(monthText+" "+year);
			
			By fromAndIncluding = By.xpath("//strong[contains(text(),' 1') and contains(text(),'"+monthText+"')]");
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(fromAndIncluding));
			
			String s = driver.findElement(By.xpath("//div[@class='bx-result']//h2")).getText();
			System.out.println("Working Days " +s);
		}

	}

	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	 public void selectItem_In_CustomDropdown (String parent_Xpath, String all_Item_Xpath, String expected_Item) throws Exception {
		  
		  WebElement parentDropdown = driver.findElement(By.xpath(parent_Xpath));
		  //1- click on dropdown by javascriptExecutor
		  if(parentDropdown.isDisplayed()) {
			  parentDropdown.click();
			  	  }
		  else {
		  javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
		  }
		  
		  // 2- Wait for dropdown loads all items
		  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(all_Item_Xpath)));		  
		  List <WebElement> all_Item = driver.findElements(By.xpath(all_Item_Xpath));
		  for (WebElement childElement : all_Item) {
			  if (childElement.getText().equals(expected_Item)) {			  
				  // 3 - scroll to expected item
				  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				  Thread.sleep(1000);
				  if (childElement.isDisplayed()) {
					  childElement.click();
				  } else {
					  javascriptExecutor.executeScript("arguments[0].click();", childElement);
				  }
				  Thread.sleep(1000);
				  break;
			  }
			  
		  }	  
	  }


}