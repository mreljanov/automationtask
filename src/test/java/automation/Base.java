package automation;

import org.junit.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

public class Base {
	public static MyWebElement driverW;
	public static WebDriver driver;

	@Test
	public void Acceptance_test() {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
//		options.addArguments("start-maximized");
//		WebDriver driver = new ChromeDriver(options);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.opencart.com/");
		driverW = new MyWebElement();
		MyWebElement.setDriver(driver);

	}

	public void MyWebElement() {
		System.out.println("MyWebElement creation");
	}

	public static void CheckLoginStatus(String password) {
		String email = Include.email;
		driverW.findElement(By.className("dropdown")).click();
		Integer display = driverW.findElements(By.xpath("//a[contains(text(), 'Login')]")).size();
		if (display != 0) {
			driverW.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
			driverW.findElement(By.id("input-email")).sendKeys(email);
			driverW.findElement(By.id("input-password")).sendKeys(password);
			driverW.findElement(By.xpath("//input[@value='Login']")).click();
		} else {
			System.out.println("You are already logged in");
			driverW.findElement(By.className("dropdown")).click();
		}
	}
}
