package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWrappers extends BaseTest {

    public SeleniumWrappers(WebDriver driver) {
        this.driver = driver;

    }

    //webelement.click();

    public void click(WebElement element) {
        Log.info("Called method<click> on  " );
        try {
            //WebElement element = driver.findElement(locator);
            WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            //returnWebElement(locator).click();
            element.click();

        }catch(StaleElementReferenceException e) {
            Log.info("StateElement exception caught -> retrying to find element");

            //WebElement element = driver.findElement(locator);
            WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.stalenessOf(element));
            element.click();

        }catch(NoSuchElementException e) {
            Log.error(e.getMessage());
            //Log.error(e.getStackTrace());

        }

    }

    public void sendKeys(WebElement element, String text) {
        Log.info("called method <sendKeys> on element " + element.getAttribute("outerHTML"));
        try {
            //WebElement element = driver.findElement(locator);
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(text);

        }catch (Exception e) {
            Log.error(e.getMessage());
        }

    }


    public void waitForElementToBeVisible(WebElement element) {
        Log.info("Called method <waitForElementToBeVisible> on locator :" );
        try {
            //WebElement element = driver.findElement(locator);
            WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));

        }catch(NoSuchElementException e) {
            Log.error(e.getMessage());
        }
    }

    public WebElement returnWebElement(By locator) {
        return driver.findElement(locator);
    }

    public boolean checkElementIsDisplayed(WebElement element){
        return element.isDisplayed();
    }
}
