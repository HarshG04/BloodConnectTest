package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.BloodInventoryPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS022_AdminManagesBloodInventory extends BaseClass {

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC058_VerifyBloodInventoryVisibilityToAdmin(String email,String password) {
        logger.info("Executing TC058: Verify Blood Inventory Visibility To Admin");
        try {
            gotoInventoryHelper(email, password);

            logger.info("Checking visibility of inventory details...");
            boolean isInventoryVisible = new BloodInventoryPage(driver).isInventoryDetailsVisible();
            logger.info("Inventory details visibility status: " + isInventoryVisible);

            Assert.assertTrue(isInventoryVisible,"Not all details are present");
            logger.info("TC058 completed successfully.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }


    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC059_VerifyAdminAddInventorySock(String email,String password) throws InterruptedException {
        logger.info("Executing TC059: Verify Admin Add Inventory Stock");
        try {
            gotoInventoryHelper(email, password);

            BloodInventoryPage inventoryPage = new BloodInventoryPage(driver);
            int initialUnitsCount = inventoryPage.getTotalUnits();
            logger.info("Initial Total Units: " + initialUnitsCount);

            logger.info("Clicking on Add Stock button...");
            inventoryPage.clickAddStockButton();

            String[] inventoryData = RandomDataGeneratorUtil.generateRandomInventoryData();
            logger.info("Submitting inventory data -> Group: " + inventoryData[0] + ", Units: " + inventoryData[1]);
            inventoryPage.submitInventoryData(inventoryData[0],Integer.parseInt(inventoryData[1]),inventoryData[2],inventoryData[3]);

            logger.info("Waiting for inventory update...");
            Thread.sleep(2000);

            int updatedUnitsCount = inventoryPage.getTotalUnits();
            logger.info("Updated Total Units: " + updatedUnitsCount);

            int expectedUnitsCount = initialUnitsCount + Integer.parseInt(inventoryData[1]);
            logger.info("Expected Total Units: " + expectedUnitsCount);

            Assert.assertEquals(updatedUnitsCount,expectedUnitsCount);
            logger.info("TC059 completed successfully.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC060_VerifyAllBloodInventoryStockVisibilityToAdmin(String email,String password) {
        logger.info("Executing TC060: Verify All Blood Inventory Stock Visibility To Admin");
        try {
            gotoInventoryHelper(email,password);

            logger.info("Checking visibility of all blood inventory elements...");
            boolean isAllInventoryVisible = new BloodInventoryPage(driver).isAllBloodInventoryVisible();
            logger.info("All blood inventory visibility status: " + isAllInventoryVisible);

            Assert.assertTrue(isAllInventoryVisible);
            logger.info("TC060 completed successfully.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC061_VerifyBloodGroupDistributionSectionVisibility(String email,String password) {
        logger.info("Executing TC061: Verify Blood Group Distribution Section Visibility");
        try {
            gotoInventoryHelper(email, password);

            logger.info("Verifying all blood group distributions are visible...");
            new BloodInventoryPage(driver).isAllDistributionVisible();
            logger.info("TC061 completed successfully.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }



    private void gotoInventoryHelper(String email,String password){
        logger.info("Logging into admin profile with email: " + email);
        try {
            loginUserHelper(email, password);
            logger.info("Logged in to admin profile...");

            AdminPage adminPage = new AdminPage(driver);
            adminPage.clickAdminDropDown();
            logger.info("Clicked Admin Drop Down...");

            adminPage.clickInventory();
            logger.info("Navigating to Inventory Page ... ");

            adminPage.waitForUrlToContain("/inventory");
            logger.info("Successfully navigated to inventory URL.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }



}