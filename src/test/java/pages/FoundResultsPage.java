package pages;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;


public class FoundResultsPage extends Page {

    public FoundResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".serp-list_right_yes")
    public WebElement search;


    public void resultsFound(){
        Assert.assertTrue(driver.findElement(By.cssSelector(".serp-list_right_yes")).isDisplayed());
    }

    public void visitEverySite() throws InterruptedException {
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> sitesList = driver.findElements(By.cssSelector(".Path-Item"));
        for (WebElement e: sitesList
        ) {
            e.click();
            String link = e.getText();
            if (link.contains("Domclick")) {
                Thread.sleep(5000);
                break;
            }
            else {
                Thread.sleep(5000);
                String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
                driver.switchTo().window(newWindow);
                driver.close();
                driver.switchTo().window(mainWindow);
            }
        }
    }
    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}
