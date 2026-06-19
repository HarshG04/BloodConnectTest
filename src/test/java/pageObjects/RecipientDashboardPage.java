package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void clickEdit() {
        lnkEdit.click();
    }

    public String getRecipientName() {
        return recipientName.getText();
    }

    public String getRecipientBloodType() {
        return bloodType.getText();
    }

}