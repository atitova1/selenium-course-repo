package tests;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() throws MalformedURLException {
        driver = new ChromeDriver();
        //driver = new RemoteWebDriver(new URL("http://192.168.10.113:4444/wd/hub"), new FirefoxOptions());
        //driver = new RemoteWebDriver(new URL("http://192.168.10.113:4444/wd/hub"), new ChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void cartTest() throws InterruptedException {
        addProducts(3);
        driver.findElement(By.partialLinkText("Checkout")).click();
        Thread.sleep(1000);
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
            WebElement dataTable = driver.findElement(By.cssSelector(".dataTable"));
            driver.findElement((By.cssSelector("[name = remove_cart_item]"))).click();
            wait.until(ExpectedConditions.stalenessOf(dataTable));
            dataTable = driver.findElement(By.cssSelector(".dataTable"));
            wait.until(elementToBeClickable(By.cssSelector("[name = remove_cart_item]")));
        }
        driver.findElement((By.cssSelector("[name = remove_cart_item]"))).click();
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}