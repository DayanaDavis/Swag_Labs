package com.swagLabs.testscripts;

import com.swagLabs.automationCore.Base;
import com.swagLabs.constants.Constants;
import com.swagLabs.pages.InventoryPage;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.utilities.ExcelUtility;
import com.swagLabs.utilities.RandomUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends Base {
    LoginPage login;
    InventoryPage inventory;
    ExcelUtility excel;
    RandomUtility random;
    @Test(dataProvider = "getLoginDataFromExcel",description = "verify user able to login with valid user credential",groups = {"smoke","regression"})
    public void verify_that_user_able_to_login_with_valid_usernames_and_passwords(String username,String password){
        login=new LoginPage(driver);
        login.enterUserName(username);
        login.enterPassword(password);
        inventory=login.clickOnLoginButton();
        String expected_Url="https://www.saucedemo.com/inventory.html";
        String actualUrl=inventory.getInventoryPageUrl();
        Assert.assertEquals(actualUrl,expected_Url,"Not a matching Url");
        }
        @DataProvider(name="getLoginDataFromExcel")
        public Object[][] GetLoginExcelData() throws IOException {
            excel=new ExcelUtility();
            String filepath = System.getProperty("user.dir") + Constants.EXCEL_FILE;
            Object[][] excelData=excel.readDataFromExcelUsingDataFormatter(filepath,"LoginPage");
            return excelData;
        }
    @Test(description = "verify that user not able to login with invalid credentials",groups = {"smoke","regression"})
    public void verify_that_user_not_able_to_login_with_invalid_credentials(){
        login=new LoginPage(driver);
        random=new RandomUtility();
        excel=new ExcelUtility();
        String username= random.userName();
        String password= random.passWord();
        login.enterUserName(username);
        login.enterPassword(password);
        inventory=login.clickOnLoginButton();
        String actual_Message= login.getValidationMessage();
        String expected_Message=excel.readSingleData(1,0,"LoginError");
        Assert.assertEquals(actual_Message,expected_Message,"Not a matching Message");
    }
    @Test(description = "Verify that locked user not able to login",groups = {"regression"})
    public void verify_that_Locked_user_not_able_to_login(){
        login=new LoginPage(driver);
        excel=new ExcelUtility();
        String username= excel.readSingleData(1,1,"LoginError");
        String password= excel.readSingleData(1,2,"LoginError");
        login.enterUserName(username);
        login.enterPassword(password);
        inventory=login.clickOnLoginButton();
        String actual_Message= login.getValidationMessage();
        String expected_Message=excel.readSingleData(1,3,"LoginError");
        Assert.assertEquals(actual_Message,expected_Message,"Not a matching Message");
    }
    @Test(description = "Verify that 3 invalid attempt of a user should lock the account",groups = {"regression"})
    public void verify_that_user_account_should_be_locked_for_3_invalid_login_attempt(){
        login=new LoginPage(driver);
        excel=new ExcelUtility();
        random=new RandomUtility();
        String username= excel.readSingleData(1,0,"LoginPage");
        String password= random.passWord();
        int count=0;
        for (int i=0;i<4;i++) {
            login.enterUserName(username);
            login.enterPassword(password);
            inventory = login.clickOnLoginButton();
            count++;
        }
        String expected_message= excel.readSingleData(1,3,"LoginError");
        String actual_message= login.getValidationMessage();
        System.out.println("No of login attempt for standard_user="+count);
        Assert.assertEquals(actual_message,expected_message,"Account doesn't locked");
    }

}
