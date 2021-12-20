package automation;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static automation.Base.driverW;
import static automation.Base.CheckLoginStatus;

public class LoginAndLogout extends Include {

	@Test
	public void loginAndOut() {
		// login process
		CheckLoginStatus(initialPassword);
		// Logout process
		Integer i = 0;
		while (i < 7) {
			try {
				driverW.waitForVisibilityOfElement(By.className("dropdown")).click();
				WebElement Logout = driverW.waitForVisibilityOfElement(By.xpath("//a[contains(text(), 'Logout')]"));
				Logout.click();
				break;
			} catch (TimeoutException nse) {
				System.out.println("User couldn't logout this time");
				i++;
			}
		}

		String expectedMessage = "You have been logged off your account. It is now safe to leave the computer.";

		// Storing the text of the heading in a string and checking if the logout
		// process is successful
		String logoutMessage = driverW
				.findElement(By.xpath("//p[contains(text(), 'You have been logged off your account.')]")).getText();
		if (expectedMessage.equalsIgnoreCase(logoutMessage))
			System.out.println("You have successfully logged out from opencart --- " + logoutMessage);
		else
			System.out
					.println("You have experienced an issue while trying to logout from opencart --- " + logoutMessage);

	}
}
