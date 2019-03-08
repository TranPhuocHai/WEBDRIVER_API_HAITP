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
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	  }	
	
  @Test
  public void TC_01_Check_Element_isDisplayed() {
	  	  
	  if(isElementDisplayed(emailTextbox)) {
		  driver.findElement(emailTextbox).sendKeys("Automation");		  
	  }
	  
	  if(isElementDisplayed(AgeUnder18Radio)) {
		  driver.findElement(AgeUnder18Radio).click();		  
	  }
	  
	  if(isElementDisplayed(educationTextArea)) {
		  driver.findElement(educationTextArea).sendKeys("Automation");		  
	  }
		 
  }
  
  @Test
  public void TC_02_Check_Element_isEnabled() {


	  if (isEnable(emailTextbox)){
		  System.out.println("***TC_02_Check_Element_isEnabled Results:");
		  System.out.println("- Email Textbox is enable");
		  
	  }
		  else {
			  System.out.println("- Email Textbox is disable");		  
	  }
	  
	  if (isEnable(AgeUnder18Radio)){
		  System.out.println("- Age (Under 18) Radio Button is enable");}
	  else {
		  System.out.println("- Age (Under 18) Radio Button is disable");		  
	  }
	  
	  if (isEnable(educationTextArea)){
		  System.out.println("- Age (Education Text Area is enable");}
	  else {
		  System.out.println("- Education Text Area is disable");		  
	  }
	  
	  if (isEnable(jobRole1)){
		  System.out.println("- Job Role 1 dropdown is enable");}
	  else {
		  System.out.println("- Job Role 1 dropdown is disable");		  
	  }
	  
	  if (isEnable(interestsDevelopment)){
		  System.out.println("- Development checkbox is enable");}
	  else {
		  System.out.println("- Development checkbox is disable");		  
	  }
	  
	  if (isEnable(slider01)){
		  System.out.println("- Slider 01 is enable");}
	  else {
		  System.out.println("- Slider 01 is disable");		  
	  }
	  
	  if (isEnable(buttonIsEnable)){
		  System.out.println("- 'Button is enable' is enable");}
	  else {
		  System.out.println("- 'Button is enable' is disable");		  
	  }
	  
	  if (isEnable(pasword)){
		  System.out.println("- Pasword textbox is enable");}
	  else {
		  System.out.println("- Pasword textbox is disable");		  
	  }
	  
	  if (isEnable(AgeDisableRadio)){
		  System.out.println("- Radio button-is-disabled is enable");}
	  else {
		  System.out.println("- Radio button-is-disabled is disable");		  
	  }
	  
	  if (isEnable(bioTextArea)){
		  System.out.println("- Biography Text Area is enable");}
	  else {
		  System.out.println("- Biography Text Area is disable");		  
	  }
	  
	  if (isEnable(jobRole2)){
		  System.out.println("- Job Role 2 dropdown is enable");}
	  else {
		  System.out.println("- Job Role 2 dropdown is disable");		  
	  }
	  
	  if (isEnable(checkBoxDisable)){
		  System.out.println("- 'Checkbox is disable' is enable");}
	  else {
		  System.out.println("- 'Checkbox is disable' is disable");		  
	  }
	  
	  if (isEnable(slider02)){
		  System.out.println("- Slider 02 is enable");}
	  else {
		  System.out.println("- Slider 02 is disable");		  
	  }
	  
	  if (isEnable(buttonDisable)){
		  System.out.println("- 'Button is disable' is enable");}
		  else {
			  System.out.println("- 'Button is disable' is disable");		  
	  }
	  
	  
	  
  }
  
  @Test
  public void TC_03_Check_Element_isTicked () {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(AgeUnder18Radio).click();
	  driver.findElement(interestsDevelopment).click();
	  
	  if (isSelected(AgeUnder18Radio)) {
		  
	  }
	  else {
		  driver.findElement(AgeUnder18Radio).click(); 
	  }
	  
	  if (isSelected(interestsDevelopment)) {
		  
	  }
	  else {
		  driver.findElement(interestsDevelopment).click(); 
	  }
  }
  
  @Test
  public void TC_04_Check_Element_isTicked_02 () throws Exception {
	  //Clone from TC_03 but don''t choose Age Under 18 Radio Button
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(interestsDevelopment).click();
	  Thread.sleep(3000);
	  
	  if (isSelected(AgeUnder18Radio)) {
		  
	  }
	  else {
		  driver.findElement(AgeUnder18Radio).click(); 
	  }
	  
	  if (isSelected(interestsDevelopment)) {
		  
	  }
	  else {
		  driver.findElement(interestsDevelopment).click(); 
	  }
  }

  @AfterTest
  public void afterTest() throws Exception {
	  Thread.sleep(5000);
	  driver.quit();
  }
  
  public boolean isElementDisplayed(By byValue) {
	  return driver.findElement(byValue).isDisplayed();
	  
  }
  
  public boolean isEnable(By byValue) {
	  return driver.findElement(byValue).isEnabled();
  }
  
  public boolean isSelected (By byValue) {
	  
	  return driver.findElement(byValue).isSelected();
	  
  }
  

  
}


