import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxNewLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/Caskroom/chromedriver/83.0.4103.39/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void loginTest(){
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