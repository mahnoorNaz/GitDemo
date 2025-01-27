package FrameworkPractice.SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import FrameworkPractice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver); // every child have to give
		this.driver=driver;
		// the first driver is from local class
		
		PageFactory.initElements(driver, this); // method so that elements can find in driver - interview questions.
	}
	
	@FindBy(xpath="(//div[@class='cartSection']/h3)")
	List<WebElement> ItemsinCart;
	
	@FindBy(xpath="//button[text()='Checkout']")
	private WebElement CheckoutButton;
	// by making it private i achieved encapsulation ( i am hiding it)

	public Boolean verifyItemInCart(String productTitle)
	{
		
		goToCartPage();
		waitForallElementsToAppear(ItemsinCart);
		return ItemsinCart.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productTitle));	
			
	}
	
	public void Checkout()
	{
		CheckoutButton.click();
	}

}
