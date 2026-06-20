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

    @FindBy(xpath = "//button[contains(text(),'Blood Camps')]") WebElement btnBloodCamps;
    @FindBy(xpath = "//div[@class='row g-3 mb-4']") WebElement adminCards;
    @FindBy(xpath = "//table/tbody/tr") List<WebElement> donorList;
    @FindBy(xpath = "//button[contains(text(),'Delete')]") List<WebElement> btnDeletelist;


    public void clickBloodCampsMenu() {
        btnBloodCamps.click();
    }

    public void waitForLoadData(){
        waitForElementToVisible(adminCards);
    }

    public boolean isDonorListVisible(){
        return wait.until(ExpectedConditions.visibilityOfAllElements(donorList)).size()>0;
    }

    protected void clickOnAllDelete() throws InterruptedException {
        for(WebElement btnDelete : btnDeletelist) {
            Thread.sleep(2000);
            btnDelete.click();
            driver.switchTo().alert().accept();
        }

    }





}
