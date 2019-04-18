package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_01_Annotations_Groups_Priority {
	@Test(groups ="check", priority = 3)
	public void TC_01 () {
		System.out.println("Test case 01");
	}
	@Test (groups ="noncheck", priority = 4)
	public void TC_02 () {
		System.out.println("Test case 02");
	}
	@Test 
	public void TC_03 () {
		System.out.println("Test case 03");
	}
	@Test (groups ="check", priority = 2)
	public void TC_04 () {
		System.out.println("Test case 04");
	}
	@Test 
	public void TC_05 () {
		System.out.println("Test case 05");
	}
	@Test 
	public void TC_06 () {
		System.out.println("Test case 06");
	}
	@Test (groups ="check", priority = 1)
	public void TC_07 () {
		System.out.println("Test case 07");
	}
	@Test (groups ="noncheck")
	public void TC_09 () {
		System.out.println("Test case 09");
	}

  @BeforeMethod
  public void beforeMethod() {
		System.out.println("beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
		System.out.println("afterMethod");
	  
  }


  @BeforeClass
  public void beforeClass() {
		System.out.println("beforeClass");
  }

  @AfterClass
  public void afterClass() {
		System.out.println("afterClass");
  }

  @BeforeTest
  public void beforeTest() {
		System.out.println("beforeTest");
  }

  @AfterTest
  public void afterTest() {
		System.out.println("afterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
		System.out.println("beforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
		System.out.println("afterSuite");
  }

}
