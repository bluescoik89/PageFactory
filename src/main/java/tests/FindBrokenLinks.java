package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class FindBrokenLinks extends BaseTest {

    static List<String> brokenLinks = new ArrayList<String>();

    @Test
    public void testBrokenLinks() throws IOException {
        List<WebElement> links = driver.findElements(By.cssSelector("a[href*='http']"));

        System.out.println("total links: " + links.size());

        for (int i = 0; i < links.size(); i++) {

            WebElement element = links.get(i);
            String url = element.getAttribute("href");

            if (url == null) {
                continue;
            }
            checkLinks(url);

        }

        assertTrue(brokenLinks.size() == 0);
    }

    public static void checkLinks(String linkURL) throws IOException {
        try {
            URL url = new URL(linkURL);
            HttpURLConnection httpConnect = (HttpURLConnection) url.openConnection();
            httpConnect.setConnectTimeout(3000);
            httpConnect.connect();

            if (httpConnect.getResponseCode() == 200) {
                System.out.println(linkURL + " = " + httpConnect.getResponseCode());
            }
            if (httpConnect.getResponseCode() == httpConnect.HTTP_NOT_FOUND) {

                System.out.println(linkURL + " = " + httpConnect.getResponseCode());
                brokenLinks.add(linkURL);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
