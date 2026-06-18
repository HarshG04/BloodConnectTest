package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DonorDashboardPage extends BasePage {

    public DonorDashboardPage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(xpath = "//button[normalize-space()='Edit Profile']")
    WebElement btnEditProfile;

    @FindBy(xpath="//div[@class='profile-card']//div[@class='profile-header']//h4']")
    WebElement lblName;

    //actions
    public void clickEditProfile(){btnEditProfile.click();}
    public String getDonorName(){return lblName.getText();}
}
