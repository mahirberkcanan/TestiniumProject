package testCases;


import Utils.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import TestCaseFunction.LoginFunction;
import listener.Listener;


@Listeners({Listener.class})
public class LoginTest extends BaseTest {

    @Test
    public void loginTestCase() {
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.LoginForm();
    }

}
