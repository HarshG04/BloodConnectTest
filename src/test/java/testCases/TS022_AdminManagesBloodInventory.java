//package testCases;
//
//import DataProviders.LoginDataProvider;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pageObjects.AdminPage;
//import pageObjects.BloodInventoryPage;
//import testBase.BaseClass;
//import utilities.RandomDataGeneratorUtil;
//
//public class TS022_AdminManagesBloodInventory extends BaseClass {
//
//    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
//    public void TC058_VerifyBloodInventoryVisibilityToAdmin(String email,String password) {
//        LoginUserHelper(email, password);
//        AdminPage adminPage = new AdminPage(driver);
//        adminPage.clickUserDropDown();
//        adminPage.clickBloodInventory();
//
//        boolean isInventoryVisible = new BloodInventoryPage(driver).isInventoryVisible();
//        Assert.assertTrue(isInventoryVisible);
//
//    }
//
//    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
//    public void TC059_VerifyAdminAddInventorySock(String email,String password) {
//        LoginUserHelper(email, password);
//
//        AdminPage adminPage = new AdminPage(driver);
//        adminPage.clickUserDropDown();
//        adminPage.clickBloodInventory();
//
//        BloodInventoryPage inventoryPage = new BloodInventoryPage(driver);
//        inventoryPage.clickAddStockButton();
//        String[] inventoryData = RandomDataGeneratorUtil.generateInventoryData();
//
//        inventoryPage.submitInventoryData(inventoryData);
//
//    }
//
//    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
//    public void TC060_VerifyAllBloodInventoryVisibilityToAdmin(String email,String password) {
//        LoginUserHelper(email, password);
//        AdminPage adminPage = new AdminPage(driver);
//        adminPage.clickUserDropDown();
//        adminPage.clickBloodInventory();
//
//        boolean isAllInventoryVisible = new BloodInventoryPage(driver).isAllBloodInventoryVisible();
//        Assert.assertTrue(isAllInventoryVisible);
//
//    }
//
//    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
//    public void TC061_VerifyBloodGroupDistributionSectionVisibility(String email,String password) {
//        LoginUserHelper(email, password);
//        AdminPage adminPage = new AdminPage(driver);
//        adminPage.clickUserDropDown();
//        adminPage.clickBloodInventory();
//
//        boolean isBloodGroupDistributionSectionVisible = new BloodInventoryPage(driver).isBloodGroupDistributionSectionVisible();
//        Assert.assertTrue(isBloodGroupDistributionSectionVisible);
//
//    }
//
//
//
//
//
//
//}
