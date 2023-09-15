package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;


public class BaseTest extends Driver {

    public static WebDriver driver;
    public BasePage app;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(String browser) {
        //System.setProperty("webdriver.chrome.driver", "path catre/chromedriver.exe")
        //driver = new ChromeDriver();

        driver = initDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();//maximizeaza fereastra browserului
        driver.get("https://keyfood.ro/");
        app = new BasePage(driver);

    }

    @AfterClass
    public void teardown() throws InterruptedException {
        Thread.sleep(4000);//bad practice

        driver.quit();//inchide tot browserul cu toate taburile
    }

    public void recordFailure(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenShots.screenShot(driver);
        }
    }


}
