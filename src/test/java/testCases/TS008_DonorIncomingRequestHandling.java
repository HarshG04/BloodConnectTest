package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

public class TS008_DonorIncomingRequestHandling extends BaseClass {

    @Test(dataProvider = "donorRecipientData", dataProviderClass = LoginDataProvider.class)
    public void TC025_verifyDonorCanViewIncomingRequests(String recipientEmail,String recipientPwd,String recipeintName,String donorEmail,String donorPwd,String donorName){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC025_verifyDonorCanViewIncomingRequests");
        logger.info("=========================================================");
        try{
            logger.info("Logging in as Recipient: {}", recipientEmail);
            LoginUserHelper(recipientEmail,recipientPwd);
            RecipientDashboardPage recipientdash = new RecipientDashboardPage(driver);

            logger.info("Validating Recipient Dashboard URL...");
            Assert.assertTrue(recipientdash.waitForUrlToContain("/recipient/dashboard"),
                    "Recipient dashboard not loaded");
            logger.info("Selecting blood type filter...");
            Thread.sleep(3000);
            recipientdash.selBloodType();
//            logger.info("Entering city for donor search...");
//            recipientdash.enterCity();
            logger.info("Sending blood request to donor: {}", donorName);
            Thread.sleep(3000);
            boolean isRequestSent = recipientdash.sendRequest(donorName);

            logger.info("Validating request sent successfully...");
            Assert.assertTrue(isRequestSent, "Failed to send request to donor");
            logger.info("Logging out from Recipient account...");
            recipientdash.clickUserDropDown();
            recipientdash.clickLogout();

            LoginUserHelper(donorEmail,donorPwd);
            DonorDashboardPage donordash = new DonorDashboardPage(driver);

            logger.info("Validating Donor Dashboard URL...");
            Assert.assertTrue(donordash.waitForUrlToContain("/donor/dashboard"),
                    "Donor dashboard not loaded");

            logger.info("Checking if incoming request from recipient '{}' is visible...", recipeintName);
            boolean requestReceived = donordash.viewRequest(recipeintName);

            logger.info("Validating incoming request visibility...");
            Assert.assertTrue(requestReceived,
                    "Incoming request from recipient '" + recipeintName + "' not visible");

            logger.info("TEST CASE PASSED");


        }catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }

        finally {
            try {
                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
                logger.info("Logged out in finally block");
            } catch (Exception e) {
                logger.warn("Logout skipped: " + e.getMessage());
            }
        }
        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC025_verifyDonorCanViewIncomingRequests");
        logger.info("=========================================================");

    }
}
