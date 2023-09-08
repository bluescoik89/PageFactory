package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.TestNgListener;

import static org.testng.Assert.assertTrue;

@Listeners(TestNgListener.class)
public class LoginTest extends BaseTest {
    @Test(priority = 1)
    public void login(){

        app.click(app.menu.myAccountLink);
        app.myAccount.loginInApp("customer", "customer@123");
        assertTrue(app.checkElementIsDisplayed(app.myAccount.greetingsMessage));
        app.click(app.myAccount.logOutButton);

    }

    @Test(priority = 2)
    public void loginInvalid(){

        app.click(app.menu.myAccountLink);
        app.myAccount.loginInApp("customer123", "customer@123");
        assertTrue(app.checkElementIsDisplayed(app.myAccount.greetingsMessage));
        app.click(app.myAccount.logOutButton);

    }

}
