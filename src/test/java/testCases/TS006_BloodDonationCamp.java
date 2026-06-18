package testCases;

import DataProviders.LoginDataProvider;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TS006_BloodDonationCamp extends BaseClass {

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
    public void TC017_verifyActiveBloodCampsVisibility(String email,String password){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickBloodCampsMenu();
        adminPage.clickAddCampButton();
        adminPage.submitBloodCampData();
        adminPage.clickSaveCamp();

        adminPage.clickHome();

    }

}
