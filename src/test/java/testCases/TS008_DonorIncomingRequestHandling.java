package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS008_DonorIncomingRequestHandling extends BaseClass {

    @Test(priority = 1)
    public void TC025_verifyDonorCanViewIncomingRequests(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC025_verifyDonorCanViewIncomingRequests");
        logger.info("=========================================================");
        try{
            String[][] userData = generateNewBloodRequest();
            String[] donorData = userData[0];
            String[] recipientData = userData[1];

            new RecipientDashboardPage(driver).logoutUser();
            logger.info("Successfully logged out from recipient profile...");

            loginUserHelper(donorData[1], donorData[2]);
            DonorDashboardPage donordash = new DonorDashboardPage(driver);

            logger.info("Validating Donor Dashboard URL...");
            Assert.assertTrue(donordash.waitForUrlToContain("/donor/dashboard"),
                    "Donor dashboard not loaded");

            logger.info("Checking if incoming request from recipient '{}' is visible...", recipientData[0]);
            boolean requestReceived = donordash.viewRequest(recipientData[0]);
            logger.info("Validating incoming request visibility...");
            Assert.assertTrue(requestReceived,
                    "Incoming request from recipient '" + recipientData[0] + "' not visible");
            logger.info("TEST CASE PASSED");

        }catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC025_verifyDonorCanViewIncomingRequests");
        logger.info("=========================================================");

    }

    @Test(priority = 2,dependsOnMethods = "TC025_verifyDonorCanViewIncomingRequests")
    public void TC026_verifyDonorCanAcceptIncomingRequests(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC026_verifyDonorCanRejectIncomingRequests");
        logger.info("=========================================================");
        try{
            String[][] userData = generateNewBloodRequest();
            new RecipientDashboardPage(driver).logoutUser();
            String[] donorData = userData[0];
            String[] recipientData = userData[1];

            loginUserHelper(donorData[1], donorData[2]);
            logger.info("Successfully login the donor who got the request...");

            DonorDashboardPage donordash = new DonorDashboardPage(driver);
            logger.info("Validating Donor Dashboard URL...");
            Assert.assertTrue(donordash.waitForUrlToContain("/donor/dashboard"),
                    "Donor dashboard not loaded");

            logger.info("Checking if incoming request from recipient '{}' is visible...", recipientData[0]);
            boolean requestReceived = donordash.viewRequest(recipientData[0]);
            logger.info("Validating incoming request visibility...");
            Assert.assertTrue(requestReceived,
                    "Incoming request from recipient '" + recipientData[0] + "' not visible");

            logger.info("Attempting to ACCEPT request from recipient: {}", recipientData[0]);

            boolean result = donordash.acceptRequest(recipientData[0]);
            if (result) {
                logger.info("Request accepted successfully");
            } else {
                logger.warn("Success message did not match expected text");
            }            Assert.assertTrue(result, "Donor failed to accept request from recipient: " + recipientData[0]);
            logger.info("TEST CASE PASSED");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC026_verifyDonorCanAcceptIncomingRequests");
        logger.info("=========================================================");
    }

    @Test(priority = 3,dependsOnMethods = "TC025_verifyDonorCanViewIncomingRequests")
    public void TC027_verifyDonorCanRejectIncomingRequests(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC027_verifyDonorCanRejectIncomingRequests");
        logger.info("=========================================================");
        try{
            String[][] userData = generateNewBloodRequest();
            new RecipientDashboardPage(driver).logoutUser();
            String[] donorData = userData[0];
            String[] recipientData = userData[1];

            loginUserHelper(donorData[1], donorData[2]);
            logger.info("Successfully login the donor who got the request...");

            DonorDashboardPage donordash = new DonorDashboardPage(driver);
            logger.info("Validating Donor Dashboard URL...");
            Assert.assertTrue(donordash.waitForUrlToContain("/donor/dashboard"),
                    "Donor dashboard not loaded");
            logger.info("Checking if incoming request from recipient '{}' is visible...", recipientData[0]);
            boolean requestReceived = donordash.viewRequest(recipientData[0]);
            logger.info("Validating incoming request visibility...");
            Assert.assertTrue(requestReceived,
                    "Incoming request from recipient '" + recipientData[0] + "' not visible");

            logger.info("Attempting to REJECT request from recipient: {}", recipientData[0]);

            boolean result = donordash.rejectRequest(recipientData[0]);
            if (result) {
                logger.info("Request rejected successfully");
            } else {
                logger.warn("reject message did not match expected text");
            }            Assert.assertTrue(result, "Donor failed to reject request from recipient: " + recipientData[0]);
            logger.info("TEST CASE PASSED");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC027_verifyDonorCanRejectIncomingRequests");
        logger.info("=========================================================");
    }

}
