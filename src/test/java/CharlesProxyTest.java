import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CharlesProxyTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void start() {
        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("127.0.0.1:9090");
        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("proxy", proxy);
        proxy.setHttpProxy("127.0.0.1:9090");
        proxy.setFtpProxy("127.0.0.1:9090");
        proxy.setSslProxy("127.0.0.1:9090");
        proxy.setAutodetect(false);
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        caps.setCapability(CapabilityType.PROXY, proxy);

        driver = new ChromeDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void addNewProductTest() {
        driver.get("https://software-testing.ru/lms/mod/assign/view.php?id=187896");
        driver.get("https://software-testing.ru/lms/mod/assign/view.php?id=187896");

    }

//    @AfterEach
//    public void stop() {
//        driver.quit();
//        driver = null;
//    }
}