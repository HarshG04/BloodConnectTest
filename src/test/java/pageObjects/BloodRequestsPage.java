package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BloodRequestsPage extends AdminPage {

    public BloodRequestsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='request-card']") List<WebElement> bloodRequestList;

    public int getVisibleRequestCount(){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(bloodRequestList));
            int count = 0;
            for (WebElement bloodRequestCard : bloodRequestList) {
                if (bloodRequestCard.isDisplayed()) count++;
            }
            return count;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean approveBloodRequest(){
        try {
           wait.until(ExpectedConditions.visibilityOfAllElements(bloodRequestList));
           for(WebElement requestCard : bloodRequestList) {
               WebElement btnApprove = requestCard.findElement(By.xpath(".//button[contains(text(),'Approve')]"));
               if (btnApprove.isEnabled()) {
                   btnApprove.click();
                   return true;
               }
           }
           return false;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean rejectBloodRequest(){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(bloodRequestList));
            for(WebElement requestCard : bloodRequestList) {
                WebElement btnApprove = requestCard.findElement(By.xpath(".//button[contains(text(),'Reject')]"));
                if (btnApprove.isEnabled()) {
                    btnApprove.click();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean fulfilledBloodRequest(){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(bloodRequestList));
            for(WebElement requestCard : bloodRequestList) {
                WebElement btnApprove = requestCard.findElement(By.xpath(".//button[contains(text(),'Fulfill')]"));
                if (btnApprove.isEnabled()) {
                    btnApprove.click();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }
}
