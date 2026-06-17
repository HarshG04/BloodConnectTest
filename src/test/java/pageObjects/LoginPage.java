package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//div[@id='navbarNav']//a[contains(text(),'Register')]") WebElement lnkRegister;
    @FindBy(xpath = "//input[@type='email']") WebElement txtEmail;
    @FindBy(xpath = "//input[@type='password']") WebElement txtPassword;
    @FindBy(xpath = "//button[@type='submit']") WebElement btnLogin;
    @FindBy(xpath = "//div[@role='alert']") WebElement alertMessage;


    //actions
    public void clickRegister(){
        lnkRegister.click();
    }

    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }
    public void clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
    }
    public String getAlertMessage(){
        return wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();
    }

}
