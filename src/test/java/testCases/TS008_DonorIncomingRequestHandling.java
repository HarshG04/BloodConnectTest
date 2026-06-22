package testCases;

import DataProviders.LoginDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS008_DonorIncomingRequestHandling extends BaseClass {
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

    @Test(priority = 1, dataProvider = "donorRecipientData08", dataProviderClass = LoginDataProvider.class)
    public void TC025_verifyDonorCanViewIncomingRequests(String recipientEmail,String recipientPwd,String recipeintName,String donorEmail,String donorPwd,String donorName){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC025_verifyDonorCanViewIncomingRequests");
        logger.info("=========================================================");
        try{
            String[] donorData = RandomDataGeneratorUtil.randomUserDataGenerator();
            String[] recipientData = RandomDataGeneratorUtil.randomUserDataGenerator();
            generateNewBloodRequest(donorData,recipientData);
            RecipientDashboardPage recipientDash = new RecipientDashboardPage(driver);
            recipientDash.clickUserDropDown();
            recipientDash.clickLogout();

            LoginUserHelper(donorData[1], donorData[2]);
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

//    @Test(priority = 2,dependsOnMethods = "TC025_verifyDonorCanViewIncomingRequests",dataProvider = "donorRecipientData08",dataProviderClass = LoginDataProvider.class)
//    public void TC026_verifyDonorCanAcceptIncomingRequests(String recipientEmail,String recipientPwd,String recipeintName,String donorEmail,String donorPwd,String donorName){
//        logger.info("=========================================================");
//        logger.info("STARTING TEST CASE: TC026_verifyDonorCanRejectIncomingRequests");
//        logger.info("=========================================================");
//        try{
//            donorRecipientRequestExchange(recipientEmail,recipientPwd,recipeintName,donorEmail,donorPwd,donorName);
//            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
//            logger.info("Attempting to ACCEPT request from recipient: {}", recipeintName);
//            logger.info("Locating Accept button for recipient: {}", recipeintName);
//            WebElement acceptBtn = driver.findElement(By.xpath(
//                    "//div[@class='requests-list']//h6[text()='" + recipeintName + "']" +
//                            "/following::div[@class='request-actions']/button[1]"
//            ));
//            logger.info("Clicking Accept button...");
//            acceptBtn.click();
//            logger.info("Waiting for success message to appear...");
//            WebElement successMsg =
//                    driver.findElement(By.xpath("//div[contains(@class,'alert-success')]"));
//            Thread.sleep(3000);
//            String messageText = successMsg.getText();
//            logger.info("Captured success message: {}", messageText);
//            boolean result = messageText.contains("accepted this blood request");
//            if (result) {
//                logger.info("Request accepted successfully");
//            } else {
//                logger.warn("Success message did not match expected text");
//            }            Assert.assertTrue(result, "Donor failed to accept request from recipient: " + recipeintName);
//            logger.info("TEST CASE PASSED");
//        }
//        catch (AssertionError ae) {
//            logger.error("ASSERTION FAILED : " + ae.getMessage());
//            throw ae; // rethrow to mark test as FAILED
//
//        } catch (Exception e) {
//            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
//            Assert.fail("Test failed due to unexpected exception");
//        }
//
//        finally {
//            try {
//                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
//                donorDash.clickUserDropDown();
//                donorDash.clickLogout();
//                logger.info("Logged out in finally block");
//            } catch (Exception e) {
//                logger.warn("Logout skipped: " + e.getMessage());
//            }
//        }
//        logger.info("=========================================================");
//        logger.info("ENDING TEST CASE: TC026_verifyDonorCanAcceptIncomingRequests");
//        logger.info("=========================================================");
//    }
//
//    @Test(priority = 3,dependsOnMethods = "TC025_verifyDonorCanViewIncomingRequests",dataProvider = "donorRecipientData08",dataProviderClass = LoginDataProvider.class)
//    public void TC027_verifyDonorCanRejectIncomingRequests(String recipientEmail,String recipientPwd,String recipeintName,String donorEmail,String donorPwd,String donorName){
//        logger.info("=========================================================");
//        logger.info("STARTING TEST CASE: TC027_verifyDonorCanRejectIncomingRequests");
//        logger.info("=========================================================");
//        try{
//            donorRecipientRequestExchange(recipientEmail,recipientPwd,recipeintName,donorEmail,donorPwd,donorName);
//            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
//            logger.info("Attempting to REJECT request from recipient: {}", recipeintName);
//            logger.info("Locating REJECT button for recipient: {}", recipeintName);
//            WebElement acceptBtn = driver.findElement(By.xpath(
//                    "//div[@class='requests-list']//h6[text()='" + recipeintName + "']" +
//                            "/following::div[@class='request-actions']/button[2]"
//            ));
//            logger.info("Clicking REJECT button...");
//            acceptBtn.click();
//            logger.info("Waiting for success message to appear...");
//            WebElement RejectMsg =
//                    driver.findElement(By.xpath("//div[@class='requests-list']//h6[text()='"+recipeintName +"']/following::p[text()=' You marked this as not interested ']"));
//            Thread.sleep(3000);
//            String messageText = RejectMsg.getText();
//            logger.info("Captured success message: {}", messageText);
//            boolean result = messageText.contains("You marked this as not interested");
//            if (result) {
//                logger.info("Request rejected successfully");
//            } else {
//                logger.warn("reject message did not match expected text");
//            }            Assert.assertTrue(result, "Donor failed to reject request from recipient: " + recipeintName);
//            logger.info("TEST CASE PASSED");
//        }
//        catch (AssertionError ae) {
//            logger.error("ASSERTION FAILED : " + ae.getMessage());
//            throw ae; // rethrow to mark test as FAILED
//
//        } catch (Exception e) {
//            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
//            Assert.fail("Test failed due to unexpected exception");
//        }
//
//        finally {
//            try {
//                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
//                donorDash.clickUserDropDown();
//                donorDash.clickLogout();
//                logger.info("Logged out in finally block");
//            } catch (Exception e) {
//                logger.warn("Logout skipped: " + e.getMessage());
//            }
//        }
//        logger.info("=========================================================");
//        logger.info("ENDING TEST CASE: TC027_verifyDonorCanRejectIncomingRequests");
//        logger.info("=========================================================");
//    }

}
