package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DonorDashboardPage extends DashboardPage {

    public DonorDashboardPage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(xpath = "//button[normalize-space()='Edit Profile']")
    WebElement btnEditProfile;

    @FindBy(xpath="//div[@class='profile-card']//div[@class='profile-header']//h4']")
    WebElement lblName;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-info']//span[@class='label' and text()='Blood Type']/following-sibling::span")
    WebElement lblBloodGrp;

    //actions
    public void clickEditProfile(){
        waitForElementToBeClickable(btnEditProfile).click();
    }
    public String getDonorName(){return lblName.getText();}
    public String getDonorBloodGrp(){return lblBloodGrp.getText();}
}
