package Auth;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AuthTest {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://bloodconnectc.netlify.app/");
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @Test(groups = "TS_001")
    public void should_RegisterSuccessfully_When_ValidDonorDetailsProvided(){
        // Arrange
        String name = "Test Donor";
        String email = "testdonor@test.com";
        String password = "temppassword";
        String phoneno = "1234567890";
        int age = 22;
        double weight = 65.5;
        String bloodType = "O+";
        String gender = "MALE";
        String streetAddress = "sample street 1";
        String city = "sample city";
        String state = "sample state";
        int zip = 234566;
        String medicalConditions = "sample medical conditions";


        WebElement registerButton = driver.findElement(By.partialLinkText("Register as Donor"));
        registerButton.click();

        WebElement bloodDonorOption = driver.findElement(By.id("roleDonor"));
       if(!bloodDonorOption.isSelected()){
            bloodDonorOption.click();
       }

       driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys(name);
       driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
       driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
       driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(phoneno);
       driver.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys(String.valueOf(age));
       driver.findElement(By.xpath("//input[@placeholder='Weight']")).sendKeys(String.valueOf(weight));
       Select bloodTypeSelect = new Select(driver.findElement(By.xpath("//select[@formcontrolname='bloodGroup']")));
       bloodTypeSelect.selectByValue(bloodType);
       Select genderSelect = new Select(driver.findElement(By.xpath("//select[@formcontrolname='gender']")));
        genderSelect.selectByValue(gender);
        driver.findElement(By.xpath("//input[@formcontrolname='address']")).sendKeys(streetAddress);
        driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@formcontrolname='state']")).sendKeys(state);
        driver.findElement(By.xpath("//input[@formcontrolname='zipCode']")).sendKeys(String.valueOf(zip));
        driver.findElement(By.xpath("//textarea[@formcontrolname='medicalConditions']")).sendKeys(medicalConditions);

        WebElement agreeTerms =  driver.findElement(By.xpath("//input[@id='agreeTerms' and @type='checkbox']"));
        js.executeScript("arguments[0].click();",agreeTerms);

        driver.findElement(By.xpath("//button[@type='submit']")).submit();

        if(wait.until(ExpectedConditions.urlToBe("https://bloodconnectc.netlify.app/login"))) Assert.assertTrue(true);
        Assert.fail();
    }

    @Test(groups = "TS_001")
    public void should_RegisterSuccessfully_When_ValidRecepientDetailsProvided(){
        // Arrange
        String name = "Test Recepient";
        String email = "testrecepient@test.com";
        String password = "temppassword";
        String phoneno = "1234567890";
        int age = 22;
        double weight = 65.5;
        String bloodType = "O+";
        String gender = "MALE";
        String streetAddress = "sample street 1";
        String city = "sample city";
        String state = "sample state";
        int zip = 234566;
        String medicalConditions = "sample medical conditions";


        WebElement registerButton = driver.findElement(By.xpath("//a[text()=' Register ']"));
        registerButton.click();

        WebElement bloodDonorOption = driver.findElement(By.id("roleDonor"));
        if(bloodDonorOption.isSelected()){
            bloodDonorOption.click();
        }
        driver.findElement(By.id("roleRecipient")).click();

        driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(phoneno);
        driver.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys(String.valueOf(age));
        driver.findElement(By.xpath("//input[@placeholder='Weight']")).sendKeys(String.valueOf(weight));
        Select bloodTypeSelect = new Select(driver.findElement(By.xpath("//select[@formcontrolname='bloodGroup']")));
        bloodTypeSelect.selectByValue(bloodType);
        Select genderSelect = new Select(driver.findElement(By.xpath("//select[@formcontrolname='gender']")));
        genderSelect.selectByValue(gender);
        driver.findElement(By.xpath("//input[@formcontrolname='address']")).sendKeys(streetAddress);
        driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@formcontrolname='state']")).sendKeys(state);
        driver.findElement(By.xpath("//input[@formcontrolname='zipCode']")).sendKeys(String.valueOf(zip));
        driver.findElement(By.xpath("//textarea[@formcontrolname='medicalConditions']")).sendKeys(medicalConditions);


        WebElement agreeTerms =  driver.findElement(By.xpath("//input[@id='agreeTerms' and @type='checkbox']"));
        js.executeScript("arguments[0].click();",agreeTerms);

        driver.findElement(By.xpath("//button[@type='submit']")).submit();

        if(wait.until(ExpectedConditions.urlToBe("https://bloodconnectc.netlify.app/login"))) Assert.assertTrue(true);
        Assert.fail();
    }
}
