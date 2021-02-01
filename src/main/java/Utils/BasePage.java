package Utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver	= driver;
        wait = new WebDriverWait(driver,5);
    }


    public WebElement element(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }


    public void sendKeys(By locator,String value) {

        element(locator).sendKeys(value);
    }


    public void SelectElement(WebElement element,String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }


    public void thread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public WebElement find(By by) {
        WebElement x = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", x);
        return x;
    }


}