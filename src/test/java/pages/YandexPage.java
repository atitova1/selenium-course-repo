package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class YandexPage extends Page {

    public YandexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://yandex.ru/");
    }

    @FindBy(css = ".input__control")
    public WebElement search;
//
//    @FindBy(partialLinkText = "Checkout")
//    public WebElement checkout;
//
//
    public void makeSearch(){
        driver.findElement(By.cssSelector(".input__control")).sendKeys("Купить квартиру в Москве");
        driver.findElement(By.cssSelector(".search2__button")).click();
    }


}
