package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement {
    // Khai báo biến
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		// Khởi tạo browser lên
		driver = new FirefoxDriver();

	}

	@Test
	public void TC_01_Browser() {
		// WebBrowser: những API tương tác vs trình duyệt

		// Mở 1 url lên
		driver.get("http://facebook.com");

		// Trả về URL của page hiện tại
		String homePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, "https://www.facebook.com/");

		// Trả về source code của page hiện tại
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(
				homePageSource.contains("Facebook giúp bạn kết nối và chia sẻ với mọi người trong cuộc sống của bạn"));

		// Trả về title của page hiện tại
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Facebook - Đăng nhập hoặc đăng ký");

		// Handle Windows/ Popup
		driver.getWindowHandle();
		driver.getWindowHandles();

		// Đóng tab đang active (có 1 tab thì nó cũng đóng luôn cái browser)
		driver.close();

		// Đóng browser
		driver.quit();

		// Handle WebDriverWait
		// Nó chính là timeout để driver đi tìm element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Timeout để chờ cho page được load thành công
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Inject đoạn code Javascript/ Jquery vào browser
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

		// Back về page trước đó
		driver.navigate().back();

		// Forward về page kế tiếp
		driver.navigate().forward();

		// Refresh 1 page (F5)
		driver.navigate().refresh();

		// Mở 1 URL lên (Tracking save history)
		driver.navigate().to("http://facebook.com");

		// Handle cho Alert
		driver.switchTo().alert();

		// Handle Frame/ Iframe
		driver.switchTo().frame("");

	}

	@Test
	public void TC_02_Element() {
		// Tương tác trực tiếp (1 step)
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("automationtest@gmail.com");
		driver.findElement(By.xpath("//button[@id='login']")).click();

		// Tương tác gián tiếp (nhiều step)
		WebElement emailTextbox = driver.findElement(By.xpath("//*[@id='email']"));

		// Xóa dữ liệu cũ đi trước khi thao tác để đảm bảo toàn vẹn dữ liệu
		emailTextbox.clear();
		emailTextbox.sendKeys("automationtest@gmail.com");

		/*
		 * <input autocomplete="off" id="search" name="q" value=""
		 * class="input-text required-entry" maxlength="128"
		 * placeholder="Search entire store here..." type="search">
		 */

		// Get dữ liệu của attribute ra
		WebElement searchTextbox = driver.findElement(By.id("search"));
		String searchPlaceholderText = searchTextbox.getAttribute("placeholder");
		Assert.assertEquals(searchPlaceholderText, "Search entire store here...");
		
		// Click vào 1 button/ checkbox/ radio button/ link/..
		searchTextbox.click();
		
		// Get ra giá trị của CSS
		WebElement enableButton = driver.findElement(By.id("button-enabled"));
		String enableBackgroundColor = enableButton.getCssValue("background-color");
		Assert.assertEquals(enableBackgroundColor, "#4bc970");
		// enableBackgroundColor  = #4bc970
		
		// Get ra vị trí của element ở bên ngoài
		searchTextbox.getLocation();
		
		// Get ra size ở bên trong
		searchTextbox.getSize();
		
		// Lấy ra tên thẻ HTML => input (điều kiện/ giá trị để dùng trong 1 element khác)
		emailTextbox.getTagName();
		// input
		
		driver.findElement(By.xpath("//div[@class='login-form']//" + emailTextbox.getTagName() + "[@id='signin']"));
		driver.findElement(By.xpath("//div[@class='login-form']//input[@id='signin']"));
		
		// Get ra được text nằm trong element (lable/ mesage/ error/..)
		searchTextbox.getText();
		
		// Kiểm tra 1 element có hiển thị (nhìn thấy và thao tác được) trên UI hay ko
		Assert.assertTrue(searchTextbox.isDisplayed());
		
		// Kiểm tra ko hiển thị
		Assert.assertFalse(searchTextbox.isDisplayed());
		Assert.assertTrue(!searchTextbox.isDisplayed());
		
		// Kiểm tra 1 element có thao tác được hay ko (mặc dù là có hiển thị)
		Assert.assertTrue(searchTextbox.isEnabled());
		
		// Kiểm tra 1 element đã được chọn hay chưa: checkbox/ radio button
		searchTextbox.isSelected();
		
		// Truyền 1 hành động (ENTER) vào form (login form/ register/ search/..)
		searchTextbox.submit();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}