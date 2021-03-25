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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartTestCloud {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() throws MalformedURLException {
        //driver = new ChromeDriver();
//        driver = new RemoteWebDriver(new URL("http://192.168.10.113:4444/wd/hub"), new ChromeOptions());
        //driver = new RemoteWebDriver(new URL("http://192.168.10.113:4444/wd/hub"), new ChromeOptions());
        String USERNAME = "bsuser7502162784";
        String AUTOMATE_KEY = "9WAzQgv5brdpWZwju1pc";
        String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
        driver = new RemoteWebDriver(new URL(URL),new ChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void cartTest() {
        driver.get("https://automate.browserstack.com/dashboard/v2/quick-start/integrate-test-suite-step#setup-authentication");
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}