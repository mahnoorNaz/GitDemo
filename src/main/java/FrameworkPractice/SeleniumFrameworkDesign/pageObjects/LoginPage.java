package FrameworkPractice.SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkPractice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

// Page object only focuses on elements and actions - no data

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	
	// creating a constructor which is named as class name and it is the first method always to be executed when class is run
	
	public LoginPage(WebDriver driver)
	{
		// if we need any initialization code it would be here
		
		super(driver); 
		// sending info to parent (only if created a constructor in parent)
		
		this.driver=driver;
		// the first driver is from local class
		
		PageFactory.initElements(driver, this); // method so that elements can find in driver - interview questions.
	}
	
	
	
	//PageFactory 
	
	@FindBy(id="userEmail")
	WebElement Email;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement LoginButton;
	
	
	
	@FindBy(css=".toast-message")

			
	WebElement ErrorMessage;
	
	
	
	// it means - WebElement usernEmail = driver.findElement(By.id("userEmail"));
	
	public void loginApplication(String email, String pass)
	{
		invokeBrowser();
		Email.sendKeys(email);
		Password.sendKeys(pass);
		LoginButton.click();
	}
	
	public void invokeBrowser()
	{
		 driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}

}
