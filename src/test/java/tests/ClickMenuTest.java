package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClickMenuTest{

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void menuClickTest() {
        LoginStep loginStep = new LoginStep(driver, wait);
        loginStep.loginTest();
        CheckExistence checkExistence = new CheckExistence(driver, wait);
        wait = new WebDriverWait(driver,10);
        ArrayList<String> menuItems = new ArrayList();
        List<WebElement> elementsList = driver.findElements(By.cssSelector("#box-apps-menu #app- a"));

        for (WebElement i: elementsList
             ) {
            menuItems.add(i.getText());
        }
        for ( String m: menuItems
             ) {
            driver.findElement(By.linkText(m)).click();
            List<WebElement> superlist =  driver.findElements(By.cssSelector("h1"));
            for (WebElement i10: superlist
                 ) {
                System.out.println(i10.getText());
            }

            List<WebElement> subElementsList = driver.findElements(By.cssSelector("ul.docs a"));
            ArrayList<String> subMenuItem = new ArrayList<>();
            for (WebElement i2: subElementsList
            ) {
                subMenuItem.add(i2.getText());
            }
            for (String m2: subMenuItem
                 ) {
                driver.findElement(By.linkText(m2)).click();
                assertTrue(driver.findElements(By.cssSelector("h1")).size()>0);
            }
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}