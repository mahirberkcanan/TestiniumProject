package TestCaseFunction;

import Utils.BasePage;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFunction extends BasePage {
    public LoginFunction(WebDriver driver) {
        super(driver);
    }


    public By email = By.id("L-UserNameField");
    public By password = By.id("L-PasswordField");
    public By loginClick = By.id("gg-login-enter");
    public By searchText = By.xpath("//input[@data-cy='header-search-input']");
    public By searchClick = By.xpath("//button[@type='submit']");

    JavascriptExecutor scroll = (JavascriptExecutor) driver;
    int offset = 0;



    public By AddBucket = By.xpath("//button[@id='add-to-basket']");

    public By ClickBucket = By.xpath("//div[@class='basket-container robot-header-iconContainer-cart']");

    public String priceResult =null;
    public String price = null;

    public String actualTitle = null;
    public String actualLink = null;

    public String mainAssert = "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi";
    public String loginAssert = "gittigidiyor.com";


    public By counterButton = By.xpath("/html//div[@id='cart-items-container']/div[@class='products-container']/div[6]/div[2]/div[2]/div[4]/div/span[2]");
    public By deleteButton  = By.xpath("/html//div[@id='cart-items-container']/div[@class='products-container']/div[6]/div[2]/div[2]/div[4]/div/span[1]");
    public By deleteApproveButton  = By.xpath("/html//div[@id='cart-items-container']/div[@class='products-container']/div[6]/div[2]//div[@class='row']/a[@title='Sil']");


    public void LoginForm() {


        actualTitle= driver.getTitle();
        Assert.assertTrue(actualTitle.equals(mainAssert));
        driver.manage().window().maximize();
        thread(1500);
        driver.get("https://gittigidiyor.com/uye-girisi");
        thread(1500);
        sendKeys(email,"mahirberk.test@gmail.com");
        thread(1500);
        sendKeys(password,"Test123.");
        thread(1500);
        element(loginClick).click();
        thread(1500);
        actualTitle= driver.getCurrentUrl();
        Assert.assertTrue(actualTitle.contains(loginAssert));
        sendKeys(searchText,"bilgisayar");
        thread(1500);
        element(searchClick).click();
        thread(1500);



        List<WebElement> links = driver.findElements(By.cssSelector(".catalog-view.products-container > li"));

        links.subList(0, 10).clear();

        Random rand = new Random();
        int randIndex = rand.nextInt(links.size());

        offset = 0;
        while(true) {
            try {
                scroll.executeScript("window.scrollTo(0, " + offset + ")");
                thread(2000);
                links.get(randIndex).click();
                break;
            } catch (ElementClickInterceptedException e) {
                offset += 500;
            }
        }



        //Ürün açıklamasının uzun olduğu durumlarda 'Sepete Ekle' butonu görünmüyor bu yüzden hata alıyorum
        //scroll.executeScript("scroll(0, 500);"); metodunlarını kullandığımda ise sepetim sayfasına click alamıyorum
        //Projede sorun yaşadığım tek nokta burası oldu

        scroll.executeScript("scroll(0, 300);");
        thread(1500);
        element(AddBucket).click();
        scroll.executeScript("scroll(300, 0);");
        thread(1500);



        List<WebElement> ProductPrices1 = driver.findElements(By.id("sp-price-highPrice"));
        String text1Price = ProductPrices1.get(0).getText();


        //wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket-container robot-header-iconContainer-cart']"))));
        //Sepetim bölümünün görünür olmasını beklemek için kullanmıştım, işe yaramadı



        thread(4500);
        element(ClickBucket).click();
        thread(1500);


        List<WebElement> elements = driver.findElements(By.cssSelector(".total-price > strong"));
        for (WebElement element : elements) {
            price = element.getText();

            int cntTL = (price.length() - price.replace("TL", "").length()) / 2;
            if (2 == cntTL) {
                price = price.split("TL")[1] + "TL";
            }

        }

        System.out.println("first value : "+text1Price+"Bucket Value : "+price);
        Assert.assertEquals(text1Price.replace("TL", "").trim(), price.replace("TL", "").trim());


        thread(2500);
        element(counterButton).click();
        thread(2500);
        element(deleteButton).click();
        thread(2500);
        element(deleteApproveButton).click();



    }


}
