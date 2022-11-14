package com.swagLabs.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitUtility {
        public void hardWait(long time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void implicitWait(long time, WebDriver driver) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        }

        public void waitUntilVisibilityOfElement(long time, WebDriver driver, WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        public void waitUntilElementIsClickable(long time, WebDriver driver, WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }


        public void fluentWait(long time, long ftime, WebDriver driver, WebElement element) {
            Wait wait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(time))
                    .pollingEvery(Duration.ofSeconds(ftime))
                    .ignoring(Exception.class);
            wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return element;
                }
            });
        }
}
