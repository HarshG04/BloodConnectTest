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
    @FindBy(xpath = "//div[@id='navbarNav']//a[contains(text(),'Login')]") WebElement lnkLogin;
    @FindBy(xpath = "//a[contains(text(),'Blood Availability')]") WebElement lnkBloodAvailability;
    @FindBy(xpath = "//button[contains(text(),'I Need Blood')]") WebElement btnINeedBlood;







    //actions
    public void clickRegister(){
        lnkRegister.click();
    }
    public void clickLogin(){
        lnkLogin.click();
    }
    public void clickBloodAvailability(){
        lnkBloodAvailability.click();
    }
    public void clickINeedBlood(){
        btnINeedBlood.click();
    }
}
