package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage{
    //constructor
    public HomePage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(xpath = "//div[@id='navbarNav']//a[contains(text(),'Register')]") WebElement lnkRegister;
    @FindBy(xpath = "//div[@id='navbarNav']//a[contains(text(),'Login')]") WebElement lnkLogin;
    @FindBy(xpath = "//a[contains(text(),'Blood Availability')]") WebElement lnkBloodAvailability;
    @FindBy(xpath = "//button[contains(text(),'I Need Blood')]") WebElement btnINeedBlood;
    @FindBy(xpath = "//h2[contains(text(),'Why Donate Blood?')]/ancestor::div[@class='container']") WebElement sectionWhyDonateBlood;
    @FindBy(xpath = "//h2[contains(text(),'Benefits of Donating Blood')]/ancestor::section") WebElement sectionBenefitsOfDonatingBlood;
    @FindBy(xpath = "//h2[contains(text(),'Eligibility Criteria')]/ancestor::section") WebElement sectionEligibilityCriteria;
    @FindBy(xpath = "//div[@class='col-lg-4 col-md-6']") List<WebElement> bloodCampList;


    //actions
    public void clickRegister(){
        lnkRegister.click();
    }
    public void clickLogin(){
        lnkLogin.click();
    }
    public void clickBloodAvailability(){
        lnkBloodAvailability.click();
    }
    public void clickINeedBlood(){
        btnINeedBlood.click();
    }
    public boolean checkWhyDonateBloodVisibility(){
        wait.until(ExpectedConditions.visibilityOf(sectionWhyDonateBlood));
        js.executeScript("arguments[0].scrollIntoView()",sectionWhyDonateBlood);
        return sectionWhyDonateBlood.isDisplayed();
    }

    public boolean checkBenefitsOfDonatingBloodVisibility(){
        wait.until(ExpectedConditions.visibilityOf(sectionBenefitsOfDonatingBlood));
        js.executeScript("arguments[0].scrollIntoView()",sectionBenefitsOfDonatingBlood);
        return sectionBenefitsOfDonatingBlood.isDisplayed();
    }
    public boolean checkEligibilityCriteriaVisibility(){
        wait.until(ExpectedConditions.visibilityOf(sectionEligibilityCriteria));
        js.executeScript("arguments[0].scrollIntoView()",sectionEligibilityCriteria);
        return sectionEligibilityCriteria.isDisplayed();
    }

    public void getBloodCampsCount(){

    }
}
