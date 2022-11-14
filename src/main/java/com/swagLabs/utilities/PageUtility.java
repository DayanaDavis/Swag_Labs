package com.swagLabs.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class PageUtility {

    public String getPageUrl(WebDriver driver){
        String url= driver.getCurrentUrl();
        return url;
    }
    public String getPageTitle(WebDriver driver){
        String title=driver.getTitle();
        return title;
    }
    public String getElementText(WebElement element){
        String text=element.getText();
        return text;
    }
    public void clickOnElement(WebElement element){
        element.click();
    }
    public void enterText(WebElement element,String text){
        element.sendKeys(text);
    }
    public String getAttributeValue(WebElement element,String attribute){
        String value=element.getAttribute(attribute);
        return value;
    }
    public void clearText(WebElement element){
        element.clear();
    }
    public void clickUsingJavaScript(WebDriver driver,WebElement element){
        JavascriptExecutor executor= (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",element);
    }

}
