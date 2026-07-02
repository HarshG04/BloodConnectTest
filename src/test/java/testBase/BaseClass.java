package testBase;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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

    @BeforeClass(alwaysRun = true)
    public void setupTest() throws IOException {
        FileReader file = new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setup(String browser){
        switch(browser.toLowerCase()){
            case "chrome" :
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
//                options.addArguments("--headless=new");
//                options.addArguments("--window-size=1920,1080");

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

    @AfterMethod(alwaysRun = true)
    public void driverTearDown(){
        driver.quit();
        driver = null;
    }


    public String captureScreen(String tname) throws IOException {

        File directory = new File(System.getProperty("user.dir") + "\\screenshots");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\"
                + tname + "_" + timeStamp + ".png";

        File targetFile = new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;
    }


    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public boolean loginUserHelper(String email, String password){
        logger.info("Switching to login page...");
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Providing user credentials...");
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();
        logger.info("Trying to logging Into dashboard...");
        return loginPage.waitForUrlToContain("/dashboard");
    }

    public String[] registerUserHelper(String[] userData,String as,boolean isNegativeTest){
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

        if (!isNegativeTest) {
            logger.info("Fetching registration alert confirmation message from the UI...");
            String alertMessage = registerPage.getAlertMessage();
            Assert.assertEquals(alertMessage, "User Registered Successfully");
            registerPage.waitForUrlToContain("/login");
        }

        return userData;
    }

    public String[] registerUserHelper(String as){
        return registerUserHelper(null,as,false);
    }
    public String[] registerUserHelper(String[] userData,String as){
        return registerUserHelper(userData,as,false);
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
            loginUserHelper(recipientData[1], recipientData[2]);
            logger.info("logged into Recipient profile...");
            RecipientDashboardPage recipientPage = new RecipientDashboardPage(driver);
            Thread.sleep(1200);
            recipientPage.setFilterFields(recipientData[7], recipientData[9]);
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
