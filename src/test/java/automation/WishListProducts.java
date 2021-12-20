package automation;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import static automation.Base.CheckLoginStatus;

import static automation.Base.driverW;

public class WishListProducts extends Include {

	@Test
	public void wishListingProducts() {
		CheckLoginStatus(password);

		// Add an element to the wish list after viewing it

		driverW.findElement(By.linkText("Desktops")).click();
		driverW.findElement(By.linkText("Show All Desktops")).click();
		driverW.findElement(By.linkText("MacBook Air")).click();

		driverW.findElement(By.xpath("//button[@class='btn btn-default']/i[@class='fa fa-heart']")).click();

		try {
			String text = driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"product-product\"]/div[1]/a[1]"))
					.getText();
			System.out.println(text + " has been successfully added to the Wishlist");
		} catch (TimeoutException te) {
			System.out.println("timeout exception occurred");
			String text = driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"product-product\"]/div[1]/a[1]"))
					.getText();
			System.out.println(text + " has been successfully added to the Wishlist");
		}

		// Add elements from the Laptops & Notebooks tab to the wish list
		driverW.findElement(By.linkText("Laptops & Notebooks")).click();
		driverW.findElement(By.linkText("Show All Laptops & Notebooks")).click();
		WebElement table = driverW.findElement(By.xpath("//*[@id=\"content\"]/div[4]"));

		// Now get all the i elements from the table
		List<WebElement> allPictures = table.findElements(By.tagName("i"));

		Integer i = 0;
		while (i < 10) {

			try {
				for (WebElement picture : allPictures) {
					if (picture.getAttribute("class").contains("heart")) {
						driverW.waitForVisibilityOfElement(By.xpath("//i[@class='fa fa-heart']"));
						picture.click();
						picture.click();
						driverW.waitForVisibilityOfElement(By.xpath("//*[@id='content']/h2"));
						driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"product-category\"]/div[1]/a[1]"));
					}
				}
				i++;
				break;
			} catch (StaleElementReferenceException see) {
				System.out.println("see");
				i++;
			} catch (NoSuchElementException nse) {
				System.out.println("nse");
				i++;
			} catch (ElementClickInterceptedException cise) {
				System.out.println("cise");
				i++;
			} catch (TimeoutException te) {
				System.out.println("te");
				i++;
			} catch (ElementNotInteractableException enie) {
				System.out.println("enie");
				i++;
			}

		}
		// Removing all elements from the wish list
		driverW.waitForVisibilityOfElement(By.xpath("//*[@id='content']/h2")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//a[@id='wishlist-total']")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//a[@id='wishlist-total']")).click();
		WebElement table1 = driverW.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody"));

		// Now get all the TD elements from the table
		List<WebElement> allDeletes = table1.findElements(By.tagName("td"));

		Integer j = 0;
		Integer k = 1;
		while (j < 5) {

			try {
				for (WebElement delete : allDeletes) {
					List<WebElement> cells = delete.findElements(By.tagName("a"));
					for (WebElement cell : cells) {
						if (cell.getAttribute("class").contains("btn-danger")) {
							driverW.waitForVisibilityOfElement(By.xpath("//a[@class='btn btn-danger']"));
							cell.click();
							if (driverW.findElement(By.className("alert-dismissible")).isDisplayed()) {
								System.out.println(
										" The " + k + " Element out of 5 has been successfully removed from wishlist");
								k++;
							}

						}
					}
				}
				break;
			} catch (StaleElementReferenceException see) {
				try {
					System.out.println("see");
					driverW.waitForVisibilityOfElement(By.xpath("//a[@class='btn btn-danger']")).click();
					if (driverW.findElement(By.className("alert-dismissible")).isDisplayed()) {
						System.out
								.println(" The " + k + " Element out of 5 has been successfully removed from wishlist");
						k++;
					}
					j++;
				} catch (TimeoutException te) {
					System.out.println("no button");
					j++;
				}
			} catch (NoSuchElementException nse) {
				System.out.println("nse");
				j++;
			}

		}
	}

}
