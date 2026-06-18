package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DonorRecepientPage extends DashboardPage{

    public DonorRecepientPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//button[contains(text(),' Switch View ')]")
    WebElement btnSwitchView;
    @FindBy(xpath = "//h1[@class='dashboard-title mb-0']") WebElement heading;

    public void clickSwitchView(String user){
        btnSwitchView.click();
        waitForUrlToContain(user.toLowerCase()+"/dashboard");
    }

}
