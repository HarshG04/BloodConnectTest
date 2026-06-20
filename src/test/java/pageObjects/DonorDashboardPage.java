package pageObjects;

import org.openqa.selenium.By;
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

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-header']//p")
    WebElement lblEmail;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-info']//span[@class='label' and text()='Blood Type']/following-sibling::span")
    WebElement lblBloodGrp;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-info']//span[@class='label' and text()='Phone']/following-sibling::span")
    WebElement lblPhoneNo;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-info']//span[@class='label' and text()='City']/following-sibling::span")
    WebElement lblLocation;

    //actions
    public void clickEditProfile(){
        waitForElementToBeClickable(btnEditProfile).click();
    }
    public String getDonorName(){return lblName.getText();}
    public String getDonorBloodGrp(){return lblBloodGrp.getText();}
    public String getDonorEmail(){return lblEmail.getText();}
    public String getDonorPhone(){return lblPhoneNo.getText();}
    public String getDonorLocation(){return lblLocation.getText();}

    public boolean acceptRequest(String recipientName) {
        try {

            WebElement acceptBtn = driver.findElement(By.xpath(
                    "//div[@class='requests-list']//h6[text()='" + recipientName + "']" +
                            "/following::div[@class='request-actions']/button[1]"
            ));
            acceptBtn.click();

            WebElement successMsg =
                    driver.findElement(By.xpath("//div[contains(@class,'alert-success')]"));
            Thread.sleep(3000);

            String messageText = successMsg.getText();


            return messageText.contains("accepted this blood request");

        } catch (Exception e) {
            return false;
        }

    }

    public boolean viewRequest(String recipientName){
        WebElement recAcceptRequest = driver.findElement(By.xpath("//div[@class='requests-list']//h6[text()='"+recipientName+"']"));
        return recAcceptRequest.isDisplayed();
    }
}
