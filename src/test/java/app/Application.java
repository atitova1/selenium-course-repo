package app;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class Application {

    private WebDriver driver;


    private MainProductsPage mainProductsPage;
    private ProductPage productPage;
    private OrderPage orderPage;
    private CheckoutPage checkOutPage;
    private YandexPage yandexPage;
    private FoundResultsPage foundResultsPage;

    public Application() {
        driver = new ChromeDriver();
        mainProductsPage = new MainProductsPage(driver);
        productPage = new ProductPage(driver);
        orderPage = new OrderPage(driver);
        checkOutPage = new CheckoutPage(driver);
        yandexPage = new YandexPage(driver);
        foundResultsPage = new FoundResultsPage(driver);
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

    public void isCartEmpty() {checkOutPage.isCartEmpty();}

    public void yandexSearch() {
        yandexPage.open();
        yandexPage.makeSearch();
    }

    public void flatsAreFound() {
        foundResultsPage.resultsFound();
    }

    public void visitEverySite() throws InterruptedException {
        foundResultsPage.visitEverySite();
    }
}
