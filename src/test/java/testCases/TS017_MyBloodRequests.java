package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

import java.util.List;

public class TS017_MyBloodRequests extends BaseClass {
    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )

    public void TC049_VerifyThatRecepientsCanViewTheirPastBloodRequests(
            String email,
            String password) {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC049_VerifyThatRecepientsCanViewTheirPastBloodRequests");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        try {

            LoginUserHelper(email, password);

            logger.info("Navigating to Recipient Dashboard page");

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);

            logger.info("Fetching current blood request history count");

            int currentHistoryCount =
                    recipientDashboardPage.getBloodRequestHistoryCount();

            logger.info("Current History Count: {}", currentHistoryCount);

            logger.info("Fetching compatible donors");

            List<String> compatibleDonors =
                    recipientDashboardPage.getCompatibleDonors();

            logger.info("Compatible donors found: {}", compatibleDonors.size());

            Assert.assertTrue(
                    compatibleDonors.size() > 0,
                    "No compatible donors available to send blood request"
            );

            logger.info("Compatible Donor List:");

            for(String donor : compatibleDonors){
                logger.info(donor);
            }

            String selectedDonor = compatibleDonors.get(0);

            logger.info("Sending blood request to donor: {}", selectedDonor);

            recipientDashboardPage.sendRequest(selectedDonor);

            logger.info("Verifying blood request success message");

            Assert.assertTrue(
                    recipientDashboardPage.isBloodRequestSentSuccessfully(),
                    "Blood request was not sent successfully"
            );

            logger.info("Fetching updated blood request history count");

            int updatedHistoryCount =
                    recipientDashboardPage.getBloodRequestHistoryCount();

            logger.info("Updated History Count: {}", updatedHistoryCount);

            Assert.assertEquals(
                    updatedHistoryCount,
                    currentHistoryCount + 1,
                    "Blood request history was not updated"
            );

            logger.info(
                    "Blood request history updated successfully after sending request to: {}",
                    selectedDonor
            );

            logger.info("TEST CASE PASSED");

        }
        catch (AssertionError ae) {

            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae;

        }
        catch (Exception e) {

            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");

        }
        finally {

            try {

                RecipientDashboardPage recipientDashboardPage =
                        new RecipientDashboardPage(driver);

                recipientDashboardPage.clickUserDropDown();
                recipientDashboardPage.clickLogout();

                logger.info("Logged out in finally block");

            }
            catch (Exception e) {

                logger.warn("Logout skipped: " + e.getMessage());

            }
        }
    }
}
