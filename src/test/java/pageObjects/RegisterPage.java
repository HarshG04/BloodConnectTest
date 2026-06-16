package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//input[@id='roleDonor']") WebElement cbDonor;
    @FindBy(xpath = "//input[@id='roleRecipient']") WebElement cbRecipient;
    @FindBy(xpath= "//input[@placeholder='Enter your full name']") WebElement txtFullName;
    @FindBy(xpath= "//input[@placeholder='Enter your email']") WebElement txtEmail;
    @FindBy(xpath= "//input[@placeholder='Create a password']") WebElement txtPassword;
    @FindBy(xpath= "//input[@placeholder='Enter your phone number']") WebElement phoneno;
    @FindBy(xpath= "//input[@placeholder='Age']") WebElement txtAge;
    @FindBy(xpath= "//input[@placeholder='Weight']") WebElement txtWeight;
    @FindBy(xpath= "//select[@formcontrolname='bloodGroup']") WebElement drpBloodGroup;
    @FindBy(xpath= "//select[@formcontrolname='gender']") WebElement drpGender;
    @FindBy(xpath= "//input[@placeholder='Enter your street address']") WebElement txtAddress;
    @FindBy(xpath= "//input[@placeholder='City']") WebElement txtCity;
    @FindBy(xpath= "//input[@placeholder='State']") WebElement txtState;
    @FindBy(xpath= "//input[@placeholder='ZIP Code']")  WebElement txtZipCode;
    @FindBy(xpath= "//textarea[@placeholder='Please list any medical conditions, allergies, or medications you are currently taking...']") WebElement txtMedicalInfo;
    @FindBy(xpath= "//input[@id='agreeTerms']") WebElement cbAgreeTerms;
    @FindBy(xpath = "//button[@type='submit']") WebElement btnRegister;
    @FindBy(xpath = "//div[text()=' Please enter a valid email ']") WebElement emailErrorMsg;
    @FindBy(xpath = "//div[@role='alert']") WebElement alertMsg;


    //actions
    public void clickDonor(){
        cbDonor.click();
    }
    public void clickRecipient(){
        cbRecipient.click();
    }

    public void setPersonalDetails(String name, String email, String password, String phone,int age,double weight,String bloodGroup,String gender) {
        txtFullName.sendKeys(name);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        phoneno.sendKeys(String.valueOf(phone)); // Converted int to String
        txtAge.sendKeys(String.valueOf(age));       // Converted int to String
        txtWeight.sendKeys(String.valueOf(weight)); // Converted int to String

        Select bloodGroupSelect = new Select(drpBloodGroup);
        bloodGroupSelect.selectByValue(bloodGroup);

        Select genderSelect = new Select(drpGender);
        genderSelect.selectByValue(gender.toUpperCase());
    }

    public void setAddress(String address,String city,String state,int zip){
        txtAddress.sendKeys(address);
        txtCity.sendKeys(city);
        txtState.sendKeys(state);
        txtZipCode.sendKeys(String.valueOf(zip));
    }

    public void setMedicalInfo(String medicalInfo){
        txtMedicalInfo.sendKeys(medicalInfo);
    }

    public void clickAgreeTerms(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",cbAgreeTerms);
    }
    public void clickRegister(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();arguments[0].click();",btnRegister);
    }
    public String getEmailErrorMessage(){
        String msg = "";
        if(emailErrorMsg.isDisplayed()) msg = emailErrorMsg.getText();
        return msg;
    }
    public String getAlertMessage(){
        String msg = "";
        if(alertMsg.isDisplayed()) msg = alertMsg.getText();
        return msg;
    }




}
