package automation;

import org.junit.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import static automation.Base.driverW;
import static automation.Base.CheckLoginStatus;


public class SearchProducts extends Include{

	@Test	
	public void searchForProducts() {

		CheckLoginStatus(password);
		  	
    	//Check if no results will be returned for strings that do not exist in product names
    	driverW.findElement(By.className("input-lg")).sendKeys("Hewlett");
    	driverW.findElement(By.className("btn-default")).click();
    	String expectedHeading = "There is no product that matches the search criteria.";
    	String heading = driverW.findElement(By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]")).getText();
    	if(expectedHeading.equalsIgnoreCase(heading))
          	System.out.println("Hewlett doesn't return any results, which is correct--- "+heading);
    	else
          	System.out.println("Hewlett does return some results, which is incorrect--- "+heading);
    	
    	//Check if correct number of results will be returned for strings that do exist in product names
    	driverW.findElement(By.className("input-lg")).clear();
    	driverW.findElement(By.className("input-lg")).sendKeys("Mac");
    	driverW.findElement(By.xpath("//button[@class=\"btn btn-default btn-lg\"]")).click();
    	driverW.findElement(By.xpath("//*[@id='content']/h1")).click();
    	Integer searchResult = driverW.findElements(By.xpath("//*[@class=\"caption\"]")).size();
    	if(searchResult==4) 
    		System.out.println("Search for the keyword 'Mac' has returned the correct number of results "+ searchResult);
    	else
    		System.out.println("Search for the keyword 'Mac' has returned incorrect number of results");	
    	
    	//Check if correct number of results will be returned for strings that do exist in product names and descriptions
    	driverW.findElement(By.xpath("//input[@id='description']")).click();
    	driverW.findElement(By.xpath("//input[@id='button-search']")).click();
    	driverW.findElement(By.xpath("//*[@id='content']/h1")).click();
    	Integer searchResultDesc = driverW.findElements(By.xpath("//*[@class=\"caption\"]")).size();
    	if(searchResultDesc==6) 
    		System.out.println("Search for the keyword 'Mac' has returned the correct number of results "+ searchResultDesc);
    	else
    		System.out.println("Search for the keyword 'Mac' has returned incorrect number of results");
    	   	
    	//Check if correct number of results will be returned for strings that do exist in product names and descriptions but only for one subcategory
    	new Select(driverW.findElement(By.name("category_id"))).selectByValue("27");
    	driverW.findElement(By.xpath("//input[@name='sub_category']")).click();
    	driverW.findElement(By.xpath("//input[@id='button-search']")).click();
    	driverW.findElement(By.xpath("//*[@id='content']/h1")).click();
    	
    	Integer searchResultSubcat = driverW.findElements(By.xpath("//*[@class=\"caption\"]")).size();
    	if(searchResultSubcat==1) 
    		System.out.println("Search for the keyword 'Mac' has returned the correct number of results "+ searchResultSubcat);
    	else
    		System.out.println("Search for the keyword 'Mac' has returned incorrect number of results");
	}
}


