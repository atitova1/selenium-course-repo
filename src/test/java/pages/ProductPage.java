package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".quantity")
    public WebElement quantity;

    @FindBy(partialLinkText = "Checkout")
    public WebElement checkout;

    @FindBy(name = "add_cart_product")
    public WebElement addButton;

}
