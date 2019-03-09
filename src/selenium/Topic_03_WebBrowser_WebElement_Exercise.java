package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement_Exercise {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By AgeUnder18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By jobRole1 = By.xpath("//select[@id='job1']");
	By interestsDevelopment = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonIsEnable = By.xpath("//button[@id='button-enabled']");
	By pasword = By.xpath("//input[@id='password']");
	By AgeDisableRadio = By.xpath("//input[@id='radio-disabled']");
	By bioTextArea = By.xpath("//textarea [@id='bio']");
	By jobRole2 = By.xpath("//select[@id='job2']");
	By checkBoxDisable = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisable = By.xpath("//button[@id='button-disabled']");
	
	
	@BeforeTest
	  public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	  }	
	
  @Test
  public void TC_01_Check_Element_isDisplayed() {
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
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  System.out.println("***TC_02_Check_Element_isEnabled Results:");

	  if (isEnable(emailTextbox)){ System.out.println("- Email Textbox is enabled");}
	  		else {System.out.println("- Email Textbox is disabled");}
	  
	  if (isEnable(AgeUnder18Radio)){System.out.println("- Age (Under 18) Radio Button is enabled");}
	  		else {System.out.println("- Age (Under 18) Radio Button is disabled");	 }
	  
	  if (isEnable(educationTextArea)){System.out.println("- Age (Education Text Area is enabled");}
	  		else {System.out.println("- Education Text Area is disabled");}
	  
	  if (isEnable(jobRole1)){System.out.println("- Job Role 1 dropdown is enabled");}
	  		else {System.out.println("- Job Role 1 dropdown is disabled");}
	  
	  if (isEnable(interestsDevelopment)){System.out.println("- Development checkbox is enabled");}
	  		else {System.out.println("- Development checkbox is disabled");}
	  
	  if (isEnable(slider01)){System.out.println("- Slider 01 is enabled");}
	  		else {System.out.println("- Slider 01 is disabled");}
	  
	  if (isEnable(buttonIsEnable)){System.out.println("- 'Button is enable' is enabled");}
	  		else {System.out.println("- 'Button is enable' is disabled");}
	  
	  if (isEnable(pasword)){System.out.println("- Pasword textbox is enabled");}
	  		else {System.out.println("- Pasword textbox is disabled");}
	  
	  if (isEnable(AgeDisableRadio)){System.out.println("- Radio button-is-disabled is enabled");}
	  		else {System.out.println("- Radio button-is-disabled is disabled");}
	  
	  if (isEnable(bioTextArea)){System.out.println("- Biography Text Area is enabled");}
	  		else {System.out.println("- Biography Text Area is disabled");}
	  
	  if (isEnable(jobRole2)){System.out.println("- Job Role 2 dropdown is enabled");}
	  		else {System.out.println("- Job Role 2 dropdown is disabled");}
	  
	  if (isEnable(checkBoxDisable)){System.out.println("- 'Checkbox is disable' is enabled");}
	  		else {System.out.println("- 'Checkbox is disable' is disabled");}
	  
	  if (isEnable(slider02)){System.out.println("- Slider 02 is enabled");}
	  		else {System.out.println("- Slider 02 is disabled");}
	  
	  if (isEnable(buttonDisable)){System.out.println("- 'Button is disable' is enabled");}
	  		else {System.out.println("- 'Button is disable' is disabled");}
	  
	  
	  
  }
  
  @Test
  public void TC_03_Check_Element_isSelected () {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(AgeUnder18Radio).click();
	  driver.findElement(interestsDevelopment).click();
	  
	  if (isSelected(AgeUnder18Radio)) {}
	  else {driver.findElement(AgeUnder18Radio).click();}
	  
	  if (isSelected(interestsDevelopment)) {}
	  else {driver.findElement(interestsDevelopment).click();}
  }
  
  @Test
  public void TC_04_Check_Element_isSelected_02 () throws Exception {
	  
	  //Be cloned by TC_03 but don't choose Age Under 18 Radio Button for checking re-select
	  
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(interestsDevelopment).click();
	  Thread.sleep(3000);
	  
	  if (isSelected(AgeUnder18Radio)) {}
	  else {driver.findElement(AgeUnder18Radio).click();}
	  
	  if (isSelected(interestsDevelopment)) {}
	  else {driver.findElement(interestsDevelopment).click();}
  }

  @AfterTest
  public void afterTest() throws Exception {
	  Thread.sleep(5000);
	  driver.quit();
  }
  
  public boolean isElementDisplayed(By byValue) {
	  return driver.findElement(byValue).isDisplayed();}
  
  public boolean isEnable(By byValue) {
	  return driver.findElement(byValue).isEnabled();}
  
  public boolean isSelected (By byValue) {	  
	  return driver.findElement(byValue).isSelected();}
  
  
}


