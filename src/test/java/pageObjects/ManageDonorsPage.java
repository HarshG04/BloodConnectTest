package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ManageDonorsPage extends AdminPage{
    public ManageDonorsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//table/tbody/tr") List<WebElement> donorList;


    public int getVisibleDonorListCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(donorList));
            int count = 0;
            for (WebElement donorCard : donorList) {
                if (donorCard.isDisplayed()) count++;
            }
            return count;
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteFirstDonor(){
        WebElement firstDonor =  wait.until(ExpectedConditions.visibilityOfAllElements(donorList)).get(0);
        clickDeleteButton(firstDonor.findElement(By.xpath("//td[6]/button")));
    }
}
