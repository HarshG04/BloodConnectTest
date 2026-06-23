package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DonorDashboardPage extends DashboardPage {

    public DonorDashboardPage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(xpath = "//button[normalize-space()='Edit Profile']")
    WebElement btnEditProfile;

    @FindBy(xpath="//div[@class='profile-card']//div[@class='profile-header']//h4")
    WebElement lblName;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-header']//p")
    WebElement lblEmail;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-info']//span[@class='label' and text()='Blood Type']/following-sibling::span")
    WebElement lblBloodGrp;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-info']//span[@class='label' and text()='Phone']/following-sibling::span")
    WebElement lblPhoneNo;

    @FindBy(xpath = "//div[@class='profile-card']//div[@class='profile-info']//span[@class='label' and text()='City']/following-sibling::span")
    WebElement lblLocation;

    @FindBy(xpath = "//h5[text()='Donation History']/../..")
    WebElement secDonationHistory;

    @FindBy(xpath = "//li[@class='nav-item dropdown']")
    WebElement drpNotification;

    @FindBy(xpath = "//div[@class='stats-card mt-4']") WebElement secDonationStatistics;
    @FindBy(xpath = "//div[@class='stats-card mt-4']//div[@class='stat-number']") WebElement totalDonations;




    //actions
    public void clickEditProfile(){
        waitForElementToBeClickable(btnEditProfile).click();
    }
    public void clickNotificationDropDown(){waitForElementToBeClickable(drpNotification).click();}
    public String getDonorName(){return lblName.getText();}
    public String getDonorBloodGrp(){return lblBloodGrp.getText();}
    public String getDonorEmail(){return lblEmail.getText();}
    public String getDonorPhone(){return lblPhoneNo.getText();}
    public String getDonorLocation(){return lblLocation.getText();}
    public boolean isDonationHistoryDisplayed(){
        return secDonationHistory.isDisplayed();
    }
    public boolean isDonationHistoryDisplayed(String recipientName){
        WebElement recipientApproval = driver.findElement(By.xpath("//div[@class='donation-timeline']/div[@class='timeline-item']/div[@class='timeline-content']/p[contains(text(),'"+recipientName+"')]"));
        waitForElementToVisible(recipientApproval);
        return recipientApproval.isDisplayed();
    }

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

    public boolean rejectRequest(String recipientName) {
        try {

            WebElement rejectBtn = driver.findElement(By.xpath(
                    "//div[@class='requests-list']//h6[text()='" + recipientName + "']" +
                            "/following::div[@class='request-actions']/button[2]"
            ));
            rejectBtn.click();

            WebElement successMsg =
                    driver.findElement(By.xpath(
                            "//div[@class='requests-list']//h6[text()='"+recipientName+"']/following::p[@class='small text-warning mt-2']"
                    ));

            String messageText = successMsg.getText().trim();


            return messageText.contains("You marked this as not interested");

        } catch (Exception e) {
            return false;
        }

    }

    public boolean viewRequest(String recipientName){
        WebElement recAcceptRequest = driver.findElement(By.xpath("//div[@class='requests-list']//h6[text()='"+recipientName+"']"));
        return recAcceptRequest.isDisplayed();
    }

    public boolean validateNotification(String recipientName){
        clickNotificationDropDown();
        By locator = By.xpath(
                "//ul[@aria-labelledby='notificationsDropdown']//a[contains(@class,'dropdown-item') and contains(.,'" + recipientName + "')]"
        );
        WebElement notification = driver.findElement(locator);
        return notification.isDisplayed();
    }

    public boolean isDonationStatisticsDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOf(secDonationStatistics));
            return secDonationStatistics.isDisplayed();
        }
        catch(TimeoutException e){
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public int getTotalDonationsCount() {
        waitForElementToVisible(totalDonations);
        String countText = totalDonations.getText().trim();
        return Integer.parseInt(countText);
    }
}
