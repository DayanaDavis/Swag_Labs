package com.swagLabs.automationCore;

import com.swagLabs.constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public WebDriver driver;
    public FileInputStream fis;
    public Properties prop;
    public Base(){
        try {
            fis=new FileInputStream(System.getProperty("user.dir")+ Constants.CONFIG_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop=new Properties();
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void testInitialize(String browser,String url){
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();//driver.exe download
            driver=new ChromeDriver();
        } else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        } else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        } else {
            try {
                throw new Exception("Browser value not defined");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();//to delete all cookies and get a fresh page
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser) {
        String baseurl=prop.getProperty("url");
        testInitialize(browser,baseurl);
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE) {
            TakesScreenshot scrshot = (TakesScreenshot) driver;
            File screenshot = scrshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File("./Screenshots/"+result.getName()+".png"));
        }
        driver.quit();
    }
}
