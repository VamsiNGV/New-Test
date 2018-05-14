import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utilities.TestUtils;

public class NewTest {
  WebDriver driver;
	
	@BeforeMethod
	public void InitialNavigation() {
		System.setProperty("webdriver.ie.driver", "G:\\Selenium3.0.1\\IEDriverServer_x64_3.0.0\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	//Launching the driver
	driver.get("https://www.humira.com");
	//((JavascriptExecutor) driver).executeScript("window.open('http://facebook.com/');");
	driver.findElement(By.xpath("//md-icon[@class = 'material-icons masthead-person-icon person-icon']//following::span")).click();
	
	//Take Screenshot
	}
	
	@DataProvider
	public static Object[][] Authentication()
	{
		Object loginData[][] = TestUtils.getDatafromExcel("Login");
		return (loginData);
		
	}
	
	
	//Enter values in the Login Form
	@Test(dataProvider = "Authentication")
	public void LoginFunctionality(String Username, String Password)
	{
		
		
		
		driver.findElement(By.className("emailField")).sendKeys(Username);;
		driver.findElement(By.name("guideContainer-rootPanel-password___jqName")).sendKeys(Password);;
		driver.findElement(By.xpath("//span[@class='iconButton-label']")).click();
		String ErrorMessage = driver.findElement(By.className("guideFieldError")).getText();
		//Assert.assertEquals(ErrorMessage, "Please enter a valid email address.");
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(ErrorMessage, "Please enter a valid email address.");
		System.out.println("The error message is displayed properly");
		
		
		
	}
	
	//Quit the application
	@AfterMethod
	public void TearDown()
	{
		driver.quit();
	}
	
}
