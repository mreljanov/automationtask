package automation;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import static automation.Base.driverW;
import static automation.Base.CheckLoginStatus;

public class ChangePassword extends Include {

	@Test
	public void ChangeInitialPassword() {

		Integer i = 0;
		while (i < 7) {
			try {
				driverW.waitForVisibilityOfElement(By.className("dropdown")).click();
				WebElement Logout = driverW.waitForVisibilityOfElement(By.xpath("//a[contains(text(), 'Login')]"));
				Logout.click();
				CheckLoginStatus(initialPassword);
				break;
			} catch (TimeoutException nse) {
				System.out.println("User couldn't login this time");
				i++;
			}
		}

		driverW.findElement(By.xpath("//a[contains(text(), 'Password')]")).click();
		driverW.findElement(By.id("input-password")).sendKeys(password);
		driverW.findElement(By.id("input-confirm")).sendKeys(password);
		driverW.findElement(By.className("btn-primary")).click();

		String expectedMessage = "Success: Your password has been successfully updated.";
		String logoutMessage = driverW.findElement(By.className("alert-success")).getText();
		if (expectedMessage.equalsIgnoreCase(logoutMessage))
			System.out.println("You have successfully changed your password --- " + logoutMessage);
		else
			System.out
					.println("You have experienced an issue while trying to logout from opencart --- " + logoutMessage);

	}
}
