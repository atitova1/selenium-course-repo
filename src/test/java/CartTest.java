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
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        int cartCount = Integer.parseInt(driver.findElement(By.cssSelector(".quantity")).getText());
        String firstProduct = driver.findElements(By.cssSelector(".product .name")).get(0).getText();
        driver.findElements(By.cssSelector(".product .name")).get(0).click();
        driver.findElement(By.cssSelector("[name = add_cart_product]")).click();
        cartCount++;
        wait.until(textToBePresentInElementLocated(By.cssSelector(".quantity"),String.valueOf(cartCount)));

        //Добавляю еще уточку
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        driver.findElement(By.partialLinkText(firstProduct)).click();
        driver.findElement(By.cssSelector("[name = add_cart_product]")).click();
        cartCount++;
        wait.until(textToBePresentInElementLocated(By.cssSelector(".quantity"),String.valueOf(cartCount)));

        //Добавляю еще уточку
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        driver.findElement(By.partialLinkText(firstProduct)).click();
        driver.findElement(By.cssSelector("[name = add_cart_product]")).click();
        cartCount++;
        wait.until(textToBePresentInElementLocated(By.cssSelector(".quantity"),String.valueOf(cartCount)));

        driver.findElement(By.partialLinkText("Checkout")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        driver.findElement(By.cssSelector("[name = quantity]")).clear();
        cartCount--;
        driver.findElement(By.cssSelector("[name = quantity]")).sendKeys(String.valueOf(cartCount));
        driver.findElement(By.cssSelector("[name = update_cart_item]")).click();
        wait.until(visibilityOfElementLocated(By.id("order_confirmation-wrapper")));
        wait.until(elementToBeClickable(By.cssSelector("[name = update_cart_item]")));

        //Удаляю еще уточку (или собачку)
        driver.findElement(By.cssSelector("[name = quantity]")).clear();
        cartCount--;
        driver.findElement(By.cssSelector("[name = quantity]")).sendKeys(String.valueOf(cartCount));
        driver.findElement(By.cssSelector("[name = update_cart_item]")).click();
        wait.until(visibilityOfElementLocated(By.id("order_confirmation-wrapper")));
        wait.until(elementToBeClickable(By.cssSelector("[name = update_cart_item]")));

        //Удаляю еще уточку (или собачку)
        driver.findElement(By.cssSelector("[name = quantity]")).clear();
        cartCount--;
        driver.findElement(By.cssSelector("[name = quantity]")).sendKeys(String.valueOf(cartCount));
        driver.findElement(By.cssSelector("[name = update_cart_item]")).click();
        wait.until(visibilityOfElementLocated(By.id("order_confirmation-wrapper")));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}