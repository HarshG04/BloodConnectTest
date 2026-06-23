package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.BloodAvailabilityPage;
import pageObjects.BloodInventoryPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS003_BloodAvailability extends BaseClass {

//    @Test
//    public void TC010_checkAllBloodInventoryVisibility(){
//        logger.info("=========================================================");
//        logger.info("STARTING TEST CASE: TC010_checkAllBloodInventoryVisibility");
//        logger.info("=========================================================");
//
//        try {
//            HomePage homePage = new HomePage(driver);
//            logger.info("Navigating to Blood Availability validation screen...");
//            homePage.clickBloodAvailability();
//
//            BloodAvailabilityPage bloodAvailabilityPage = new BloodAvailabilityPage(driver);
//
//            logger.info("Fetching actual count of inventory cards from UI layout...");
//            int bloodGroupSize = bloodAvailabilityPage.getBloodGroupCount();
//            logger.info("Total blood type categories found on UI: " + bloodGroupSize);
//
//            logger.info("Verifying all 8 mandatory blood group types are registered in the view...");
//            Assert.assertEquals(bloodGroupSize, 8, "Blood Inventory dashboard failed to load all 8 primary types!");
//
//            logger.info("Auditing data visibility tags inside individual item panels...");
//            boolean isVisible = bloodAvailabilityPage.checkBloodGroupInventory();
//
//            Assert.assertTrue(isVisible, "Validation Error: Certain blood inventory labels or unit tags are hidden!");
//            logger.info("SUCCESS: checkAllBloodInventoryVisibility verified successfully.");
//        }
//        catch(AssertionError ae) {
//            logger.error("ASSERTION FAILED: " + ae.getMessage());
//            throw ae;
//        }
//        catch (Exception e) {
//            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
//            logger.error("Exception message context: " + e.getMessage());
//            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
//        }
//    }

    @Test(dataProvider = "adminLoginData", dataProviderClass = LoginDataProvider.class)
    public void TC011_VerifyUpdationInventoryReflectedOnHomePage(String email, String password) {
        logger.info("Starting TC011: Verifying that admin stock updates reflect in public Blood Availability counts.");

        try {
            String bloodGroup = "O+";

            // 1. Fetch initial public available stock
            logger.info("Navigating to Blood Availability page to read baseline metrics...");
            new HomePage(driver).clickBloodAvailability();

            BloodAvailabilityPage bloodAvailabilityPage = new BloodAvailabilityPage(driver);
            int initialAvailableUnit = bloodAvailabilityPage.getAvailableUnitsCount(bloodGroup);
            logger.info("Baseline available units for group " + bloodGroup + ": " + initialAvailableUnit);

            // 2. Admin logs in to adjust backend system stock
            logger.info("Logging into the application as Admin user: " + email);
            LoginUserHelper(email, password);

            AdminPage adminPage = new AdminPage(driver);
            logger.info("Navigating deep into Admin Inventory dashboard panels...");
            adminPage.clickAdminDropDown();
            adminPage.clickInventory();

            // 3. Form payload generation and stock injection execution
            logger.info("Generating localized randomized inventory dataset...");
            String[] inventoryFormData = RandomDataGeneratorUtil.generateRandomInventoryData();
            inventoryFormData[0] = bloodGroup; // Enforce targeted blood type injection override
            int unitsToAdd = Integer.parseInt(inventoryFormData[1]);

            logger.info("Injecting new stock: [Blood Group: " + bloodGroup + " | Units: " + unitsToAdd + "]");
            BloodInventoryPage inventoryPage = new BloodInventoryPage(driver);
            inventoryPage.clickAddStockButton();
            inventoryPage.submitInventoryData(inventoryFormData[0], unitsToAdd, inventoryFormData[2], inventoryFormData[3]);

            // Pause momentarily for operational system writes to sync
            Thread.sleep(1000);

            // 4. Clean administrative session termination
            logger.info("Logging out from administrative secure scope...");
            inventoryPage.clickUserDropDown();
            inventoryPage.clickLogout();

            // 5. Return to context home and verify counts
            logger.info("Returning to unauthenticated landing page to check update propagation...");
            new HomePage(driver).clickBloodAvailability();

            int updatedAvailableUnit = new BloodAvailabilityPage(driver).getAvailableUnitsCount(bloodGroup);
            int expectedAvailableUnits = initialAvailableUnit + unitsToAdd;

            logger.info("Audit Evaluation - Expected: " + expectedAvailableUnits + " | Actual UI Value: " + updatedAvailableUnit);

            Assert.assertEquals(updatedAvailableUnit, expectedAvailableUnits,
                    "FAILED: The public available stock count did not increase correctly by the admin added amount!");

            logger.info("TC011 passed successfully");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC011 execution execution stream!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}