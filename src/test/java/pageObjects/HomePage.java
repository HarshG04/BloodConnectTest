package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    //constructor
    public HomePage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(xpath = "//div[@id='navbarNav']//a[contains(text(),'Register')]") WebElement lnkRegister;



    //actions
    public void clickRegister(){
        lnkRegister.click();
    }
}
