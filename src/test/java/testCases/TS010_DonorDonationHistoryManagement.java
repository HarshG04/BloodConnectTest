package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

public class TS010_DonorDonationHistoryManagement extends BaseClass {

    public void donorRecipientRequestExchange(String recipientEmail,String recipientPwd,String recipeintName,String donorEmail,String donorPwd,String donorName) throws InterruptedException {
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
        Thread.sleep(4000);
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
    }
    @Test(priority = 1,dataProvider = "donorRecipientData10",dataProviderClass = LoginDataProvider.class)
    public void TC031_verifyDonorCanViewDonationHistory(String recipientEmail,String recipientPwd,String recipientName,String donorEmail,String donorPwd,String donorName){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC031_verifyDonorCanViewDonationHistory");
        logger.info("=========================================================");
        try{
            LoginUserHelper(donorEmail,donorPwd);
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            logger.info("Validating Donor Dashboard URL...");
            Assert.assertTrue(donorDash.waitForUrlToContain("/donor/dashboard"),
                    "Donor dashboard not loaded");

            boolean isDonationDisplayed = donorDash.isDonationHistoryDisplayed();
            logger.info("Validating donation history visibility...");
            Assert.assertTrue(isDonationDisplayed,
                    "Donation History is not visible");

        }catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC031_verifyDonorCanViewDonationHistory");
        logger.info("=========================================================");
    }

    @Test(priority = 2)
    public void TC032_verifyDonorCanAcceptDonationHistory(){

    }
}
