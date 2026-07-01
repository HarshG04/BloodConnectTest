package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

import java.util.List;

public class TS016_SendBloodRequest extends BaseClass {
    @Test(groups = {"recipient","smoke"})
    public void TC048_VerifyThatRecipientsCanSendBloodRequestsToCompatibleDonors() {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC048_VerifyThatRecipientsCanSendBloodRequestsToCompatibleDonors");
        logger.info("=========================================================");

        try {
            generateNewBloodRequest();
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
