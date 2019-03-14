package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By AgeUnder18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By jobRole1 = By.xpath("//select[@id='job1']");
	By interestsDevelopment = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonIsEnabled = By.xpath("//button[@id='button-enabled']");
	By pasword = By.xpath("//input[@id='password']");
	By AgeDisableRadio = By.xpath("//input[@id='radio-disabled']");
	By bioTextArea = By.xpath("//textarea [@id='bio']");
	By jobRole2 = By.xpath("//select[@id='job2']");
	By checkBoxDisable = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");
	
	
	@BeforeTest
	  public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	  }	
	
  @Test
  public void TC_01_Check_Element_isDisplayed() {
	  System.out.println("---TC_01_Check_Element_isDisplayed---");
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  	  
	  if(isElementDisplayed(emailTextbox)) {
		  driver.findElement(emailTextbox).sendKeys("Automation");}
	  
	  if(isElementDisplayed(AgeUnder18Radio)) {
		  driver.findElement(AgeUnder18Radio).click();}
	  
	  if(isElementDisplayed(educationTextArea)) {
		  driver.findElement(educationTextArea).sendKeys("Automation");}
		 
  }
  
  @Test
  public void TC_02_Check_Element_isEnabled() {
	  System.out.println("---TC_02_Check_Element_isEnabled---");
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  Assert.assertTrue(isElementEnabled(emailTextbox));
	  Assert.assertTrue(isElementEnabled(AgeUnder18Radio));
	  Assert.assertTrue(isElementEnabled(educationTextArea));
	  Assert.assertTrue(isElementEnabled(jobRole1));
	  Assert.assertTrue(isElementEnabled(interestsDevelopment));
	  Assert.assertTrue(isElementEnabled(slider01));
	  Assert.assertTrue(isElementEnabled(buttonIsEnabled));
	  Assert.assertFalse(isElementEnabled(AgeDisableRadio));
	  Assert.assertFalse(isElementEnabled(bioTextArea));
	  Assert.assertFalse(isElementEnabled(jobRole2));
	  Assert.assertFalse(isElementEnabled(checkBoxDisable));
	  Assert.assertFalse(isElementEnabled(slider02));
	  Assert.assertFalse(isElementEnabled(buttonDisabled));
  }
  
  @Test
  public void TC_03_Check_Element_isSelected () {
	  System.out.println("---TC_03_Check_Element_isSelected---");
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  checkToCheckbox(AgeUnder18Radio);
	  checkToCheckbox(interestsDevelopment);
	  Assert.assertTrue(isElementSelected(AgeUnder18Radio));
	  Assert.assertTrue(isElementSelected(interestsDevelopment));
	  UncheckToCheckbox(interestsDevelopment);
	  Assert.assertFalse(isElementSelected(interestsDevelopment));
	  

  }
  

  @AfterTest
  public void afterTest() {
	  driver.quit();
	  System.out.println("-----------------------------------");
  }
  
  public boolean isElementDisplayed(By byValue) {
	  if (driver.findElement(byValue).isDisplayed()) {
	  System.out.println("Element [" + byValue + "] is displayed!");
	  return true;
	  }	  else {
		  System.out.println("Element [" + byValue + "] is not displayed!");
		  return false;
	  }
  }
  
  public boolean isElementEnabled(By byValue) {
	  if(driver.findElement(byValue).isEnabled()){
		  System.out.println("Element [" + byValue + "] is enabled!");
		  return true;
	  } else {
		  System.out.println("Element [" + byValue + "] is disabled!");
		  return false;
	  }
	  
	  
  }
  
  public boolean isElementSelected(By byValue) {
	  if(driver.findElement(byValue).isSelected()){
		  System.out.println("Element [" + byValue + "] is selected!");
		  return true;
	  } else {
		 System.out.println("Element [" + byValue + "] is de-selected!");
		 return false;
	  }
	  
	  
  }
  
  public void checkToCheckbox(By byValue) {
	  WebElement element = driver.findElement(byValue);
	  if (!element.isSelected()) {
		  element.click();
	  }
	  
  }
  
  public void UncheckToCheckbox(By byValue) {
	  WebElement element = driver.findElement(byValue);
	  if (element.isSelected()) {
		  element.click();
	  }
	  
  }

}


