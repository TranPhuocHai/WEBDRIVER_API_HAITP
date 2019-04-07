package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_User_Interactions {

    WebDriver driver;
    Actions action;


	@BeforeTest
	public void beforeTest() {
		
		// for chrome
//		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
//		driver = new ChromeDriver();
//		action = new Actions(driver);
		
		//For firefox
		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test (enabled = true)
	public void TC_01_HoverMouse() throws Exception {
		
		driver.get("https://www.myntra.com/");
		WebElement profileIcon, logInButton,logInBox;
		profileIcon = driver.findElement(By.xpath("//span[contains(@class,'iconUser')]"));		
		action.moveToElement(profileIcon).perform();
		Thread.sleep(1000);
		
		logInButton = driver.findElement(By.xpath("//a[text()='log in']"));
		logInButton.click();	
		
		logInBox = driver.findElement(By.xpath("//div[@class='login-box']"));
		Assert.assertTrue(logInBox.isDisplayed());
	}
	
	@Test (enabled = true)
	public void TC_02_ClickAndHold_Block() throws Exception {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id = 'selectable']/li"));
		action.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release().perform();
		Thread.sleep(2000);
		
		List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//ol[@id = 'selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberItemsSelected.size(), 4);	
	}
	
	@Test (enabled = true)
	public void TC_03_ClickAndHold_Random() throws Exception {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id = 'selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(numberItems.get(0));
		action.click(numberItems.get(2));
		action.click(numberItems.get(4));
		action.click(numberItems.get(6));
		action.keyUp(Keys.CONTROL).perform();
		Thread.sleep(3000);
		
		List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//ol[@id = 'selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberItemsSelected.size(), 4);	
	}
	
	@Test (enabled = true)
	public void TC_04_DoubleClick () throws Exception {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleClick).perform();
		Thread.sleep(1000);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
		
	}
	
	@Test (enabled = true)
	public void TC_05_RightClick () throws Exception {
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickBnt = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClickBnt);		
		WebElement quitOption = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(quitOption).perform();
		Thread.sleep(1000);
		
		WebElement quitHover = driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]"));
		Assert.assertTrue(quitHover.isDisplayed());
		
		quitHover.click();
		Alert alert = driver.switchTo().alert();		
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		Thread.sleep(2000);
		Assert.assertFalse(quitHover.isDisplayed());				
		
	}
	
	@Test (enabled = true)
	public void TC_06_DragAndDrop() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(smallCircle, targetCircle).perform();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
		
		
	}

	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}





























