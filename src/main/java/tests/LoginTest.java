package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void login(){

        app.click(app.menu.myAccountLink);
        app.myAccount.loginInApp("customer", "customer@123");
        assertTrue(app.checkElementIsDisplayed(app.myAccount.greetingsMessage));

    }
}
