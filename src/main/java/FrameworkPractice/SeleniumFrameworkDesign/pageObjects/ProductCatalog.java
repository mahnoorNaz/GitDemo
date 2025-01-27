package FrameworkPractice.SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import FrameworkPractice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	WebDriver driver;
	
	
	public ProductCatalog(WebDriver driver)
	{
		// if we need any initialization code it would be here
		
		super(driver); // every child have to give
		this.driver=driver;
		// the first driver is from local class
		
		PageFactory.initElements(driver, this); // method so that elements can find in driver - interview questions.
	}
	
	
	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> ProductsList;
	
	@FindBy(css=".ng-animating")
	WebElement Loader;

	By productsBy = By.cssSelector("//div[@class='card-body']");
	By addToCart = By.cssSelector(".btn.w-10.rounded");
	By ToastContainer =  By.cssSelector("#toast-container");

	
	public List<WebElement> getProductsList() {
		
		//waitForElementToAppear(productsBy);
		return ProductsList;
		
	}
	
	public WebElement getProductByName(String productTitle)
	{
		waitForallElementsToAppear(ProductsList);
		 WebElement Prod = getProductsList().stream()
	                .filter(s -> s.findElement(By.cssSelector("h5")).getText().equals(productTitle))
	                .findFirst()
	                .orElse(null);
		 
		 return Prod;
	}
	
	public void addProductToCart(String productTitle)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(getProductByName(productTitle).findElement(addToCart)).click().perform();
		waitForElementToAppear(ToastContainer);
		waitForElementToDisAppear(Loader);

	}

		
	}


