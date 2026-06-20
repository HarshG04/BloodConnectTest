package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    public void clickEdit() {
        lnkEdit.click();
    }

    public void selBloodType() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        js.executeScript("arguments[0].scrollIntoView();",drpBloodType);
        Select sel = new Select(drpBloodType);
        sel.selectByValue(getRecipientBloodType());
    }

    public void enterCity(){
        txtCity.sendKeys(getRecipientLocation());
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
            Thread.sleep(2000);
            WebElement donorSendReqBtn = driver.findElement(
                    By.xpath("//div[@class='donors-grid']//h6[text()='" + donorName + "']/following::button")
            );

            waitForElementToBeClickable(donorSendReqBtn).click();
            Thread.sleep(6000);

            WebElement updatedBtn = driver.findElement(
                    By.xpath("//div[@class='donors-grid']//h6[text()='" + donorName + "']/following::button")
            );

            String btnText = updatedBtn.getText();

            if (btnText.equalsIgnoreCase("Request Sent") || btnText.equalsIgnoreCase("Pending")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}