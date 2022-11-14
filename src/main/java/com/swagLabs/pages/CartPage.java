package com.swagLabs.pages;

import com.swagLabs.utilities.ObjectUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends ObjectUtility {
    WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class=\"inventory_item_name\"]")
    List<WebElement> inventoryList;
    @FindBy(xpath = "//button[@class=\"btn btn_secondary btn_small btn_inventory\"]")
    WebElement remove;
    @FindBy(id = "shopping_cart_container")
    WebElement Cart;
    public List<String> getInventoryListFromCart(){
        List<String> cartItems=new ArrayList<>();
        for (int i=0;i< inventoryList.size();i++){
            cartItems.add(page.getElementText(inventoryList.get(i)));
        }
        return cartItems;
    }
    public void selectTheItemToRemove(String item){
            for (int i=0;i<inventoryList.size();i++){
                String data=page.getElementText(inventoryList.get(i));
                if(item.equalsIgnoreCase(data)){
                    page.clickOnElement(inventoryList.get(i));
                }
            }
    }
    public void clickOnRemove(){
        page.clickOnElement(remove);
    }
    public void clickOnCart(){
        page.clickOnElement(Cart);
    }

}
