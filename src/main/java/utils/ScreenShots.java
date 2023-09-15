package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShots {

    public static void screenShot(WebDriver driver) {

        TakesScreenshot tks = (TakesScreenshot) driver;
        File picture = tks.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        try {
            Files.copy(picture.toPath(), new File("screenshots/screen "+timestamp+".png").toPath());

        } catch (IOException e) {
            Log.error("Picture could not be saved!");
            Log.error(e.getMessage());

        }
    }

}
