package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void selectBloodGrp(String bd){
        Select sel = new Select(drpBloodGroup);
        sel.selectByValue(bd);
    }

    public boolean isFullNameEditable() {
        return txtFullName.isEnabled();
    }

    public boolean isBloodGroupEditable(){
        return drpBloodGroup.isEnabled();
    }

    public void clickSaveChanges() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();arguments[0].click();",btnSaveChanges);
//        waitForElementToBeClickable(btnSaveChanges).click();
    }

}
