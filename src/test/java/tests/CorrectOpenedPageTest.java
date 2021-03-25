package tests;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CorrectOpenedPageTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void productNameCheckTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        String mainPageProductName = driver.findElement(By.cssSelector("#box-campaigns li:first-child .name")).getText();
        driver.findElement(By.cssSelector(("#box-campaigns li:first-child"))).click();
        String productName = driver.findElement(By.cssSelector("h1.title")).getText();
        Assert.assertTrue(mainPageProductName.equals(productName));
    }

    @Test
    public void productPriceCheckTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        String mainProductSalePrice = driver.findElement(By.cssSelector("#box-campaigns li:first-child .campaign-price")).getText();
        driver.findElement(By.cssSelector(("#box-campaigns li:first-child"))).click();
        String productSalePrice = driver.findElement(By.cssSelector(".campaign-price")).getText();
        Assert.assertTrue(mainProductSalePrice.equals(productSalePrice));

        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        String mainProductPrice = driver.findElement(By.cssSelector("#box-campaigns li:first-child .regular-price")).getText();
        driver.findElement(By.cssSelector(("#box-campaigns li:first-child"))).click();
        String productPrice = driver.findElement(By.cssSelector(".regular-price")).getText();
        Assert.assertTrue(mainProductPrice.equals(productPrice));
    }

    @Test
    public void mainPagePriceColourTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        System.out.print(driver.findElement(By.cssSelector("#box-campaigns li:first-child .regular-price")).getCssValue("color"));
        Color color = Color.fromString(driver.findElement(By.cssSelector("#box-campaigns li:first-child .regular-price")).getCssValue("color"));
        int red = color.getColor().getRed();
        int green = color.getColor().getGreen();
        int blue = color.getColor().getBlue();
        Assert.assertTrue(red==blue&&blue==green);
        Assert.assertTrue(driver.findElement(By.cssSelector("#box-campaigns li:first-child .regular-price")).getCssValue("text-decoration")!=null);
    }
    @Test
    public void productPagePriceColourTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        driver.findElement(By.cssSelector(("#box-campaigns li:first-child"))).click();
        Color color = Color.fromString(driver.findElement(By.cssSelector(".regular-price")).getCssValue("color"));
        int red = color.getColor().getRed();
        int green = color.getColor().getGreen();
        int blue = color.getColor().getBlue();
        Assert.assertTrue(red==blue&&blue==green);
        Assert.assertTrue(driver.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration")!=null);
    }


    @Test
    public void mainPageSalePriceColourTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        System.out.print(driver.findElement(By.cssSelector("#box-campaigns li:first-child .campaign-price")).getCssValue("color"));
        Color color = Color.fromString(driver.findElement(By.cssSelector("#box-campaigns li:first-child .campaign-price")).getCssValue("color"));
        int green = color.getColor().getGreen();
        int blue = color.getColor().getBlue();
        Assert.assertTrue(blue==0&&green==0);
        System.out.println(driver.findElement(By.cssSelector("#box-campaigns li:first-child .campaign-price")).getCssValue("font-weight"));
        // bold = 700
        Assert.assertTrue(driver.findElement(By.cssSelector("#box-campaigns li:first-child .campaign-price")).getCssValue("font-weight").equals("700"));
    }
    @Test
    public void productPageSalePriceColourTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        driver.findElement(By.cssSelector(("#box-campaigns li:first-child"))).click();
        Color color = Color.fromString(driver.findElement(By.cssSelector(".campaign-price")).getCssValue("color"));
        int green = color.getColor().getGreen();
        int blue = color.getColor().getBlue();
        Assert.assertTrue(blue==0&&green==0);
        // bold = 700
        Assert.assertTrue(driver.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight").equals("700"));
    }

    @Test
    public void mainPagePriceSizeTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        String sizeRegularFont = driver.findElement(By.cssSelector("#box-campaigns li:first-child .regular-price")).getCssValue("font-size").replaceAll("[^0-9.]","");
        String sizeCampaignFont = driver.findElement(By.cssSelector("#box-campaigns li:first-child .campaign-price")).getCssValue("font-size").replaceAll("[^0-9.]","");
        Double d = Double.valueOf(sizeRegularFont);
        Double c = Double.valueOf(sizeCampaignFont);
        Assert.assertTrue(c > d);
    }
    @Test
    public void productPagePriceSizeTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
        driver.findElement(By.cssSelector(("#box-campaigns li:first-child"))).click();
        String sizeRegularFont = driver.findElement(By.cssSelector(".regular-price")).getCssValue("font-size").replaceAll("[^0-9.]","");
        String sizeCampaignFont = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size").replaceAll("[^0-9.]","");
        Double d = Double.valueOf(sizeRegularFont);
        Double c = Double.valueOf(sizeCampaignFont);
        Assert.assertTrue(c > d);
    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}