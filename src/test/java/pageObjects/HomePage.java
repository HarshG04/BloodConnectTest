package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(xpath = "//body/app-root/app-home/section[5]/div[1]")  WebElement sectionBloodDonationCamps;
    @FindBy(xpath = "//p[text()='No upcoming camps scheduled. Check back soon!']") WebElement noBloodCampMessage;

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

    public int getBloodCampsCount(){
        try
        {
            new Actions(driver).scrollToElement(sectionBloodDonationCamps).perform();
            wait.until(ExpectedConditions.visibilityOfAllElements(bloodCampList));
            return bloodCampList.size();
        } catch (Exception e) {
            return 0;
        }

    }
    public boolean checkNoBloodCampMessageVisibility(){
        return noBloodCampMessage.isDisplayed();
    }


}
