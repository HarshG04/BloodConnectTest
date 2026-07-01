package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

public class TS015_AvailableDonorSection extends BaseClass {
    @Test(groups = {"recipient","regression"})
    public void TC047_VerifyAvailableDonorSectionIsVisible() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC047_VerifyAvailableDonorSectionIsVisible");
        logger.info("=========================================================");

        try {
            String[] recipientData = registerUserHelper("recipient");
            loginUserHelper(recipientData[1], recipientData[2]);

            logger.info("Navigating to Recipient Profile page");

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);



            logger.info("Verifying Available Donors section visibility");

            boolean isVisible =
                    recipientDashboardPage.isAvailableDonorsVisible();

            logger.info("Available Donors section visible: {}", isVisible);

            Assert.assertTrue(
                    isVisible,
                    "Available Donors section is not visible"
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
    }
}
