package FrameworkPractice.SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkPractice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class PlaceOrderPage extends AbstractComponents{
	WebDriver driver;

	public PlaceOrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		super(driver); // every child have to give
		this.driver=driver;
		// the first driver is from local class
		
		PageFactory.initElements(driver, this); // method so that elements can find in driver - interview questions.
	}

	@FindBy(css="input[placeholder='Select Country']")
	WebElement SelectCountry;
	
	@FindBy(css="section[class='ta-results list-group ng-star-inserted'] button")
	List<WebElement> CountryButtons;
	
	@FindBy(css=".btnn.action__submit")
	WebElement PlaceOrder;
	
	@FindBy(css=".hero-primary")
	WebElement SucsessMessage;
	
	By CountriesDropdown = By.cssSelector("section[class='ta-results list-group ng-star-inserted']");
	
	
	public void placeOrder(String countryText, String Country)
	{
		Actions actions = new Actions(driver);	
		
		SelectCountry.sendKeys(countryText);
		
		waitForElementToAppear(CountriesDropdown);
		
		
		 CountryButtons.stream().filter(option -> option.getText().equalsIgnoreCase(Country)).findFirst()
         
         .ifPresent(option -> 
         {
            waitForElementToBeClickable(option);
             actions.moveToElement(option).build().perform();
             option.click();
         });
		
		actions.moveToElement(PlaceOrder).click().build().perform();
		waitForWebElementToAppear(SucsessMessage);
		verifyText(SucsessMessage, "THANKYOU FOR THE ORDER.");
		
}

	
}
	
	
	
	