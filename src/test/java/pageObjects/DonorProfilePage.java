package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DonorProfilePage extends BasePage{
    public DonorProfilePage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(xpath = "//input[@name='name']")
    WebElement txtFullName;

    @FindBy(xpath="//select[@name='bloodGroup']")
    WebElement drpBloodGroup;

    @FindBy(xpath="//input[@name='age']")
    WebElement txtAge;

    @FindBy(xpath="//input[@name='phone']")
    WebElement phoneno;

    @FindBy(xpath="//textarea[@name='address']")
    WebElement txtAddress;

    @FindBy(xpath="//button[normalize-space()='Save Changes']")
    WebElement btnSaveChanges;

    //actions
    public void enterFullName(String name){
        txtFullName.clear();
        txtFullName.sendKeys(name);
    }

    public boolean isFullNameEditable() {
        return txtFullName.isEnabled();
    }

    public void clickSaveChanges(){
        btnSaveChanges.click();
    }

}
