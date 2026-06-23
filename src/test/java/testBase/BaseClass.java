package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RecipientDashboardPage;
import pageObjects.RegisterPage;
import utilities.RandomDataGeneratorUtil;

public class BaseClass {
    public WebDriver driver;
    public Properties properties;
    public Logger logger;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) throws IOException {

        FileReader file = new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());

        switch(browser.toLowerCase()){
            case "chrome" :
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");


                HashMap<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);


                options.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(options); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" : driver = new FirefoxDriver();  break;
            default: throw new IllegalArgumentException("Invalid Browser Name: " + browser);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(properties.getProperty("uri"));

    }

//    @BeforeMethod
//    public void driverSetup(){
//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        driver.get(properties.getProperty("uri"));
//    }

    @AfterMethod
    public void driverTearDown(){
        driver.quit();
    }

//    @AfterClass
//    public void teardown(){
//        driver.quit();
//    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public void LoginUserHelper(String email,String password){
        logger.info("Switching to login page...");
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Providing user credentials...");
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();
        logger.info("Trying to logging Into dashboard...");
        loginPage.waitForUrlToContain("/dashboard");
    }

    public String[] registerUserHelper(String[] userData,String as){
        HomePage homePage = new HomePage(driver);
        logger.info("Navigating to Registration page from Home page...");
        homePage.clickRegister();

        RegisterPage registerPage = new RegisterPage(driver);

        switch (as.toLowerCase()){
            case "donor" : logger.info("Donor checkbox remains selected..."); break;
            case "recipient" :
                            logger.info("Deselecting Donor and Selecting Recipient checkbox...");
                            registerPage.clickDonor();
                            registerPage.clickRecipient();
                            break;
            case "both" : logger.info("Donor checkbox remains selected, Selecting Recipient checkbox...");
                            registerPage.clickRecipient();
                            break;
            default: throw new IllegalArgumentException("Invalid User type: "+as);
        }


        if(userData==null){
            logger.info("Generating random user data and populating the registration form...");
            userData = RandomDataGeneratorUtil.randomUserDataGenerator();
        }
        RandomDataGeneratorUtil.submitUserData(registerPage,userData);

        logger.info("Clicking on the Agree Terms...");
        registerPage.clickAgreeTerms();

        logger.info("Submitting the registration form...");
        registerPage.clickRegister();

        registerPage.getAlertMessage();
        registerPage.waitForUrlToContain("/login");

        return userData;
    }

    public String[] registerUserHelper(String as){
        return registerUserHelper(null,as);
    }

    public String[][] generateNewBloodRequest(String[] donorData,String[] recipientData){
        try {
            if(donorData==null) donorData = RandomDataGeneratorUtil.randomUserDataGenerator();
//            donorData[7] = "O+";
            logger.info("Donor Profile Data: " + donorData[0] + ":" + donorData[1] + ":" + donorData[2]);
            if(recipientData==null)  recipientData = RandomDataGeneratorUtil.randomUserDataGenerator();
            logger.info("Recipient Profile Data: " + recipientData[0] + ":" + recipientData[1] + ":" + recipientData[2]);
            recipientData[7] = donorData[7];    //blood type
            recipientData[9] = donorData[9];    // city


            registerUserHelper(donorData, "donor");
            logger.info("Registered With Donor Profile...");
            new LoginPage(driver).clickRegister();

            registerUserHelper(recipientData, "recipient");
            logger.info("Registered With Recipient Profile...");
            LoginUserHelper(recipientData[1], recipientData[2]);
            logger.info("logged into Recipient profile...");
            RecipientDashboardPage recipientPage = new RecipientDashboardPage(driver);
            recipientPage.setFilterFields(recipientData[7], recipientData[9]);
            Thread.sleep(2000);
            boolean isRequestSent = recipientPage.sendRequest(donorData[0]);
            logger.info("Validating request sent successfully...");
            Assert.assertTrue(isRequestSent, "Failed to send request to donor");

            return new String[][]{donorData,recipientData};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String[][] generateNewBloodRequest(){
        return generateNewBloodRequest(null,null);
    }


}
