package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.ProductPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;


public class MainProductsPage extends Page {

    public MainProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
    }

    @FindBy(css = ".quantity")
    public WebElement quantity;

    @FindBy(partialLinkText = "Checkout")
    public WebElement checkout;


    public void chooseProduct(int productNum) {
        driver.findElements(By.cssSelector(".product .name")).get(productNum).click();
    }


}
