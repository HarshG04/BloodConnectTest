package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.BloodCampPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS021_AdminManageBloodCamps extends BaseClass {

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC057_VerifyAdminCanAddsBloodCamp(String email,String password) throws InterruptedException {
        logger.info("Starting Test: TC057_VerifyAdminCanAddsBloodCamp");
        try {
            loginUserHelper(email, password);

            logger.info("clicking Blood Camps Button...");
            new AdminPage(driver).clickBloodCampsMenu();

            BloodCampPage campPage = new BloodCampPage(driver);
            int initialCampCount = campPage.getCampsCount();
            logger.info("Initial Camp Count: "+initialCampCount);
            logger.info("Clicking Add Camp Button...");
            campPage.clickAddCampButton();
            logger.info("Generating random blood camp data");
            RandomDataGeneratorUtil.randomBloodCampGenerator(campPage);
            Thread.sleep(2000);
            int updatedCampCount = campPage.getCampsCount();
            logger.info("Updated Camp Count: "+updatedCampCount);
            Assert.assertEquals(updatedCampCount,initialCampCount+1,"Admin is not able to add new camps");
            logger.info("Test TC057 passed successfully");
        }
        catch(AssertionError ae) {
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