package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class BloodInventoryPage extends AdminPage{
    public BloodInventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='stat-card']") List<WebElement> statsCards;
    @FindBy(xpath = "//button[contains(text(),'Add Stock')]") WebElement btnAddStock;
    @FindBy(id = "bloodGroup") WebElement selectBloodGroup;
    @FindBy(id = "quantity") WebElement txtQuantity;
    @FindBy(id = "collectionDate") WebElement txtCollectionDate;
    @FindBy(id = "source") WebElement txtSource;
    @FindBy(xpath = "//div[@class='modal-footer']/button[contains(text(),'Add Stock')]") WebElement btnAddStockSubmit;
    @FindBy(xpath = "//div[@class='chart-item mb-3']") List<WebElement> bloodGroupDistributionList;
    @FindBy(xpath = "//table") WebElement stockDetailsTable;

    public boolean isInventoryDetailsVisible(){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(statsCards));
            for (WebElement statCard : statsCards) {
                if (!statCard.isDisplayed()) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddStockButton(){
        btnAddStock.click();
    }

    public void submitInventoryData(String bloodType,int quantity,String date,String source){
        new Select(selectBloodGroup).selectByValue(bloodType.toUpperCase());
        txtQuantity.clear();
        txtQuantity.sendKeys(String.valueOf(quantity));
        txtCollectionDate.sendKeys(date);
        txtSource.sendKeys(source);
        btnAddStockSubmit.click();
    }

    public int getTotalUnits(){
        wait.until(ExpectedConditions.visibilityOfAllElements(statsCards));
        return Integer.parseInt(statsCards.get(0).findElement(By.xpath(".//div[@class='stat-number']")).getText());
    }

    public int getBloodDistributionCount(){
        return wait.until(ExpectedConditions.visibilityOfAllElements(bloodGroupDistributionList)).size();
    }

    public void isAllDistributionVisible(){
        if(getBloodDistributionCount()!=8) throw new AssertionError("Blood Type Distribution don't contain all 8 blood types. {Current number of Types: "+getBloodDistributionCount()+" }");
        HashMap<String, Integer> bloodGroupMap = new HashMap<>();
        bloodGroupMap.put("A+", 0);
        bloodGroupMap.put("A-", 0);
        bloodGroupMap.put("B+", 0);
        bloodGroupMap.put("B-", 0);
        bloodGroupMap.put("AB+", 0);
        bloodGroupMap.put("AB-", 0);
        bloodGroupMap.put("O+", 0);
        bloodGroupMap.put("O-", 0);

        for(WebElement bloodGroup : bloodGroupDistributionList){
            WebElement bloodGroupName = bloodGroup.findElement(By.xpath(".//div[1]/span[1]"));
            WebElement bloodGroupUnits = bloodGroup.findElement(By.xpath(".//div[1]/span[2]"));

            if(!bloodGroupMap.containsKey(bloodGroupName.getText())) throw new AssertionError("Invalid Blood Group Found in List {Blood Group: "+bloodGroupName+" }");
            if(bloodGroupMap.get(bloodGroupName.getText())>=1) throw new AssertionError("Duplicate Blood Group Found in List {Blood Group: "+bloodGroupName+" }");

            bloodGroupMap.put(bloodGroupName.getText(),1);
        }

        for(String key : bloodGroupMap.keySet()){
            if(bloodGroupMap.get(key)==0) throw new AssertionError("Blood Group Not Found in List {Blood Group: "+key+" }");
        }
    }


    public boolean isAllBloodInventoryVisible(){
        wait.until(ExpectedConditions.visibilityOfAllElements(stockDetailsTable));
        return stockDetailsTable.findElements(By.xpath(".//tbody/tr")).size()>0;
    }



}
