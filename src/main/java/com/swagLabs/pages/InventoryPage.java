package com.swagLabs.pages;

import com.github.javafaker.Faker;
import com.swagLabs.utilities.ObjectUtility;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends ObjectUtility {
    WebDriver driver;

    @FindBy(xpath = "//button[@class=\"btn btn_primary btn_small btn_inventory\"]")
    WebElement AddToCart;
    @FindBy(xpath = "//div[@class=\"inventory_item_name\"]")
    List<WebElement> availableProducts;
    @FindBy(id = "shopping_cart_container")
    WebElement Cart;
    public InventoryPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public String getInventoryPageUrl(){
        String url=page.getPageUrl(driver);
        return url;
    }
    public String selectAnItemFromProducts(){
        int size=availableProducts.size();
        int randomDigit= random.randomDigit(0,size);
        String item=null;
        for (int i=0;i<size;i++){
            if(i==randomDigit){
                item=page.getElementText(availableProducts.get(i));
                page.clickOnElement(availableProducts.get(i));
            }
        }
        return item;
    }

    public void clickOnAddTOCart(){
                page.clickOnElement(AddToCart);
        }
   public CartPage ClickOnCart(){
        wait.waitUntilVisibilityOfElement(500,driver,Cart);
        page.clickOnElement(Cart);
        return new CartPage(driver);
   }



}
