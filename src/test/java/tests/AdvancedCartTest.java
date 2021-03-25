package tests;
import app.Application;
import org.junit.jupiter.api.Test;


public class AdvancedCartTest extends TestBase {


    @Test
    public void cartTest() throws InterruptedException{
        app.addProducts(3);
        app.makeCheckout();
        Thread.sleep(1000);
        app.deleteAllProducts();
    }
}