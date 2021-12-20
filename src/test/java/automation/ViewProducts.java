package automation;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementClickInterceptedException;
import static automation.Base.CheckLoginStatus;

import static automation.Base.driverW;

public class ViewProducts extends Include {

	@Test
	public void viewingProducts() {
		CheckLoginStatus(password);

		openPictures(true, "MacBook");
		openPictures(true, "iPhone");
		openPictures(true, "Apple Cinema 30\"");
		openPictures(true, "Canon EOS 5D");

		// view product that is not on the Home page
		driverW.findElement(By.linkText("Desktops")).click();
		driverW.findElement(By.linkText("Show All Desktops")).click();
		driverW.findElement(By.linkText("HP LP3065")).click();
		openPictures(false, "HP LP3065");

	}

	private void openPictures(boolean onfrontPage, String product) {
		if (onfrontPage) {
			driverW.findElement(By.className("fa-home")).click();
			driverW.findElement(By.linkText(product)).click();
		}
		WebElement table = driverW.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/ul[1]"));
		// Now get all the a elements from the table
		List<WebElement> allPictures = table.findElements(By.tagName("a"));

		Integer i = 0;
		while (i < 7) {

			try {
				for (WebElement picture : allPictures) {

					System.out.println(picture.getAttribute("title"));
					picture.click();
					driverW.waitForVisibilityOfElement(By.className("mfp-close")).click();
				}
				break;
			} catch (StaleElementReferenceException see) {
				System.out.println("see");
				i++;
			} catch (NoSuchElementException nse) {
				System.out.println("nse");
				i++;
			} catch (ElementClickInterceptedException ecie) {
				System.out.println("nse");
				i++;
			}
		}

		Boolean specificationVisible = driverW.findElement(By.linkText("Description")).isDisplayed();
		Boolean reviewsVisible = driverW.findElement(By.linkText("Reviews (0)")).isDisplayed();
		if (specificationVisible && reviewsVisible)
			System.out.println("You have successfully viewed: " + product);
		else
			System.out.println(product + " has not been viewed");
	}
}
