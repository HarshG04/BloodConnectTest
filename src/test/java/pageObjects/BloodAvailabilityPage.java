package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BloodAvailabilityPage extends BasePage{

    public BloodAvailabilityPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='card-body py-4']")
    List<WebElement> bloodGroupList;


    public int getBloodGroupCount(){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(bloodGroupList));
            return bloodGroupList.size();
        }
        catch(Exception e){
            return 0;
        }
    }
    public boolean checkBloodGroupInventory(){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(bloodGroupList));
            for(WebElement bloodGroup : bloodGroupList){
                WebElement bloodGroupName = bloodGroup.findElement(By.xpath("//div[@class='blood-group-badge mx-auto mb-3']"));
                WebElement bloodGroupUnits = bloodGroup.findElement(By.className("mb-1"));

                if(!bloodGroupName.isDisplayed() || !bloodGroupUnits.isDisplayed()) return false;
            }
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
    public int getAvailableUnitsCount(String bloodGroup){
        WebElement bloodGroupAvaiability = driver.findElement(By.xpath("//div[@class='blood-group-badge mx-auto mb-3' and contains(text(),'"+bloodGroup.toUpperCase()+"')]/following-sibling::h2"));
        wait.until(ExpectedConditions.visibilityOf(bloodGroupAvaiability));
        return Integer.parseInt(bloodGroupAvaiability.getText());
    }
}
