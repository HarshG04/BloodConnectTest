package pageObjects;

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

    @FindBy(xpath = "//button[contains(text(),'Blood Camps')]") WebElement btnBloodCamps;
    @FindBy(xpath = "//button[contains(text(),'Add Camp')]") WebElement btnAddCamp;
    @FindBy(xpath= "//input[@placeholder='e.g., City Blood Drive']") WebElement txtCampName;
    @FindBy(xpath= "//input[@placeholder='e.g., City Hospital']") WebElement txtLocation;
    @FindBy(xpath= "//input[@type='date']") WebElement txtDate;
    @FindBy(xpath= "//div[@class='tab-content']//div[4]//input[1]") WebElement timeStartTime;
    @FindBy(xpath= "//div[@class='tab-content']//div[5]//input[1]") WebElement timeEndTime;
    @FindBy(xpath= "//input[@type='number']") WebElement txtCapacity;
    @FindBy(xpath= "//input[@placeholder='Optional details']") WebElement txtDescription;
    @FindBy(xpath= "//button[normalize-space()='Save Camp']") WebElement btnSaveCamp;
    @FindBy(xpath = "//div[@class='row g-3 mb-4']") WebElement adminCards;
    @FindBy(xpath = "//table/tbody/tr") List<WebElement> donorList;

    public void clickBloodCampsMenu() {
        btnBloodCamps.click();
    }

    public void clickAddCampButton() {
        btnAddCamp.click();
    }
    public void submitBloodCampData(String campName, String location, String campDate, String startTime, String endTime, int capacity, String description) {
        txtCampName.sendKeys(campName);
        txtLocation.sendKeys(location);
        txtDate.sendKeys(campDate);
        timeStartTime.clear();
        timeStartTime.sendKeys(startTime);
        timeEndTime.clear();
        timeEndTime.sendKeys(endTime);
        txtCapacity.clear();
        txtCapacity.sendKeys(String.valueOf(capacity));
        txtDescription.sendKeys(description);
    }

    public void clickSaveCamp() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSaveCamp)).click();
    }

    public void waitForLoadData(){
        waitForElementToVisible(adminCards);
    }

    public boolean isDonorListVisible(){
        return wait.until(ExpectedConditions.visibilityOfAllElements(donorList)).size()>0;
    }





}
