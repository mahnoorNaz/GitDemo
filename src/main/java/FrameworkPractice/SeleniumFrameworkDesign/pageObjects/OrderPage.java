package FrameworkPractice.SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import FrameworkPractice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver); // every child have to give
		this.driver=driver;
		// the first driver is from local class
		
		PageFactory.initElements(driver, this); // method so that elements can find in driver - interview questions.
	}
	
	@FindBy (xpath="//tbody/tr/td[2]")
	List<WebElement> OrderedItems;
	
	

	public Boolean verifyItemInOrdersList(String productTitle)
	{
		
		goToOrdersPage();
		waitForallElementsToAppear(OrderedItems);
		return OrderedItems.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productTitle));	
			
	}



	
	
	
}
