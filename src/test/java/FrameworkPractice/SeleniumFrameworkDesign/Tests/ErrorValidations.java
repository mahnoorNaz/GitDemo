package FrameworkPractice.SeleniumFrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import FrameworkPractice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import FrameworkPractice.SeleniumFrameworkDesign.TestComponents.Retry;
import FrameworkPractice.SeleniumFrameworkDesign.pageObjects.LoginPage;

public class ErrorValidations extends BaseTest{
	
	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
	// we will add this retry analyzer if we think or expect that it is gonna be a flaky failure
    public void IncorrectCredentials() throws IOException {

        LoginPage loginpage = new LoginPage(driver);
        loginpage.invokeBrowser();
        loginpage.loginApplication("test@test.com", "ok@123");
        Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
	
	}
	
	

}
