package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RecipientEditProfilePage extends BasePage{
    public RecipientEditProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@type='submit']") WebElement saveChanges;
    @FindBy(xpath = "//input[@name='name']") WebElement name;
    @FindBy(xpath = "//select[@name='bloodGroup']") WebElement bloodType;

    public void clickSaveChanges(){saveChanges.click();}
    public void setRecipientName(String recipientName){name.sendKeys(recipientName);}
    public void setBloodType(String recipientBloodType){
        Select select=new Select(bloodType);
        select.selectByVisibleText(recipientBloodType);
    }

}
