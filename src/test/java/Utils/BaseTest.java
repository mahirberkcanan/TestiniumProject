package Utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    public static WebDriver driver;
    public String baseUrl = "https://www.gittigidiyor.com/";
    public Logger log = LogManager.getLogger(getClass().getName());


}
