package FrameworkPractice.SeleniumFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import FrameworkPractice.SeleniumFrameworkDesign.pageObjects.LoginPage;

public class StandAloneTest2 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        
        String ProductTitle = "BANARSI SAREE";

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        
        LoginPage loginpage = new LoginPage(driver);
        driver.manage().window().maximize();

        // Login
        driver.findElement(By.id("userEmail")).sendKeys("test@mahnoor.com");
        driver.findElement(By.id("userPassword")).sendKeys("nazMah@123");
        driver.findElement(By.id("login")).click();

        // Locate products
        List<WebElement> Products = driver.findElements(By.xpath("//div[@class='card-body']"));

        // Filter the product "BANARSI SAREE"
        WebElement Prod = Products.stream()
                .filter(s -> s.findElement(By.cssSelector("h5")).getText().equals(ProductTitle))
                .findFirst()
                .orElse(null);

        
            // Create a single Actions instance
            Actions actions = new Actions(driver);

            // Scroll to the element and click the "Add to Cart" button
            actions.moveToElement(Prod).perform();
            
            WebElement addToCartButton = Prod.findElement(By.cssSelector(".btn.w-10.rounded"));
            actions.moveToElement(addToCartButton).perform();
            addToCartButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // toast is visible
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // loader is gone

            // Move to and click on the "View Cart" button using the same Actions instance
            actions.moveToElement(driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"))).click().perform();
            
            List<WebElement> ItemsinCart = driver.findElements(By.xpath("(//div[@class='cartSection']/h3)"));
            
            Assert.assertTrue(ItemsinCart.stream().anyMatch(s -> s.getText().equalsIgnoreCase(ProductTitle)));
            // we just wanted to make sure if condition matches thats why used anymatch - if we want the element and perform some action on it then we use filter 
            
            driver.findElement(By.xpath("//button[text()='Checkout']")).click();
            
         
            driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("pa");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class='ta-results list-group ng-star-inserted']")));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("section[class='ta-results list-group ng-star-inserted'] button")));

          //Use streams to locate and click the option dynamically
          driver.findElements(By.cssSelector("section[class='ta-results list-group ng-star-inserted'] button"))
             .stream().filter(option -> option.getText().equalsIgnoreCase("Pakistan")).findFirst()
             
             .ifPresent(option -> 
             {
                 wait.until(ExpectedConditions.elementToBeClickable(option));
                 actions.moveToElement(option).build().perform();
                 option.click();
             });
              WebElement SubmitButton = driver.findElement(By.cssSelector(".btnn.action__submit"));
             actions.moveToElement(SubmitButton).build().perform(); 
             SubmitButton.click();
           
            Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.") ;

            }
    }

    		

    

        //driver.quit();
    

