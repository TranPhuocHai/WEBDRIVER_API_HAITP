package selenium;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_11_Upload_Files {
    WebDriver driver;

    //If we want to upload file, we need to get path of file
    String rootFolder = System.getProperty("user.dir");
    String fileName01 = "image 01.png";
    String fileName02 = "image 02.png";
    String fileName03 = "image 03.png";
    String [] AllfileNames = {fileName01,fileName02,fileName03};
    
    String fileNamePath01 = rootFolder + "\\uploadFiles\\"+ fileName01;
    String fileNamePath02 = rootFolder + "\\uploadFiles\\"+ fileName02;
    String fileNamePath03 = rootFolder + "\\uploadFiles\\"+ fileName03;    
    String [] Allfiles = {fileNamePath01,fileNamePath02,fileNamePath03};
    
    String chromePath = rootFolder + "\\uploadFiles\\chrome.exe";
    String firefoxPath = rootFolder + "\\uploadFiles\\firefox.exe";
    String iePath = rootFolder + "\\uploadFiles\\ie.exe";

	@BeforeTest
	public void beforeTest() {
		
		/*  ---  Firefox  ---   */
		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		/*  ---  Chrome  ---  */
//		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
//		driver = new ChromeDriver();
		
		/*  ---   ie  ---   */
//		System.setProperty("webdriver.ie.driver",".\\lib\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 

	}

//	@Test
	public void TC_01_SendKeys_UploadMultiple_Queue() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		Thread.sleep(3000);
		
		//Upload 3 files
		for (String file:Allfiles) {
			WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
			uploadFile.sendKeys(file);	
			Thread.sleep(2000);
		}
		
		//Click on each start button
		List <WebElement> allStartBtns = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement startBtn : allStartBtns ){
			if(driver.toString().contains("internet explorer")) {
				clickToElementByJS(startBtn);
				System.out.println("Click by JS for ie");
			} else {
				startBtn.click();
				System.out.println("Click by selenium builtin");
			}			
			
			Thread.sleep(1000);
		}		
				
		
		//verfiy upload 3 files successfully 
		for(String filename:AllfileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());	
		}
				
	}
	
//	@Test
	public void TC_02_SendKeys_UploadMultiple_Once() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		Thread.sleep(3000);
		
		//Upload 3 files
			WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
			String AllfileNamepaths = fileNamePath01 +"\n" + fileNamePath02 +"\n" + fileNamePath03 ;
			uploadFile.sendKeys(AllfileNamepaths);				
			Thread.sleep(1000);		
						
		//Click on each start button
		List <WebElement> allStartBtns = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement startBtn : allStartBtns ){
			if(driver.toString().contains("internet explorer")) {
				clickToElementByJS(startBtn);
				System.out.println("Click by JS for ie");
			} else {
				startBtn.click();
				System.out.println("Click by selenium builtin");
			}			
			
			Thread.sleep(1000);
		}

		
		//verfiy upload 3 files successfully (by Xpath)
		for(String filename:AllfileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());	
		}


	}
	
//	@Test
	public void TC_03_Upload_By_AutoIT() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");	
		Thread.sleep(3000);
		
		for (String file : Allfiles){
			if(driver.toString().contains("chrome")) {
				WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));		
				uploadFile.click();
				Thread.sleep(1000);
				Runtime.getRuntime().exec(new String [] {chromePath,file});
				Thread.sleep(1000);			
			} else if(driver.toString().contains("firefox")) {
				WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
				clickToElementByJS(uploadFile);
				Thread.sleep(1000);
				Runtime.getRuntime().exec(new String [] {firefoxPath,file});
				Thread.sleep(1000);	
				
			} else {
				WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
				clickToElementByJS(uploadFile);
				Thread.sleep(1000);
				Runtime.getRuntime().exec(new String [] {iePath,file});
				Thread.sleep(1000);	
				
			}
			
		}
		
		//Click on each start button
		List <WebElement> allStartBtns = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement startBtn : allStartBtns ){
			if(driver.toString().contains("internet explorer")) {
				clickToElementByJS(startBtn);
				System.out.println("Click by JS for ie");
			} else {
				startBtn.click();
				System.out.println("Click by selenium builtin");
			}			
			
			Thread.sleep(1000);
		}		
		
		Thread.sleep(3000);
		//verfiy upload 3 files successfully (by Xpath)
		for(String filename:AllfileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());	
		}		
		
	}
	
//	@Test
	public void TC_04_Upload_By_Robot() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");	
		Thread.sleep(3000);

		for (String file : Allfiles){
	        // Specify the file location with extension
	        StringSelection select = new  StringSelection(file);

	        // Copy to clipboard
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);			
			
			if(driver.toString().contains("chrome")) {
				WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));		
				uploadFile.click();
				Thread.sleep(1000);		
			} else if(driver.toString().contains("firefox")) {
				WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
				clickToElementByJS(uploadFile);
				Thread.sleep(1000);	
				
			} else {
				WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
				clickToElementByJS(uploadFile);
				Thread.sleep(1000);
				
			}
			
			Robot robot = new Robot();
			Thread.sleep(1000); 
			
			// Press & Release EnterS
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			// Press Ctrl - V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			
			// Release Ctrl - V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			
			// Press Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
		}	    
        
        
        
		
		//Click on each start button
		List <WebElement> allStartBtns = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement startBtn : allStartBtns ){
			if(driver.toString().contains("internet explorer")) {
				clickToElementByJS(startBtn);
				System.out.println("Click by JS for ie");
			} else {
				startBtn.click();
				System.out.println("Click by selenium builtin");
			}			
			
			Thread.sleep(1000);
		}		
		
		Thread.sleep(3000);
		//verfiy upload 3 files successfully (by Xpath)
		for(String filename:AllfileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());	
		}			
		
	}
	
	@Test
	public void TC_05_Upload_File() throws Exception {
		//Step 01 - Open URL: 'https://encodable.com/uploaddemo/'
		driver.get("https://encodable.com/uploaddemo/");
		
		//Delaire variable
		String email = "hai.gsd@gmail.com", firstName = "Hai", folderName = "Selenium" + randomNumber();
		
		// Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
		WebElement chooseFilesBtn = driver.findElement(By.xpath("//input[@type='file']"));
		chooseFilesBtn.sendKeys(fileNamePath01);
		Thread.sleep(3000);
		
		//Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
		
		
		
		// Step 04 - Input random folder to 'New subfolder? Name:) textbox 
		
		
		
		// Step 05 - Input email and firstname
		
		
		//Step 06 - Click Begin Upload (Note: Wait for page load successfully)
		
		
		
		// Step 07 - Verify information
		   // + Email Address
		    //+ File name
		
		
		
		// Step 08 - Click 'View Uploaded Files' link
		
		
		// Step 09 - Click to random folder
		
		
		// Step 10 - Verify file name exist in folder (UploadFile.jpg)
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
    public Object clickToElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("arguments[0].click();", element);
}
	public int randomNumber() {
		Random ran = new Random();
		int number = ran.nextInt(999999);
		return number;
	}

}














