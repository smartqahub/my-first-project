package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BaseClass;

public class LoginPage extends BaseClass {
	
	//Constructor	
	public LoginPage() {
		initPageFactory(this);
	}
	
	//User Name
	@FindBy(id="user-name")   //User Name box se ID liya hai
	WebElement username;   //ek WebElment banaya hai
	
	//Password
	
	@FindBy(id="password")
	
	WebElement password1;
	
	//Login button per click
	
    @FindBy(id="login-button3")
	
	WebElement loginbutton;
	
	//Action
    
    //Method for Username Type
    
    public void enterUserName(String user) {
    	
    	username.sendKeys(user);
    }
    
    //Method for Password type
    
    public void enterPassword(String pass) {
    	
    	password1.sendKeys(pass);
    }
    
    //login button per click method
    
    public void clickOnLoginBtn() {
    	
    	loginbutton.click();
    }
    
    
    
    
    
    
	

}
