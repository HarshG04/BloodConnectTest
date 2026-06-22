package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdminPage extends DashboardPage{
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(text(),'Blood Requests')]") WebElement btnBloodRequests;
    @FindBy(xpath = "//button[contains(text(),'Blood Camps')]") WebElement btnBloodCamps;
    @FindBy(xpath = "//div[@class='row g-3 mb-4']") WebElement adminCards;
    @FindBy(xpath = "//div[@role='alert']") WebElement alertMessage;
    @FindBy(xpath = "//div[@class='stat-card stat-red']/div[@class='stat-number']") WebElement totalDonors;
    @FindBy(xpath = "//div[@class='stat-card stat-blue']/div[@class='stat-number']") WebElement totalRequests;
    @FindBy(xpath = "//div[@class='stat-card stat-yellow']/div[@class='stat-number']") WebElement pendingRequests;




    public void clickBloodRequests(){
        wait.until(ExpectedConditions.visibilityOf(btnBloodRequests)).click();
    }
    public void clickBloodCampsMenu() {
        btnBloodCamps.click();
    }

    public void waitForLoadData(){
        waitForElementToVisible(adminCards);
    }

    public String getAlertMessage(){
        return wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();
    }

    protected void clickDeleteButton(WebElement deleteButton){
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        driver.switchTo().alert().accept();
    }

    public int getTotalDonorsCount(){
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOf(totalDonors)).getText());
    }
    public int getTotalRequestCount(){
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOf(totalRequests)).getText());
    }
    public int getPendingRequestCount(){
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOf(pendingRequests)).getText());
    }

//    public void clickBloodInventory(){
//
//    }

}
