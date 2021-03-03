import org.junit.Assert;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlfabetCountryTest{

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void alfabetTest() {
        LoginStep loginStep = new LoginStep(driver, wait);
        loginStep.loginTest();
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=countries&doc=countries");
        alfabetCheckSort("[name = countries_form] :nth-child(5) a");

    }

    @Test
    public void timeZonesAflabetTest() {
        LoginStep loginStep = new LoginStep(driver, wait);
        loginStep.loginTest();
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=countries&doc=countries");
        ArrayList<String> countryItems = new ArrayList();
        List<WebElement> elementsList = driver.findElements(By.cssSelector("[name = countries_form] .row"));

        for (WebElement w: elementsList
             ) {
                if (!w.findElement(By.cssSelector("[name = countries_form] :nth-child(6)")).getText().equals("0")){
                    System.out.print(w.findElement(By.cssSelector("[name = countries_form] :nth-child(6)")).getText());
                    countryItems.add((w.findElement(By.cssSelector("[name = countries_form] :nth-child(5) a")).getText()));
                }

        }
        for (String c: countryItems
             ) {
            driver.findElement(By.linkText(c)).click();
            alfabetCheckSort("input[name*=name]");
            driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=countries&doc=countries");
        }
    }

    public void alfabetCheckSort(String locator) {

        ArrayList<String> countryItems = new ArrayList();
        ArrayList<String> countrySortedItems = new ArrayList();
        List<WebElement> elementsList = driver.findElements(By.cssSelector(locator));

        for (WebElement i: elementsList
        ) {
            countryItems.add(i.getText());
            countrySortedItems.add(i.getText());
        }
        Collections.sort(countrySortedItems);

        Assert.assertTrue(countryItems.equals(countrySortedItems));
    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}