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

    @Test(priority = 1)
    public void TC025_verifyDonorCanViewIncomingRequests(){
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
            logger.info("Checking if incoming request from recipient '{}' is visible...", recipientData[0]);
            boolean requestReceived = donordash.viewRequest(recipientData[0]);
            logger.info("Validating incoming request visibility...");
            Assert.assertTrue(requestReceived,
                    "Incoming request from recipient '" + recipientData[0] + "' not visible");

            logger.info("Attempting to REJECT request from recipient: {}", recipientData[0]);
            logger.info("Locating REJECT button for recipient: {}", recipientData[0]);
            WebElement acceptBtn = driver.findElement(By.xpath(
                    "//div[@class='requests-list']//h6[text()='" + recipientData[0] + "']" +
                            "/following::div[@class='request-actions']/button[2]"
            ));
            logger.info("Clicking REJECT button...");
            acceptBtn.click();
            logger.info("Waiting for success message to appear...");
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
