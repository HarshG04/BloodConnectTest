package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

import java.util.List;

public class TS017_MyBloodRequests extends BaseClass {
    @Test(groups = "recipient")
    public void TC049_VerifyThatRecipientsCanViewTheirPastBloodRequests(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC049_VerifyThatRecepientsCanViewTheirPastBloodRequests");
        logger.info("=========================================================");

        try {
            String[] recipientData = registerUserHelper("recipient");
            String[] donorData = registerUserHelper("donor");
            loginUserHelper(recipientData[1], recipientData[2]);

            logger.info("Navigating to Recipient Dashboard page");

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);

            logger.info("Fetching current blood request history count");

            int currentHistoryCount =
                    recipientDashboardPage.getBloodRequestHistoryCount();

            logger.info("Current History Count: {}", currentHistoryCount);

            generateNewBloodRequest(donorData,recipientData);

            logger.info("Fetching updated blood request history count");

            int updatedHistoryCount =
                    recipientDashboardPage.getBloodRequestHistoryCount();

            logger.info("Updated History Count: {}", updatedHistoryCount);

            Assert.assertEquals(
                    updatedHistoryCount,
                    currentHistoryCount + 1,
                    "Blood request history was not updated"
            );

            logger.info("Blood request history updated successfully after sending request to: {}",donorData[1]);

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
    }
}
