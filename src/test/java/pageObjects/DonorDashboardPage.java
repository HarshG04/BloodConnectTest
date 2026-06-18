package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DonorDashboardPage extends DashboardPage{

    public DonorDashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='stats-card mt-4']")
    WebElement donationStatistics;


    public boolean isDonationStatisticsVisible(){
        return donationStatistics.isDisplayed();
    }

}
