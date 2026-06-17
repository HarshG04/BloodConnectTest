package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BloodAvailabilityPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS003_BloodAvailability extends BaseClass {

    @Test
    public void TC010_checkAllBloodInventoryVisibility(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC010_checkAllBloodInventoryVisibility");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Blood Availability validation screen...");
            homePage.clickBloodAvailability();

            BloodAvailabilityPage bloodAvailabilityPage = new BloodAvailabilityPage(driver);

            logger.info("Fetching actual count of inventory cards from UI layout...");
            int bloodGroupSize = bloodAvailabilityPage.getBloodGroupCount();
            logger.info("Total blood type categories found on UI: " + bloodGroupSize);

            logger.info("Verifying all 8 mandatory blood group types are registered in the view...");
            Assert.assertEquals(bloodGroupSize, 8, "Blood Inventory dashboard failed to load all 8 primary types!");

            logger.info("Auditing data visibility tags inside individual item panels...");
            boolean isVisible = bloodAvailabilityPage.checkBloodGroupInventory();

            Assert.assertTrue(isVisible, "Validation Error: Certain blood inventory labels or unit tags are hidden!");
            logger.info("SUCCESS: checkAllBloodInventoryVisibility verified successfully.");
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