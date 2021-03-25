package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class FirefoxOldLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;
//"/Users/ruatoam/Developer/Firefox/Firefox.app/Contents/MacOS/firefox"

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/Caskroom/chromedriver/83.0.4103.39/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        DesiredCapabilities desired = new DesiredCapabilities();
        File pathBinary = new File("/Users/ruatoam/Developer/Firefox/Firefox.app/Contents/MacOS/firefox");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        desired.setCapability(FirefoxDriver.MARIONETTE, options.setBinary(firefoxBinary));
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void loginTest() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}