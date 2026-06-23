package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

import java.util.List;

public class TS016_SendBloodRequest extends BaseClass {
    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC048_VerifyThatRecipientsCanSendBloodRequestsToCompatibleDonors(
            String email,
            String password) {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC048_VerifyThatRecipientsCanSendBloodRequestsToCompatibleDonors");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        try {

            LoginUserHelper(email, password);

            logger.info("Navigating to Recipient Dashboard page");

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);

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

            logger.info(
                    "Blood request sent successfully to donor: {}",
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
