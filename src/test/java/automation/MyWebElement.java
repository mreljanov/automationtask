package automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class MyWebElement implements WebElement {
	protected static WebElement elementW;
	private static WebDriver driver;

	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	public void click() {
		try {
			elementW.click();
		} catch (StaleElementReferenceException stre) {
			System.out.println(
					"StaleElementReferenceException was caught in 'click()' method, attempted to recover - MyWebElement");
			driver.navigate().refresh();
			elementW.click();
		}
	}

	public WebElement findElementW(By by) {
		// logger.info("findElementW @Implementation");

		try {
			elementW = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(by));
		} catch (TimeoutException te) {
			// logger.info("MightyHelper-findElementW-TimeoutException, trying to
			// recover...");
			elementW = waitForPresenceOfElementNoStyleChange(by);
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid black;');",
				elementW);

		return elementW;
	}

	public void submit() {
		// TODO Auto-generated method stub

	}

	public void sendKeys(CharSequence... keysToSend) {
		// TODO Auto-generated method stub

	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElements(By by) {
		List<WebElement> listWeb = elementW.findElements(by);

		return listWeb;
	}

	public WebElement findElement(By by) {
		elementW = driver.findElement(by);
		return elementW;
	}

	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCssValue(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		MyWebElement.driver = driver;
	}

	public static void setWebElement(WebElement element) {
		MyWebElement.elementW = element;
	}

	public WebElement waitForPresenceOfElementNoStyleChange(By locator) {
		WebElement foundElement = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.presenceOfElementLocated(locator));

		return foundElement;
	}

	public WebElement waitForVisibilityOfElement(By locator) {
		WebElement foundElement = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));

		return foundElement;
	}

}
