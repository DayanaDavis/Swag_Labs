package com.swagLabs.testscripts;

import com.swagLabs.automationCore.Base;
import com.swagLabs.pages.CartPage;
import com.swagLabs.pages.InventoryPage;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.utilities.ExcelUtility;
import com.swagLabs.utilities.RandomUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartPageTest extends Base {

    LoginPage login;
    InventoryPage inventory;
    ExcelUtility excel;
    RandomUtility random;
    CartPage cart;

    @Test(description = "verify user able to add an Item to the cart",groups = {"smoke"})
    public void verify_that_user_able_to_add_an_item_to_the_cart() {
        login=new LoginPage(driver);
        excel=new ExcelUtility();
        random=new RandomUtility();
        String username= excel.readSingleData(1,0,"LoginPage");
        String password= excel.readSingleData(1,1,"LoginPage");
        login.enterUserName(username);
        login.enterPassword(password);
        inventory = login.clickOnLoginButton();
        String item=inventory.selectAnItemFromProducts();
        System.out.println(item);
        inventory.clickOnAddTOCart();
        cart=inventory.ClickOnCart();
        boolean actual_result=false;
        List<String>cartItems=cart.getInventoryListFromCart();
        for (int i=0;i<cartItems.size();i++){
            String value=cartItems.get(i);
            if(item.equalsIgnoreCase(value)) {
                actual_result = true;
            }
        }
        Assert.assertTrue(actual_result);

    }
    @Test(description = "verify that user able to Delete an Item from the cart",groups = {"regression"})
    public void verify_that_user_able_to_Remove_an_item_from_the_cart(){
        login=new LoginPage(driver);
        excel=new ExcelUtility();
        random=new RandomUtility();
        String username= excel.readSingleData(1,0,"LoginPage");
        String password= excel.readSingleData(1,1,"LoginPage");
        login.enterUserName(username);
        login.enterPassword(password);
        inventory = login.clickOnLoginButton();
        String item=inventory.selectAnItemFromProducts();
        System.out.println(item);
        inventory.clickOnAddTOCart();
        cart=inventory.ClickOnCart();
        cart.selectTheItemToRemove(item);
        cart.clickOnRemove();
        cart.clickOnCart();
        boolean actual_result=false;
        List<String> cartItems=cart.getInventoryListFromCart();
        for (int i=0;i<cartItems.size();i++){
            String value=cartItems.get(i);
            if(item.equalsIgnoreCase(value)) {
                actual_result = true;
            }
        }
        Assert.assertFalse(actual_result);
    }



}
