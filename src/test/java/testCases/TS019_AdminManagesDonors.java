package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.ManageDonorsPage;
import testBase.BaseClass;

public class TS019_AdminManagesDonors extends BaseClass {

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = {"admin","smoke"})
    public void TC051_verifyAllDonorListVisibilityOnAdminPage(String email,String password){
        logger.info("Starting Test: TC051_verifyDonorListVisibilityOnAdminPage");

        try {
            logger.info("Logging in with administrator credentials: " + email);
            loginUserHelper(email,password);

            logger.info("Getting the count of all donors from admin dashboard...");
            int totalDonorsCount = new AdminPage(driver).getTotalDonorsCount();

            logger.info("Getting the count of visible donors from donors managment page");
            int visibleDonorsCount = new ManageDonorsPage(driver).getVisibleDonorListCount();

            logger.info("Verifying both count numbers...");
            Assert.assertEquals(visibleDonorsCount,totalDonorsCount,"Count of Total Donors and Visible Donors are not equal...");
            logger.info("Test TC051 passed successfully");

        } catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the assertion failure directly to TestNG for reporting
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC051 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = {"admin","sanity"})
    public void TC052_VerifyAdminCanDeleteDonors(String email,String password){
        logger.info("Starting Test: TC052_VerifyAdminCanDeleteDonors");

        try {
            logger.info("Logging in with administrator credentials: " + email);
            loginUserHelper(email,password);

            ManageDonorsPage manageDonorsPage = new ManageDonorsPage(driver);
            logger.info("Executing deletion routine on the first donor row record");
            manageDonorsPage.deleteFirstDonor();

            logger.info("Capturing confirmation window alert text string");
            String alertMessage = manageDonorsPage.getAlertMessage();
            logger.info("Retrieved confirmation banner message: " + alertMessage);

            logger.info("Asserting deletion system response message matches verification benchmark");
            Assert.assertEquals("Donor deleted successfully",alertMessage);
            logger.info("Test TC052 passed successfully");

        } catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the assertion failure directly to TestNG for reporting
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC052 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}