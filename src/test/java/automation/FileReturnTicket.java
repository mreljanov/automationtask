package automation;

import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import static automation.Base.CheckLoginStatus;
import static automation.Base.driverW;

public class FileReturnTicket extends Include {

	@Test
	public void creatingReturnTicket() {
		CheckLoginStatus(password);

		driverW.findElement(By.linkText("Order History")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//a[@class=\"btn btn-info\"]")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//a[@class='btn btn-danger']")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//input[@value='2']")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//label[@class='radio-inline']/input[@value='1']")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//input[@value='Submit']")).click();
		Integer m = 0;
		while (m < 7) {
			try {
				String textConfirm = driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"content\"]//p[1]"))
						.getText();
				if (textConfirm.contains("Thank you for submitting your return request")) {
					System.out.println("You have successfully created a return ticket");
				}
				break;
			} catch (TimeoutException nse) {
				try {
					System.out.println("ns");
					String textConfirm = driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"content\"]//p[1]"))
							.getText();
					if (textConfirm.contains("Thank you for submitting your return request")) {
						System.out.println("You have successfully created a return ticket");
					}
					break;
				} catch (TimeoutException te) {
					System.out.println("no confirm order button at " + m + " run");
					m++;
				}
			} catch (NoSuchElementException nse) {
				System.out.println("no button");
				m++;
			}
		}

	}
}