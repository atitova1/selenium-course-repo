package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class OrderPage extends Page{

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/checkout");
    }

    @FindBy(css = ".shortcut")
    public List<WebElement> shortcuts;

    @FindBy(css = ".dataTable")
    public WebElement dataTable;

    @FindBy(name = "remove_cart_item")
    public WebElement removeCartItem;


    public void deleteAllProducts(){
        int uniqueProductsCount = shortcuts.size();
        for (int i = 0; i < uniqueProductsCount - 1; i++) {
            shortcuts.get(0).click();
            WebElement newDataTable = dataTable;
            removeCartItem.click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(".dataTable"))));
            newDataTable = dataTable;
            wait.until(elementToBeClickable(removeCartItem));
        }
        removeCartItem.click();

    }
}
