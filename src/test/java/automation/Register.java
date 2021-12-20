package automation;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import static automation.Base.driverW;

public class Register extends Include {

	@Test
	public void createAccount() {
		String firstName = "John";
		String lastName = "Doe";
		String email = Include.email;
		String telephone = "01123456";

		WebElement dropdown = driverW.findElement(By.className("dropdown"));
		dropdown.click();
		WebElement Register = driverW.findElement(By.xpath("//a[contains(text(), 'Register')]"));
		Register.click();
		driverW.findElement(By.id("input-firstname")).sendKeys(firstName);
		driverW.findElement(By.id("input-lastname")).sendKeys(lastName);
		driverW.findElement(By.id("input-email")).sendKeys(email);
		driverW.findElement(By.id("input-telephone")).sendKeys(telephone);
		driverW.findElement(By.id("input-password")).sendKeys(initialPassword);
		driverW.findElement(By.id("input-confirm")).sendKeys(initialPassword);

		driverW.findElement(By.name("agree")).click();
		driverW.findElement(By.className("btn-primary")).click();
		String expectedHeading = "Your Account Has Been Created!";

		// Storing the text of the heading in a string and checking if the registration
		// process is successful
		String heading = driverW.findElement(By.xpath("//div[@class='col-sm-9']//h1")).getText();
		if (expectedHeading.equalsIgnoreCase(heading))
			System.out.println("You have successfully registered to opencart --- " + heading);
		else
			System.out.println("You have experienced an issue while trying to register to opencart --- " + heading);
	}
}
