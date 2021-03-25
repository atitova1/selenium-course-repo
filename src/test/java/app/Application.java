package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainProductsPage;
import pages.OrderPage;
import pages.ProductPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class Application {

    private WebDriver driver;


    private MainProductsPage mainProductsPage;
    private ProductPage productPage;
    private OrderPage orderPage;

    public Application() {
        driver = new ChromeDriver();
        mainProductsPage = new MainProductsPage(driver);
        productPage = new ProductPage(driver);
        orderPage = new OrderPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProducts(int count) {
        mainProductsPage.open();
        int cartCount = Integer.parseInt(mainProductsPage.quantity.getText());
        for (int i = 0; i < count; i++) {
            mainProductsPage.open();
            mainProductsPage.chooseProduct(0);
            productPage.addButton.click();
            cartCount++;
            mainProductsPage.wait.until(textToBePresentInElementLocated(By.cssSelector(".quantity"), String.valueOf(cartCount)));
        }
    }

    public void makeCheckout() {
        mainProductsPage.checkout.click();
    }

    public void deleteAllProducts() {
        orderPage.deleteAllProducts();
    }
}
