package com.swagLabs.pages;

import com.swagLabs.utilities.ObjectUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ObjectUtility {

    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "user-name")
    WebElement UserName;
    @FindBy(id= "password")
    WebElement Password;
    @FindBy(id = "login-button")
    WebElement Login_Button;
    @FindBy(xpath = "//div[@class='error-message-container error']")
    WebElement error;

    public void enterUserName(String Uname){
        page.clearText(UserName);
        page.enterText(UserName,Uname);
    }
    public void enterPassword(String pass){
        page.clearText(Password);
        page.enterText(Password,pass);
    }
    public InventoryPage clickOnLoginButton(){
        page.clickOnElement(Login_Button);
        return new InventoryPage(driver);
    }
    public String getValidationMessage(){
        String message=page.getElementText(error);
        return message;
    }
}
