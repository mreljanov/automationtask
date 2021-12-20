package automation;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.ElementClickInterceptedException;
import static automation.Base.CheckLoginStatus;

import static automation.Base.driverW;

public class AddProducts extends Include {

	@Test
	public void addingProducts() {
		CheckLoginStatus(password);

		// Add an element to the shopping cart after viewing it
		driverW.findElement(By.linkText("Laptops & Notebooks")).click();
		driverW.findElement(By.linkText("Show All Laptops & Notebooks")).click();
		driverW.findElement(By.linkText("HP LP3065")).click();
		driverW.findElement(By.xpath("//div[@class='input-group date']/input")).clear();
		driverW.findElement(By.xpath("//div[@class='input-group date']/input")).sendKeys("2022-01-22");
		driverW.findElement(By.xpath("//div[@class='form-group']/button")).click();
		driverW.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/h1")).click();
		Integer i = 0;
		while (i < 10) {
			try {
				WebElement alert = driverW.waitForVisibilityOfElement(
						By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]"));
				if (alert.isDisplayed()) {
					String successMessage = driverW
							.waitForVisibilityOfElement(By.xpath("//*[@id=\"product-product\"]/div[1]/a[1]")).getText();
					System.out.println(successMessage + " has been successfully added to the Shopping cart");
				}
				break;
			} catch (TimeoutException te) {
				System.out.println(i + "timeout exception occurred");
				i++;
			}
		}

		// Add an element to the shopping cart directly from the Home page
		Integer j = 0;
		while (j < 10) {
			try {
				driverW.findElement(By.className("fa-home")).click();
				driverW.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]/span")).click();
				WebElement home = driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"common-home\"]/div[1]"));
				if (home.isDisplayed()) {
					String successMessage = driverW
							.waitForVisibilityOfElement(By.xpath("//*[@id=\"common-home\"]/div[1]/a[1]")).getText();
					System.out.println(successMessage + " has been successfully added to the Shopping cart");
				}
				break;
			} catch (TimeoutException nse) {
				try {
					System.out.println("timeout exception occurred");
					driverW.findElement(By.className("fa-home")).click();
					driverW.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]/span"))
							.click();
					driverW.waitForVisibilityOfElement(
							By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]"));
					if (driverW.findElement(By.className("alert-dismissible")).isDisplayed()) {
						String successMessage = driverW
								.waitForVisibilityOfElement(By.xpath("//*[@id=\"common-home\"]/div[1]/a[1]")).getText();
						System.out.println(successMessage + " has been successfully added to the cart");
					}
					break;
				} catch (TimeoutException te) {
					System.out.println("te");
					j++;
				}
			} catch (NoSuchElementException nse) {
				System.out.println("nse");
				j++;
			} catch (StaleElementReferenceException see) {
				System.out.println("see");
				j++;
			}
		}

		// Remove products from the Shopping cart that are out of stock
		String danger = "//span[contains(text(), '***')]";
		String danger2 = danger + "/../following-sibling::td/./following-sibling::td/div/span/button[2]";

		Integer k = 0;
		while (k < 7) {
			try {
				driverW.findElement(By.linkText("Shopping Cart")).click();
				Boolean display = driverW.waitForVisibilityOfElement(By.xpath(danger)).isDisplayed();
				if (display) {
					driverW.waitForVisibilityOfElement(By.xpath(danger2)).click();
					System.out.println("The out of stock item has been removed from the Shopping cart");
					break;
				}
			} catch (TimeoutException nse) {
				try {
					driverW.findElement(By.linkText("Shopping Cart")).click();
					Boolean display = driverW.waitForVisibilityOfElement(By.xpath(danger)).isDisplayed();
					if (display) {
						driverW.waitForVisibilityOfElement(By.xpath(danger2)).click();
						System.out.println("The out of stock item has been removed from the Shopping cart");
						break;
					}
				} catch (NoSuchElementException nsee) {
					System.out.println("There are no items which are out of stock in the Shopping cart");
					k++;
				} catch (TimeoutException nsee) {
					System.out.println("Timeout: There are no items which are out of stock in the Shopping cart");
					k++;
				} catch (ElementClickInterceptedException eciee) {
					System.out.println(
							"Click interception: There are no items which are out of stock in the Shopping cart");
					k++;
				}

			}
		}
	}

}