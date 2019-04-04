package selenium;

import java.util.List;
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
    String fileName01 = "Image 01.png";
    String fileName02 = "Image 02.png";
    String fileName03 = "Image 03.png";
    String [] AllfileNames = {fileName01,fileName02,fileName03};
    
    String fileNamePath01 = rootFolder + "\\uploadFiles\\"+ fileName01;
    String fileNamePath02 = rootFolder + "\\uploadFiles\\"+ fileName02;
    String fileNamePath03 = rootFolder + "\\uploadFiles\\"+ fileName03;    
    String [] Allfiles = {fileNamePath01,fileNamePath02,fileNamePath03};

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
		
		
		
		System.out.println(rootFolder);
		System.out.println(fileNamePath01);
		System.out.println(fileNamePath02);
		System.out.println(fileNamePath03);
	}

	@Test (enabled = true)
	public void TC_01_SendKeys_UploadMultiple_Queue() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		Thread.sleep(3000);
		
		//Upload 3 files
		for (String file:Allfiles) {
			WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
			uploadFile.sendKeys(file);	
			Thread.sleep(2000);
		}
		
						
		//Click upload button
		WebElement UploadBtn = driver.findElement(By.xpath(" //span[text()='Start upload']"));
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(UploadBtn);
			System.out.println("Click by JS for ie");
		} else {
			UploadBtn.click();
			System.out.println("Click by selenium builtin");
		}			
				
		
		//verfiy upload 3 files successfully 
		for(String filename:AllfileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());	
		}
				
	}
	
	@Test (enabled = true)
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

		
		//verfiy upload 3 files successfully 
		for(String filename:AllfileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());	
		}
				
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
    public Object clickToElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("arguments[0].click();", element);
}

}













