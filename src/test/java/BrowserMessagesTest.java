import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BrowserMessagesTest {

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
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> elementsList = driver.findElements(By.cssSelector(".dataTable a"));
        List<String> productsList = new ArrayList<>();

        for (WebElement w: elementsList) {
            if (!w.getText().equals("") && !w.getText().equals("[Root]") && !w.getText().equals("Rubber Ducks") && !w.getText().equals("Subcategory")){
                productsList.add(w.getText());
            }
        }
        for (String w: productsList) {
            driver.findElement(By.linkText(w)).click();
            Assert.assertTrue(driver.manage().logs().get("browser").getAll().isEmpty());
            driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=catalog&doc=catalog&category_id=1");
        }

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}