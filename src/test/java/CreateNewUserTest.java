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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateNewUserTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void createUserTest() {
        String generatedEmail = generateEmail();
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/create_account");
        driver.findElement(By.cssSelector("[name = firstname]")).sendKeys("firstname"
                + Keys.TAB + "lastname"
                + Keys.TAB + "addres");
        driver.findElement(By.cssSelector("[name = postcode]")).sendKeys("12345"+ Keys.TAB + "City" + Keys.TAB + Keys.ENTER);
        driver.findElement(By.cssSelector("[type = search]")).sendKeys("United States" + Keys.ENTER);
        driver.findElement(By.cssSelector("[name = email]")).sendKeys(generatedEmail + Keys.TAB + "78987989900");
        driver.findElement(By.cssSelector("[name = password]")).sendKeys("qwerty1"+ Keys.TAB + "qwerty1");
        driver.findElement(By.cssSelector("[name = create_account]")).click();
        driver.findElement(By.linkText("Logout")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("[name = email]")).sendKeys(generatedEmail + Keys.TAB + "qwerty1");
        driver.findElement(By.cssSelector("[name = login]")).click();
    }

    public String generateEmail() {
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
        return generatedString + "@gmail.com";
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}