package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

public class TS009_DonationStatistics extends BaseClass {

    @Test(dataProvider = "donorLogin",dataProviderClass = LoginDataProvider.class)
    public void TC028_VerifyDonorDonationStatisticsSectionVisibility(String email,String password){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        loginPage.waitForUrlToContain("donor/dashboard");

        DonorDashboardPage donorDashboardPage = new DonorDashboardPage(driver);
//        Assert.assertTrue(donorDashboardPage.isDonationStatisticsVisible());
    }

    @Test
    public void TC029_VerifyDonationStatisticsUpdation(){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(properties.getProperty("recepientEmail"));
        loginPage.setEmail(properties.getProperty("recepientPassword"));
        loginPage.clickLogin();

        RecipientDashboardPage recipientPage = new RecipientDashboardPage(driver);


    }
}
