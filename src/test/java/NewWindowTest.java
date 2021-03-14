import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NewWindowTest {

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
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=countries&doc=countries");
        driver.findElement(By.linkText("Add New Country")).click();
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> elementsList = driver.findElements(By.cssSelector(".fa-external-link"));
        for (WebElement w: elementsList
        ) {
            w.click();
            // ожидание появления нового окна, идентификатор которого отсутствует в списке oldWindows, остаётся в качестве самостоятельного упражнения
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}