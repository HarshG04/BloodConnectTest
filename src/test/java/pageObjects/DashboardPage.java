package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//a[@id='userDropdown']") WebElement lnkUserDropDown;
    @FindBy(xpath = "//a[@class='dropdown-item']") WebElement lnkLogout;
    @FindBy(xpath = "//a[contains(text(),'Home')]") WebElement lnKHome;
    @FindBy(tagName = "h1") WebElement heading;


    //actions
    public void clickUserDropDown(){
        lnkUserDropDown.click();
    }
    public void clickLogout(){
        lnkLogout.click();
    }
    public void clickHome(){
        lnKHome.click();
    }

    public String getHeading(){
        return heading.getText();
    }

}
