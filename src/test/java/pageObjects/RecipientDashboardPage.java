package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import java.util.List;

public class RecipientDashboardPage extends DashboardPage {

    public RecipientDashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(.,'Edit')]")
    WebElement lnkEdit;
    @FindBy(xpath = "//div[@class='profile-avatar']/following-sibling::h4")
    WebElement recipientName;
    @FindBy(xpath = "//span[normalize-space()='Blood Type']/following-sibling::span[1]")
    WebElement bloodType;
    @FindBy(xpath ="//div[@class='profile-avatar']/following-sibling::p[1]")
    WebElement email;
    @FindBy(xpath ="//div/span[text()='Phone']/following-sibling::span[1]")
    WebElement phoneno;
    @FindBy(xpath = "//div/span[text()='City']/following-sibling::span[1]")
    WebElement location;
    @FindBy(xpath="//label[text()='Blood Type']/following-sibling::select")
    WebElement drpBloodType;
    @FindBy(xpath="//input[@placeholder='Enter city']")
    WebElement txtCity;
    @FindBy(xpath="//li[@class='nav-item dropdown']")
    WebElement drpNotification;

    @FindBy(xpath = "//div[@class='donor-header']/child::span")
    List<WebElement> donorCards;
    @FindBy(xpath = "//div[@class='donor-header']//p ")
    List<WebElement> donorCardLocation;
    @FindBy(xpath = "//*[contains(text(),'Available Donors')]")
    WebElement availableDonors;
    @FindBy(xpath = "//div/p[normalize-space()='No available donors found']")
    WebElement noAvailableDonorsFound;
    @FindBy(xpath = "//div[@class='donor-avatar compatible-avatar']/following-sibling::div/h6")
    List<WebElement> compatibleDonorNames;
    @FindBy(xpath = "//div[contains(text(),'Blood request sent successfully')]")
    WebElement bloodRequestSentSuccessMsg;
    @FindBy(xpath = "//div/div[@class='request-item']")
    List<WebElement> requests;
    @FindBy(xpath = "//div[@role='alert']") WebElement alertMessage;


    public void clickEdit() {
        lnkEdit.click();
    }

    public void clickNotificationdrp(){
        drpNotification.click();
    }

    public void selBloodType() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        js.executeScript("arguments[0].scrollIntoView();",drpBloodType);
        Select sel = new Select(drpBloodType);
        sel.selectByValue(getRecipientBloodType());
    }

    public void enterCity(){
        txtCity.sendKeys(getRecipientLocation(), Keys.ENTER);
    }

    public String getRecipientName() {
        return recipientName.getText();
    }

    public String getRecipientBloodType() {
        return bloodType.getText();
    }

    public String getRecipientEmail() {
        return email.getText();
    }

    public String getRecipientPhoneno() {
        return phoneno.getText();
    }

    public String getRecipientLocation(){
        return location.getText();
    }

    public boolean sendRequest(String donorName) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(1000, 0);");
           // Thread.sleep(2000);
            WebElement donorSendReqBtn = driver.findElement(
                    By.xpath("//div[@class='donors-grid']//h6[text()='" + donorName + "']/following::button[1]")
            );

            js.executeScript("arguments[0].click();",donorSendReqBtn);

//            new Actions(driver).click(donorSendReqBtn).perform();
//
            String alertMessage = getAlertMessage();

            if(alertMessage.equals("Blood request sent successfully!")) return true;
            return false;

//            waitForElementToBeClickable(donorSendReqBtn).click();
//            //Thread.sleep(6000);
//
//            WebElement updatedBtn = driver.findElement(
//                    By.xpath("//div[@class='donors-grid']//h6[text()='" + donorName + "']/following::button")
//            );
//
//            String btnText = updatedBtn.getText();
//
//            if (btnText.equalsIgnoreCase("Request Sent")) {
//                return true;
//            } else {
//                return false;
//            }

        } catch (Exception e) {
            return false;
        }
    }

    public void setFilterFields(String bloodType,String city) throws InterruptedException {
        js.executeScript("window.scrollBy(0,500);");
       new Select(drpBloodType).selectByValue(bloodType);
       new Actions(driver).scrollToElement(txtCity).click(txtCity)
               .sendKeys(city)
               .sendKeys(Keys.ENTER)
               .perform();
        Thread.sleep(1500);
    }

    public void completeRequest(String donorName) throws InterruptedException {
        WebElement completeBtn = driver.findElement(By.xpath("//span[text()='MATCHED']/following-sibling::strong[text()='"+donorName+"']/following::div[@class='request-actions']/button"));
        new Actions(driver).scrollToElement(completeBtn).click(completeBtn)
                .perform();
    }
    public void cancelRequest(){
        WebElement cancelBtn = driver.findElement(By.xpath("//div[text()=' PENDING ']/following::div[@class='request-actions']/button"));
        new Actions(driver).scrollToElement(cancelBtn).click(cancelBtn)
                .perform();
    }
    public boolean validateNotification(String donorName) {
        clickNotificationdrp();
        try {
            By notificationLocator = By.xpath(
                    "//ul[@aria-labelledby='notificationsDropdown']//a[contains(.,'Donor "
                            + donorName + " has accepted')]"
            );

            WebElement notification = driver.findElement(notificationLocator);

            String text = notification.getText();

            return text.contains(donorName);

        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getDisplayedDonorsBloodType(){
        List<String> donors=new LinkedList<>();
        for(WebElement w:donorCards){
            donors.add(w.getText());
        }
        return donors;

    }

    public List<String> getDisplayedDonorsLocation(){
        List<String> donors=new LinkedList<>();
        for(WebElement w:donorCardLocation){
            donors.add(w.getText());
        }
        return donors;

    }

    public boolean isAvailableDonorsVisible(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(availableDonors));

        return availableDonors.isDisplayed();

    }

    public List<String> getCompatibleDonors(){
        List<String> donors=new LinkedList<>();
        for(WebElement w:compatibleDonorNames){
            donors.add(w.getText());
        }
        return donors;
    }

    public boolean isBloodRequestSentSuccessfully() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(bloodRequestSentSuccessMsg));

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public int getBloodRequestHistoryCount(){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(requests));
            return requests.size();
        } catch (Exception e) {
            return 0;
        }

    }


    public void clearFilterByCityField() {
        txtCity.clear();
    }

    public String getAlertMessage(){
        return waitForElementToVisible(alertMessage).getText().trim();
    }

}