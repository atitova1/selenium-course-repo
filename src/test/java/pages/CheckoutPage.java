package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Page {
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/checkout");
    }

    @FindBy(css = "#checkout-cart-wrapper em")
    public WebElement checkoutCartWrapper;

    public void isCartEmpty() {
        String message = checkoutCartWrapper.getText();
        String emptyCartMessage = "There are no items in your cart.";
        Assert.assertTrue(message.equals(emptyCartMessage));
    }

}
