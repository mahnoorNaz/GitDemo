package FrameworkPractice.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver;
	@BeforeMethod(alwaysRun=true)
	public WebDriver initializeDriver() throws IOException
	{
		
		Properties prop = new Properties();
		// we wont hardcode the path so it works everywhere
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//FrameworkPractice//SeleniumFrameworkDesign//resources//GlobalData.properties");
		prop.load(fis);
		
		// this is java ternary operator
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if (browserName.contains("chrome"))
			
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			
			// to make sure that in headless mode screen is maximized and there are no element invisble 
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		  
			return driver;
	}
	
	@AfterMethod(alwaysRun=true)
	public void TearDown()
	{
		driver.quit();
	}
	
	public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
	{
	
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
         
		// String to hashmap jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
				{
			
		});
		return data;
	
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File File = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
	    FileUtils.copyFile(source, File);
	    return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	

}
