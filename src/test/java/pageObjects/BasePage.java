package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    public BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
    }

    public boolean waitForUrlToContain(String partialUrl) {
        try{
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        }catch(TimeoutException e){
            return false;
        }
    }


    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void waitForElementToVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void refreshPage(){
        driver.navigate().refresh();
    }
}
