import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void cartTest() {
        addProducts(3);
        driver.findElement(By.partialLinkText("Checkout")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        deleteAllProducts();
    }

    public void addProducts(int count) {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        int cartCount = Integer.parseInt(driver.findElement(By.cssSelector(".quantity")).getText());
        for (int i = 0; i < count; i++) {
            driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
            driver.findElements(By.cssSelector(".product .name")).get(0).click();
            driver.findElement(By.cssSelector("[name = add_cart_product]")).click();
            cartCount++;
            wait.until(textToBePresentInElementLocated(By.cssSelector(".quantity"), String.valueOf(cartCount)));
        }
    }

    public void deleteAllProducts(){
        int uniqueProductsCount = driver.findElements(By.cssSelector(".shortcut")).size();
        for (int i = 0; i < uniqueProductsCount - 1; i++) {
            driver.findElements(By.cssSelector(".shortcut")).get(0).click();
            driver.findElement((By.cssSelector("[name = remove_cart_item]"))).click();
            wait.until(elementToBeClickable(By.cssSelector("[name = remove_cart_item]")));
            wait.until(visibilityOfElementLocated(By.cssSelector(".dataTable")));
        }
        driver.findElement((By.cssSelector("[name = remove_cart_item]"))).click();
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}