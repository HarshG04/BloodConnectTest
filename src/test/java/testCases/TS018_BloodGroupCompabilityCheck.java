package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

import java.util.List;

public class TS018_BloodGroupCompabilityCheck extends BaseClass {

    @Test
    public void TC050_VerifyThatANegativeCanReceiveOnlyFromANegativeAndONegative() {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC050_VerifyThatANegativeCanReceiveOnlyFromANegativeAndONegative");
        logger.info("=========================================================");

        try {

            logger.info("Creating A- Donor");

            String[] aMinusDonor =
                    RandomDataGeneratorUtil.randomUserDataGenerator();

            aMinusDonor[7] = "A-";

            registerUserHelper(aMinusDonor, "donor");

            logger.info("A- Donor Created: {} | Blood Group: {} | City: {}",
                    aMinusDonor[0], aMinusDonor[7], aMinusDonor[9]);

            logger.info("Creating O- Donor");

            String[] oMinusDonor =
                    RandomDataGeneratorUtil.randomUserDataGenerator();

            oMinusDonor[7] = "O-";

            registerUserHelper(oMinusDonor, "donor");

            logger.info("O- Donor Created: {} | Blood Group: {} | City: {}",
                    oMinusDonor[0], oMinusDonor[7], oMinusDonor[9]);

            logger.info("Creating A- Recipient");

            String[] aMinusRecipient =
                    RandomDataGeneratorUtil.randomUserDataGenerator();

            aMinusRecipient[7] = "A-";

            registerUserHelper(aMinusRecipient, "recipient");

            logger.info("A- Recipient Created: {} | Blood Group: {}",
                    aMinusRecipient[0], aMinusRecipient[7]);

            logger.info("Logging in as A- Recipient");

            LoginUserHelper(
                    aMinusRecipient[1],
                    aMinusRecipient[2]
            );

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);

            logger.info("Filtering for A- donor");

            recipientDashboardPage.setFilterFields(
                    "A-",
                    aMinusDonor[9]
            );

            List<String> compatibleDonors =
                    recipientDashboardPage.getCompatibleDonors();

            logger.info("Compatible donors after A- filter: {}", compatibleDonors);

            Assert.assertTrue(
                    compatibleDonors.contains(aMinusDonor[0]),
                    "A- donor is not marked as compatible"
            );

            logger.info("A- donor verified as compatible");

            recipientDashboardPage.clearFilterByCityField();


            logger.info("Filtering for O- donor");


            recipientDashboardPage.setFilterFields(
                    "O-",
                    oMinusDonor[9]
            );

            compatibleDonors =
                    recipientDashboardPage.getCompatibleDonors();

            logger.info("Compatible donors after O- filter: {}", compatibleDonors);

            Assert.assertTrue(
                    compatibleDonors.contains(oMinusDonor[0]),
                    "O- donor is not marked as compatible"
            );

            logger.info("O- donor verified as compatible");

            logger.info(
                    "Verified that an A- recipient can receive from A- and O- donors"
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
