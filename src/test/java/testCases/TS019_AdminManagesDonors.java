package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TS019_AdminManagesDonors extends BaseClass {

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
    public void TC051_verifyDonorListVisibilityOnAdminPage(String email,String password){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        loginPage.waitForUrlToContain("admin/dashboard");
        AdminPage adminPage = new AdminPage(driver);
        adminPage.waitForLoadData();
        Assert.assertTrue(adminPage.isDonorListVisible());
    }
}
