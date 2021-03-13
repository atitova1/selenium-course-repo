import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddNewProductTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void addNewProductTest() {
        LoginStep loginStep = new LoginStep(driver, wait);
        loginStep.loginTest();
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=catalog&doc=catalog");
        driver.findElement(By.linkText("Add New Product")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        String productName = generateProductName();
        driver.findElement(By.cssSelector("input[name = status][value = \"1\"]")).click();
        driver.findElement(By.cssSelector("[name ^= name]")).sendKeys(productName + Keys.TAB + "Product code");
        driver.findElement(By.cssSelector("[data-name='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("[value='1-2']")).click();
        driver.findElement(By.cssSelector("[name=quantity]")).clear();
        driver.findElement(By.cssSelector("[name=quantity]")).sendKeys("10");
        URL res = AddNewProductTest.class.getClassLoader().getResource("Doggy.png");
        String absolutePath = res.getPath();
        driver.findElement(By.cssSelector("[name^=new_images]")).sendKeys(absolutePath);
        driver.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("12122020"+ Keys.TAB + "12122100");

        //Вкладка information
        driver.findElement(By.linkText("Information")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        driver.findElement(By.cssSelector("[name=manufacturer_id]")).click();
        driver.findElement(By.cssSelector("[name=manufacturer_id] option[value = '1']")).click();
        driver.findElement(By.cssSelector("[name=keywords]")).click();
        driver.findElement(By.cssSelector("[name=keywords]")).sendKeys("Keyword");
        driver.findElement(By.cssSelector("[name^=short_description]")).sendKeys("Short description");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Super puper description");
        driver.findElement(By.cssSelector("[name^=head_title]")).sendKeys("Head title");
        driver.findElement(By.cssSelector("[name^=meta_description]")).sendKeys("Meta-meta");

        //Вкладка Price
        driver.findElement(By.linkText("Prices")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        driver.findElement(By.cssSelector("[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("[name=purchase_price]")).sendKeys("10");
        driver.findElement(By.cssSelector("[name=purchase_price_currency_code]")).click();
        driver.findElement(By.cssSelector("[name=purchase_price_currency_code] option[value = USD]")).click();
        driver.findElement(By.cssSelector("[name*=USD]")).sendKeys("300");
        driver.findElement(By.cssSelector("[name*=EUR]")).sendKeys("500");
        driver.findElement(By.id("add-campaign")).click();
        driver.findElement(By.cssSelector("[name*=start_date]")).sendKeys("12122020" + Keys.ARROW_RIGHT + "0000");
        driver.findElement(By.cssSelector("[name*=end_date]")).sendKeys("12122021" + Keys.ARROW_RIGHT + "0000");
        driver.findElement(By.cssSelector("[name*=percentage]")).clear();
        driver.findElement(By.cssSelector("[name*=percentage]")).sendKeys("213");
        driver.findElement(By.cssSelector("[name=save]")).click();

        //Проверка добавления продукта
        Assert.assertTrue(driver.findElement(By.linkText(productName)).isDisplayed());
    }

    public String generateProductName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return "Duck " + generatedString;
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}