package stepdefinitions;


import org.junit.Assert;
import org.openqa.selenium.By;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps extends BaseClass {
	
	LoginPage loginPageObj = new LoginPage(); 
	
	@Given("user is on Login Page") 
	public void user_is_on_Login_Page() {
	
		  // URL already open ho chuka hai Hooks se
        // yahan kuch karna zaroori nahi
	}
	
	@When("user enters valid username aur password")
	public void user_enters_valid_username_aur_password() {
		
		loginPageObj.enterUserName(prop.getProperty("username")); // prop.getProperty("username") config.properties se username read karega
		loginPageObj.enterPassword(prop.getProperty("password"));  // prop.getProperty("password") config.properties se password read karega
		loginPageObj.clickOnLoginBtn();
			
	}
	
	@Then("User should be logged in successfully")
	public void User_should_be_logged_in_successfully() {
		
		String logoText = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
		
		
		 // ✅ Logo element find karo using XPath				
		boolean isLogoVisible = driver.findElement(By.xpath("//div[@class='app_logo']")).isDisplayed();
		
		//Logo ka naam print karo
		
		System.out.println("Logo Name is : " + logoText);
		
		//Assertion		
		Assert.assertTrue("Login Failed - Logo not visible", isLogoVisible);
		
		System.out.println("User Login Successfully");
		
		 
		
	}

}
