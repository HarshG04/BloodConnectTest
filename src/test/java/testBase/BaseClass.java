package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import pageObjects.HomePage;
import pageObjects.LoginPage;

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
            case "chrome" : driver = new ChromeDriver(); break;
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


}
