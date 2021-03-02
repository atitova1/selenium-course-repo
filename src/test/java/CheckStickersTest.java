import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckStickersTest {
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void checkStickersTest (){
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");

        List<WebElement> productsList = driver.findElements(By.cssSelector(".product"));
        for (WebElement e: productsList
             ) {
            Assert.assertTrue(e.findElements(By.cssSelector(".product .sticker")).size()==1);
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
