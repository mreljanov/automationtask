package automation;

import org.junit.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;
import static automation.Base.CheckLoginStatus;

import static automation.Base.driverW;

public class FinishPurchase extends Include {

	@Test
	public void finishingOrdering() {
		CheckLoginStatus(password);
		Boolean billingDetailsExist = false;

		Integer e = 0;
		while (e < 5) {
			try {
				driverW.waitForVisibilityOfElement(By.linkText("Shopping Cart")).click();
				break;
			} catch (ElementNotInteractableException nse) {
				System.out.println("nse");
				e++;
			} catch (TimeoutException te) {
				e++;
				System.out.println("te");
			} catch (StaleElementReferenceException see) {
				e++;
				System.out.println("see");
			}
		}
		driverW.waitForVisibilityOfElement(By.linkText("Shopping Cart")).click();
		driverW.findElement(By.linkText("Checkout")).click();
		driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"content\"]/h1"));

		// Check if user has already entered his or hers billing details before in a
		// previous purchase
		Integer f = 0;
		while (f < 10) {
			try {
				driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"input-payment-firstname\"]"));
				billingDetailsExist = false;
				break;
			} catch (ElementNotInteractableException nse) {
				billingDetailsExist = true;
				System.out.println("user has already entered his or hers billing details");
				f++;
			} catch (TimeoutException te) {
				billingDetailsExist = true;
				f++;
				System.out.println("timeout user has already entered his or hers billing details");
			}
		}
		if (!billingDetailsExist) {
			Integer i = 0;
			while (i < 7) {
				try {

					driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"input-payment-firstname\"]"));
					break;
				} catch (TimeoutException nse) {
					try {
						System.out.println("ns");
						driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"input-payment-firstname\"]"));
						break;
					} catch (TimeoutException te) {
						System.out.println("no firstname input section");
						i++;
					}
				}
			}

			driverW.findElement(By.xpath("//*[@id=\"input-payment-firstname\"]")).sendKeys("John");
			driverW.findElement(By.xpath("//*[@id=\"input-payment-lastname\"]")).sendKeys("Doe");
			driverW.findElement(By.xpath("//*[@id=\"input-payment-address-1\"]")).sendKeys("Vukovarska 120");
			driverW.findElement(By.xpath("//*[@id=\"input-payment-city\"]")).sendKeys("Split");
			new Select(driverW.findElement(By.id("input-payment-country"))).selectByVisibleText("Croatia");
			Integer j = 0;
			while (j < 7) {
				try {
					new Select(driverW.findElement(By.id("input-payment-zone")))
							.selectByVisibleText("Splitsko-dalmatinska");
					break;
				} catch (NoSuchElementException nse) {
					try {
						new Select(driverW.findElement(By.id("input-payment-zone")))
								.selectByVisibleText("Splitsko-dalmatinska");
						break;
					} catch (NoSuchElementException te) {
						System.out.println("no payment zone select list at " + j + "run");
						j++;
					}
				}
			}
		}

		Integer g = 0;
		while (g < 7) {
			try {
				driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-payment-address']")).click();
				break;
			} catch (TimeoutException nse) {
				try {
					driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-payment-address']")).click();
					break;
				} catch (TimeoutException te) {
					System.out.println("no payment address button at " + g + "run");
					g++;
				}
			}
		}

		Integer h = 0;
		while (h < 7) {
			try {			
				driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-shipping-address']")).click();
				break;
			} catch (TimeoutException nse) {
				try {
					driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-shipping-address']")).click();
					break;
				} catch (TimeoutException te) {
					System.out.println("no shipping address button at " + h + "run");
					h++;
				}
			}
		}

		Integer k = 0;
		while (k < 7) {
			try {
				driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-shipping-method']")).click();
				driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"collapse-shipping-method\"]/div/p[4]/textarea"))
						.sendKeys("1st floor apartman Jack");
				break;
			} catch (TimeoutException nse) {
				try {

					driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-shipping-method']")).click();
					driverW.waitForVisibilityOfElement(
							By.xpath("//*[@id=\"collapse-shipping-method\"]/div/p[4]/textarea"))
							.sendKeys("1st floor apartman Jack");
					break;
				} catch (TimeoutException te) {
					System.out.println("no shipping method button at " + k + "run");
					k++;
				}
			}
		}

		Integer l = 0;
		while (l < 7) {
			try {
				driverW.waitForVisibilityOfElement(By.xpath("//input[@name='agree']")).click();
				driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-payment-method']")).click();
				break;
			} catch (TimeoutException nse) {
				try {
					System.out.println("ns1");
					driverW.waitForVisibilityOfElement(By.xpath("//input[@name='agree']")).click();
					driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-payment-method']")).click();
					break;
				} catch (TimeoutException te) {
					System.out.println("no payment method button at " + l + " run");
					l++;
				}
			} catch (NoSuchElementException nse) {
				System.out.println("no button");
				l++;
			}
		}
		// Workaround for the payment method missing message that occasionally appears
		Boolean bug = false;
		Integer p = 0;
		while (p < 5) {
			try {
				if (driverW
						.waitForVisibilityOfElement(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]"))
						.isDisplayed()) {
					driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"accordion\"]/div[4]/div[1]/h4/a")).click();
					driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-shipping-method']")).click();
					bug = true;
				}
				break;
			} catch (TimeoutException te) {
				p++;
			} catch (NoSuchElementException nse) {
				p++;
			} catch (ElementNotInteractableException nse) {
				p++;
			}
		}

		if (bug) {
			Integer i = 0;
			while (i < 7) {
				try {
					driverW.waitForVisibilityOfElement(By.xpath("//input[@name='agree']")).click();
					driverW.waitForVisibilityOfElement(By.xpath("//*[@id='button-payment-method']")).click();
					System.out.println("A workaraound for the no payment method has been executed");
					break;
				} catch (TimeoutException te) {
					System.out.println("no payment method method still not resolved");
					p++;

				} catch (NoSuchElementException nse) {
					System.out.println("no payment method method still not resolved");
					l++;
				}
			}

		}
		Integer m = 0;
		while (m < 7) {
			try {
				driverW.waitForVisibilityOfElement(By.xpath("//input[@value='Confirm Order']")).click();
				break;
			} catch (TimeoutException nse) {
				try {
					driverW.waitForVisibilityOfElement(By.xpath("//input[@value='Confirm Order']")).click();
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

		Integer n = 0;
		while (n < 7) {
			try {
				String textConfirm = driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
				if (textConfirm.contains("Your order has been placed!")) {
					System.out.println("You have successfully finished your order");
					break;
				}
			}

			catch (TimeoutException nse) {
				try {
					String textConfirm = driverW.waitForVisibilityOfElement(By.xpath("//*[@id=\"content\"]/h1"))
							.getText();
					if (textConfirm.contains("Your order has been placed!")) {
						System.out.println("You have successfully finished your order");
						break;
					}
				} catch (TimeoutException te) {
					System.out.println("no agree or payment method button");
					n++;
				}
			} catch (StaleElementReferenceException nse) {
				System.out.println("no button");
				n++;
			}
		}

	}

}
