package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public Properties properties;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) throws IOException {
        FileReader file = new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        switch(browser.toLowerCase()){
            case "chrome" : driver = new ChromeDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" : driver = new FirefoxDriver();  break;
            default: System.out.println("Invalid Browser Name"); return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(properties.getProperty("uri"));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}
