package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04_Part2_DropDown {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascriptExecutor;
	
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  waitExplicit = new WebDriverWait(driver, 30);
	  javascriptExecutor = (JavascriptExecutor) driver;
	  	  
	  }
  
  @Test
  public void TC_01_Default_Dropdown () throws Exception {	  
  
  driver.get("https://daominhdam.github.io/basic-form/index.html");
  
  WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
  Select jobRoleSelect = new Select(jobRole1);
 
  Assert.assertFalse(jobRoleSelect.isMultiple()); 
  
  jobRoleSelect.selectByVisibleText("Automation Tester");
  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Automation Tester"); 
  Thread.sleep(2000);
  
  jobRoleSelect.selectByValue("manual");
  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Manual Tester");
  Thread.sleep(2000);
  
  jobRoleSelect.selectByIndex(3); 
  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Mobile Tester");
  Assert.assertEquals(jobRoleSelect.getOptions().size(),5); 
  Thread.sleep(2000);

  
  }
  
  @Test
  public void TC_02_Custom_Dropdown () {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

	  selectItem_In_CustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "19");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']"));
	  
	  selectItem_In_CustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "18");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='18']"));
	  
	  selectItem_In_CustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "2");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='2']"));
	  
	  selectItem_In_CustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "4");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='4']"));

  }
  
  public void selectItem_In_CustomDropdown (String parent_Xpath, String all_Item_Xpath, String expected_Item) {

	  WebElement parentDropdown = driver.findElement(By.xpath(parent_Xpath));
	  parentDropdown.click();
	  
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(all_Item_Xpath)));
	  List <WebElement> all_Item = driver.findElements(By.xpath(all_Item_Xpath));
	  
	  for (int i = 0; i < all_Item.size(); i++) {
		  if (all_Item.get(i).getText().equals(expected_Item)) {
			  System.out.println("Each Time get Text: " + all_Item.get(i).getText());
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", all_Item.get(i));
			  all_Item.get(i).click();
			  break;
		  }
	  }

	  
	    }
  
  public boolean isElementDisplayed (String valueXpath) {
	  WebElement element = driver.findElement(By.xpath(valueXpath));
	  if (element.isDisplayed()) {
		  System.out.println("item is displayed successfully");
		 		  return true;
	  }
	  else {
		  System.out.println("item is not displayed");
		  return false;
	  }
	  
  }
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
