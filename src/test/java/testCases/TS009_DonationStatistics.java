package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

public class TS009_DonationStatistics extends BaseClass {

    @Test(dataProvider = "donorLogin", dataProviderClass = LoginDataProvider.class)
    public void TC028_VerifyDonationStatisticsSectionVisibility(String email, String password) {
        logger.info("Starting TC028: Verifying donation statistics section visibility.");
        try {
            LoginUserHelper(email, password);

            DonorDashboardPage donorPage = new DonorDashboardPage(driver);
            boolean isDonationStatsSectionVisible = donorPage.isDonationStatisticsDisplayed();
            logger.info("Visibility status retrieved: " + isDonationStatsSectionVisible);

            Assert.assertTrue(isDonationStatsSectionVisible, "Donation Statistics Section is not visible");
            logger.info("TC028 passed successfully");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC028 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test
    public void TC029_VerifyDonationStatisticsUpdation() {
        logger.info("Starting TC029: Verifying donation statistics increment upon accepting a request.");
        try {
            String[][] userData = generateNewBloodRequest();
            String[] donorData = userData[0];
            String[] recipientData = userData[1];

            new RecipientDashboardPage(driver).logoutUser();

            logger.info("Logging in as donor: " + donorData[1]);
            LoginUserHelper(donorData[1], donorData[2]);

            DonorDashboardPage donorPage = new DonorDashboardPage(driver);
            int initialDonations = donorPage.getTotalDonationsCount();
            logger.info("Baseline count before acceptance: " + initialDonations);

            logger.info("Accepting the blood request sent by: " + recipientData[0]);
            boolean isRequestAccepted = donorPage.acceptRequest(recipientData[0]);
            Assert.assertTrue(isRequestAccepted, "Failed to successfully accept the recipient's request!");
            donorPage.refreshPage();
            int updatedDonations = donorPage.getTotalDonationsCount();
            logger.info("Post-acceptance count: " + updatedDonations);

            Assert.assertEquals(updatedDonations, initialDonations + 1,
                    "FAILED: Total donations counter did not increase after accepting the request!");

            donorPage.logoutUser();
            logger.info("TC029 passed successfully");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC029 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test
    public void TC030_VerifyDonationStatisticsStaysSameOnReject() {
        logger.info("Starting TC030: Verifying donation statistics remain static upon rejecting a request.");
        try {
            String[][] userData = generateNewBloodRequest();
            String[] donorData = userData[0];
            String[] recipientData = userData[1];

            new RecipientDashboardPage(driver).logoutUser();

            logger.info("Logging in as donor: " + donorData[1]);
            LoginUserHelper(donorData[1], donorData[2]);

            DonorDashboardPage donorPage = new DonorDashboardPage(driver);
            int initialDonations = donorPage.getTotalDonationsCount();
            logger.info("Baseline count before rejection: " + initialDonations);

            logger.info("Rejecting the blood request sent by: " + recipientData[0]);
            boolean isRequestRejected = donorPage.rejectRequest(recipientData[0]);
            Assert.assertTrue(isRequestRejected, "Failed to successfully reject the recipient's request!");

            int updatedDonations = donorPage.getTotalDonationsCount();
            logger.info("Post-rejection count: " + updatedDonations);

            Assert.assertEquals(updatedDonations, initialDonations,
                    "FAILED: Total donations counter changed after a request rejection!");

            donorPage.logoutUser();
            logger.info("TC030 passed successfully");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC030 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}