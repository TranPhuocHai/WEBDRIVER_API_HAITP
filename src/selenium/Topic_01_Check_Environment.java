package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment {
    WebDriver driver;
    // Tao bien driver

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver(); // Lam viec tren firefox
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Chua hieu lam
		driver.manage().window().maximize(); // Maximize cua so browser
		driver.get("http://live.guru99.com/"); // open link with firefox
	}

	@Test
	public void TC_01_CheckUrl() {
		String homePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");
	}
	
	@Test
	public void TC_01_CheckTitle() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}