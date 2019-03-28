package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_06_Button_Radio_Checkbox_Alert {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 

	}

	@Test (enabled = true)
	public void TC_01_Button_JSclick() {
		
		driver.get("http://live.guru99.com/");
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']"));
		jsExecutor.executeScript("arguments[0].click();", myAccountLink);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
		WebElement CreateAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		jsExecutor.executeScript("arguments[0].click();", CreateAccountLink);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
	}
		
	@Test (enabled = true)
	public void TC_02_KendoUi_CheckBox() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		By DualZoneXpath = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		WebElement DualZoneCheckbox = driver.findElement(DualZoneXpath);
		
		jsExecutor.executeScript("arguments[0].click();", DualZoneCheckbox);	
		Assert.assertTrue(isElementSelected((DualZoneXpath)));
		Thread.sleep(2000);
		
		jsExecutor.executeScript("arguments[0].click();", DualZoneCheckbox);	
		Assert.assertFalse(DualZoneCheckbox.isSelected());
		Thread.sleep(2000);
		
	}
	
	@Test (enabled = true)
	public void TC_03_KendoUi_RadioButton() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		
		By expectedRadioXpath = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		WebElement expectedRadioButton = driver.findElement(expectedRadioXpath);
		
		jsExecutor.executeScript("arguments[0].click();", expectedRadioButton);	
		Assert.assertTrue(expectedRadioButton.isSelected());
		Thread.sleep(2000);
		
	}
	
	@Test (enabled = true)
	public void TC_04_Accept_Confirm_Prompt_Alert() throws Exception {
		
		//Accept Alert
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Thread.sleep(1500);
		Alert acceptAlert = driver.switchTo().alert();
		String acceptAlertText = acceptAlert.getText();
		Assert.assertEquals(acceptAlertText, "I am a JS Alert");
		acceptAlert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked an alert successfully ']")).isDisplayed());
		Thread.sleep(1500);
		
		//Confirm Alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Thread.sleep(1500);
		Alert confirmAlert = driver.switchTo().alert();
		String confirmAlertText = confirmAlert.getText();
		Assert.assertEquals(confirmAlertText, "I am a JS Confirm");
		confirmAlert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
		
		
		//Prompt Alert
		driver.navigate().refresh();	
		String inputText = "Tran Phuoc Hai";		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Thread.sleep(1500);
		Alert promptAlert = driver.switchTo().alert();
		String promptAlertText = promptAlert.getText();
		Assert.assertEquals(promptAlertText, "I am a JS prompt");			
		promptAlert.sendKeys(inputText);		
		Thread.sleep(2000);
		promptAlert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: "+ inputText +"']")).isDisplayed());
		
				
	}
		
	@Test (enabled = true)
	public void TC_05_Authentication_Alert() throws Exception {
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
		Thread.sleep(3000);
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
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}