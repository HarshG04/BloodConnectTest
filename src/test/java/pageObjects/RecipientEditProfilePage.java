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
    @FindBy(xpath = "//input[@name='email']") WebElement email;
    @FindBy(xpath="//input[@name='phone']") WebElement phoneNo;
    @FindBy(xpath = "//textarea[@name='address']") WebElement location;

    public void clickSaveChanges(){saveChanges.click();}
    public void setRecipientName(String recipientName){name.sendKeys(recipientName);}
    public void setRecipientBloodType(String recipientBloodType){
        Select select=new Select(bloodType);
        select.selectByVisibleText(recipientBloodType);
    }
    public  void setRecipientEmail(String recipientEmail){email.sendKeys(recipientEmail);}
    public void setRecipientPhoneno(String recipientPhoneno){phoneNo.sendKeys(recipientPhoneno);}
    public void setRecipientLocation(String recipientLocation){location.sendKeys(recipientLocation);}

    public boolean isNameFieldEditable(){
        return name.isEnabled();
    }
    public boolean isEmailFieldEditable(){
        return email.isEnabled();
    }
    public boolean isPhoneFieldEditable(){
        return phoneNo.isEnabled();
    }
    public boolean isPhoneFieldDisplayed(){return phoneNo.isDisplayed();
    }
    public boolean isLocationFieldDisplayed(){
        return location.isDisplayed();
    }
    public boolean isLocationFieldEditable(){
        return location.isEnabled();
    }
}
