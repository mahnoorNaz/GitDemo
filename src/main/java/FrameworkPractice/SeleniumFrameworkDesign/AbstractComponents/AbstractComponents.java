package FrameworkPractice.SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement ViewCartButton;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement ViewOrdersButton;
	
	

	public void waitForElementToAppear(By FindBy)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToDisAppear(WebElement ele)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	public void waitForallElementsToAppear(List<WebElement> productsList)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOfAllElements(productsList));
		
	}
	
	public void waitForElementToBeClickable(WebElement option)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.elementToBeClickable(option));
		
	}

	
	public void verifyText(WebElement element, String expectedText) {
	    String actualText = element.getText();
	    Assert.assertEquals(actualText, expectedText, "Text does not match!");
	}
	
	public void goToCartPage()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(ViewCartButton).click().build().perform();
	}
	
	public void goToOrdersPage()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(ViewOrdersButton).click().build().perform();
	}

}
