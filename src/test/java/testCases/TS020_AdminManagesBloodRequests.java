package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.BloodRequestsPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

public class TS020_AdminManagesBloodRequests extends BaseClass {

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC053_VerifyAllBloodRequestsVisibilityToAdmin(String email,String password){
        logger.info("Starting Test: TC053_VerifyAllBloodRequestsVisibilityToAdmin");
        try {
            helperMethod(email,password);
            BloodRequestsPage requestsPage = new BloodRequestsPage(driver);
            int totalRequestCount = requestsPage.getTotalRequestCount();
            logger.info("Total request count from backend/badge: " + totalRequestCount);

            int visibleRequestCount = requestsPage.getVisibleRequestCount();
            logger.info("Visible request card count on UI: " + visibleRequestCount);

            logger.info("Asserting visible request count matches total request count");
            Assert.assertEquals(visibleRequestCount,totalRequestCount,"Count of Total Requests and Visible Requests are not equal...");
            logger.info("Test TC053 passed successfully");
        }
        catch(AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC054_VerifyAdminApprovesBloodRequests(String email,String password) throws InterruptedException {
        logger.info("Starting Test: TC054_VerifyAdminApprovesBloodRequests");
        try {
            helperMethod(email, password);
            BloodRequestsPage bloodRequestsPage = new BloodRequestsPage(driver);
            int initialPendingRequestCount = bloodRequestsPage.getPendingRequestCount();
            logger.info("Initial pending request count: " + initialPendingRequestCount);

            logger.info("Attempting to approve a blood request");
            boolean isRequestApproved = bloodRequestsPage.approveBloodRequest();
            logger.info("Is request action performed: " + isRequestApproved);

            logger.info("Waiting for 2 seconds after approval action...");
            Thread.sleep(2000);

            int updatedPendingRequestCount = bloodRequestsPage.getPendingRequestCount();
            logger.info("Updated pending request count: " + updatedPendingRequestCount);

            if(isRequestApproved) {
                logger.info("Asserting pending request count decremented by 1");
                Assert.assertEquals(updatedPendingRequestCount,initialPendingRequestCount-1,"Request not get approved");
            }
            else {
                logger.warn("No pending request was available to approve");
                Assert.fail("No Request found to approve");
            }
            logger.info("Test TC054 passed successfully");
        }
        catch(AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC055_VerifyAdminRejectBloodRequests(String email,String password) throws InterruptedException {
        logger.info("Starting Test: TC055_VerifyAdminRejectBloodRequests");
        try {
            helperMethod(email, password);
            BloodRequestsPage bloodRequestsPage = new BloodRequestsPage(driver);
            int initialPendingRequestCount = bloodRequestsPage.getPendingRequestCount();
            logger.info("Initial pending request count: " + initialPendingRequestCount);

            logger.info("Attempting to reject a blood request");
            boolean isRequestApproved = bloodRequestsPage.rejectBloodRequest();
            logger.info("Is request action performed: " + isRequestApproved);

            logger.info("Waiting for 2 seconds after rejection action...");
            Thread.sleep(2000);

            int updatedPendingRequestCount = bloodRequestsPage.getPendingRequestCount();
            logger.info("Updated pending request count: " + updatedPendingRequestCount);

            if (isRequestApproved) {
                logger.info("Asserting pending request count decremented by 1");
                Assert.assertEquals(updatedPendingRequestCount, initialPendingRequestCount - 1, "Request not get rejected");
            }
            else {
                logger.warn("No pending request was available to reject");
                Assert.fail("No Request found to reject");
            }
            logger.info("Test TC055 passed successfully");
        }
        catch(AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class,groups = "admin")
    public void TC056_VerifyAdminFulfilledBloodRequests(String email,String password) throws InterruptedException {
        logger.info("Starting Test: TC056_VerifyAdminFulfilledBloodRequests");
        try {
            helperMethod(email, password);
            BloodRequestsPage bloodRequestsPage = new BloodRequestsPage(driver);
            int initialPendingRequestCount = bloodRequestsPage.getPendingRequestCount();
            logger.info("Initial pending request count: " + initialPendingRequestCount);

            logger.info("Attempting to fulfill a blood request");
            boolean isRequestApproved = bloodRequestsPage.fulfilledBloodRequest();
            logger.info("Is request action performed: " + isRequestApproved);

            logger.info("Waiting for 2 seconds after fulfillment action...");
            Thread.sleep(2000);

            int updatedPendingRequestCount = bloodRequestsPage.getPendingRequestCount();
            logger.info("Updated pending request count: " + updatedPendingRequestCount);

            if (isRequestApproved) {
                logger.info("Asserting pending request count decremented by 1");
                Assert.assertEquals(updatedPendingRequestCount, initialPendingRequestCount - 1, "Request not get Fulfilled");
            }
            else {
                logger.warn("No pending request was available to fulfill");
                Assert.fail("No Request found to fulfill");
            }
            logger.info("Test TC056 passed successfully");
        }
        catch(AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during validation tracking sequence!");
            logger.error("Exception message context: " + e.getMessage());
            Assert.fail("Test system encountered exception crash payload: " + e.getMessage());
        }
    }

    private void helperMethod(String email,String password){
        logger.info("Executing helper setup sequence: Generating prerequisite request environment state");
        generateNewBloodRequest();

        logger.info("Logging out current Recipient user session context");
        new RecipientDashboardPage(driver).logoutUser();

        logger.info("Logging in with target Admin identity: " + email);
        loginUserHelper(email,password);

        logger.info("Navigating to Blood Requests dashboard management grid");
        new AdminPage(driver).clickBloodRequests();
    }
}
