package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS010_DonorDonationHistoryManagement extends BaseClass {

    @Test(priority = 1)
    public void TC031_verifyDonorCanViewDonationHistory(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC031_verifyDonorCanViewDonationHistory");
        logger.info("=========================================================");
        try{
            String[] donorData = registerUserHelper("donor");
            loginUserHelper(donorData[1],donorData[2]);
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
    public void TC032_verifyDonorDonationHistoryUpdatesOnAcceptingRequest(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC032_verifyDonorDonationHistoryUpdatesOnAcceptingRequest");
        logger.info("=========================================================");
        try{
            String[] donorData = RandomDataGeneratorUtil.randomUserDataGenerator();
            String[] recipientData = RandomDataGeneratorUtil.randomUserDataGenerator();
            generateNewBloodRequest(donorData,recipientData);
            RecipientDashboardPage recipientDash = new RecipientDashboardPage(driver);
            recipientDash.clickUserDropDown();
            recipientDash.clickLogout();

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

            logger.info("Attempting to ACCEPT request from recipient: {}", recipientData[0]);
            logger.info("Locating Accept button for recipient: {}", recipientData[0]);
            WebElement acceptBtn = driver.findElement(By.xpath(
                    "//div[@class='requests-list']//h6[text()='" + recipientData[0] + "']" +
                            "/following::div[@class='request-actions']/button[1]"
            ));
            logger.info("Clicking Accept button...");
            acceptBtn.click();
            logger.info("Waiting for success message to appear...");
            WebElement successMsg =
                    driver.findElement(By.xpath("//div[contains(@class,'alert-success')]"));
            Thread.sleep(3000);
            String messageText = successMsg.getText();
            logger.info("Captured success message: {}", messageText);
            boolean result = messageText.contains("accepted this blood request");
            if (result) {
                logger.info("Request accepted successfully");
            } else {
                logger.warn("Success message did not match expected text");
            }            Assert.assertTrue(result, "Donor failed to accept request from recipient: " + recipientData[0]);

            donordash.clickUserDropDown();
            donordash.clickLogout();

            loginUserHelper(recipientData[1],recipientData[2]);
            logger.info("Validating Recipient Dashboard URL...");
            recipientDash.completeRequest(donorData[0]);
            recipientDash.clickUserDropDown();
            recipientDash.clickLogout();

            loginUserHelper(donorData[1],donorData[2]);
            boolean isDonationDisplayed = donordash.isDonationHistoryDisplayed(recipientData[0]);
            logger.info("Validating donation history visibility with approval of recipient : "+recipientData[0]);
            Assert.assertTrue(isDonationDisplayed,
                    "Donation History is not visible");
            logger.info("TEST CASE PASSED");
        }catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC032_verifyDonorDonationHistoryUpdatesOnAcceptingRequest");
        logger.info("=========================================================");
    }

    @Test(priority = 3)
    public void TC033_verifyDonorDonationHistoryRemainsSameOnRejectingRequest(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC033_verifyDonorDonationHistoryRemainsSameOnRejectingRequest");
        logger.info("=========================================================");
        try{
            String[] donorData = RandomDataGeneratorUtil.randomUserDataGenerator();
            String[] recipientData = RandomDataGeneratorUtil.randomUserDataGenerator();
            generateNewBloodRequest(donorData,recipientData);
            RecipientDashboardPage recipientDash = new RecipientDashboardPage(driver);
            recipientDash.clickUserDropDown();
            recipientDash.clickLogout();

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

            logger.info("Attempting to ACCEPT request from recipient: {}", recipientData[0]);
            logger.info("Locating Reject button for recipient: {}", recipientData[0]);

            WebElement acceptBtn = driver.findElement(By.xpath(
                    "//div[@class='requests-list']//h6[text()='" + recipientData[0] + "']" +
                            "/following::div[@class='request-actions']/button[2]"
            ));

            By donationHistoryLocator = By.xpath("//h5[text()='Donation History']/following::div");

            WebElement beforeElement = driver.findElement(donationHistoryLocator);
            String beforeText = beforeElement.getText();

            logger.info("Donation History BEFORE action: {}", beforeText);

            logger.info("Clicking REJECT button...");
            acceptBtn.click();
            logger.info("Waiting for reject message to appear...");
            WebElement RejectMsg =
                    driver.findElement(By.xpath("//div[@class='requests-list']//h6[text()='"+recipientData[0] +"']/following::p[text()=' You marked this as not interested ']"));
            Thread.sleep(3000);
            String messageText = RejectMsg.getText();
            logger.info("Captured success message: {}", messageText);
            boolean result = messageText.contains("You marked this as not interested");
            if (result) {
                logger.info("Request rejected successfully");
            } else {
                logger.warn("reject message did not match expected text");
            }            Assert.assertTrue(result, "Donor failed to reject request from recipient: " + recipientData[0]);
            donordash.clickUserDropDown();
            donordash.clickLogout();

            loginUserHelper(recipientData[1],recipientData[2]);
            logger.info("Validating Recipient Dashboard URL...");
            recipientDash.cancelRequest();
            recipientDash.clickUserDropDown();
            recipientDash.clickLogout();

            loginUserHelper(donorData[1],donorData[2]);
            WebElement afterElement = driver.findElement(donationHistoryLocator);
            String afterText = afterElement.getText();
            logger.info("Donation History AFTER action: {}", afterText);
            Assert.assertEquals(afterText, beforeText,
                    "Donation history changed after action");
            logger.info("TEST CASE PASSED");

        }catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC033_verifyDonorDonationHistoryRemainsSameOnRejectingRequest");
        logger.info("=========================================================");
    }
}
