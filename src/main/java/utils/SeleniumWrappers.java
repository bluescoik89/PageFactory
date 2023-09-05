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
        System.out.println("Called method<click> on  " );
        try {
            //WebElement element = driver.findElement(locator);
            WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            //returnWebElement(locator).click();
            element.click();

        }catch(StaleElementReferenceException e) {

            //WebElement element = driver.findElement(locator);
            WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.stalenessOf(element));
            element.click();

        }catch(NoSuchElementException e) {
            System.out.println(e.getMessage());

        }

    }

    public void sendKeys(WebElement element, String text) {
        System.out.println("called method <sendKeys> on locator " );
        try {
            //WebElement element = driver.findElement(locator);
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(text);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void waitForElementToBeVisible(WebElement element) {
        System.out.println("Called method <waitForElementToBeVisible> on locator :" );
        try {
            //WebElement element = driver.findElement(locator);
            WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));

        }catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public WebElement returnWebElement(By locator) {
        return driver.findElement(locator);
    }

    public boolean checkElementIsDisplayed(WebElement element){
        return element.isDisplayed();
    }
}
