package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.DonorProfilePage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

import java.util.List;

public class TS014_SearchAndFilterDonorWorks extends BaseClass {
    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class, enabled = false
    )
    public void TC044_VerifyThatFilterByBloodGroupWorks(String email, String password){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC044_VerifyThatFilterByBloodGroupWorks");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        try {

            LoginUserHelper(email, password);

            logger.info("Navigating to Recipient Profile page");

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);

            String expectedBloodType =
                    recipientDashboardPage.getRecipientBloodType();

            logger.info("Selecting Required Blood Type: " + expectedBloodType);

            recipientDashboardPage.selBloodType();

            logger.info("Fetching all displayed donor blood groups");
            Thread.sleep(3000);

            List<String> displayedBloodTypes =
                    recipientDashboardPage.getDisplayedDonorsBloodType();

            logger.info("Number of displayed donor cards: "
                    + displayedBloodTypes.size());

            for(String actualBloodType : displayedBloodTypes){

                logger.info("Expected Blood Type: {}", expectedBloodType);
                logger.info("Actual Blood Type: {}", actualBloodType);
                Thread.sleep(3000);
                Assert.assertEquals(
                        actualBloodType.trim(),
                        expectedBloodType.trim(),
                        "Found donor card with incorrect blood group");
            }

            logger.info("All displayed donor cards match selected blood group: "
                    + expectedBloodType);

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

    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class, enabled = false
    )
    public void TC045_VerifyThatFilterByLocationWorks(String email, String password){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC045_VerifyThatFilterByLocationWorks");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        try {

            LoginUserHelper(email, password);

            logger.info("Navigating to Recipient Profile page");

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);

            String expectedLocation =
                    recipientDashboardPage.getRecipientLocation();

            logger.info("Entering Required Location: " + expectedLocation);

            recipientDashboardPage.enterCity();

            logger.info("Fetching all displayed donors' Locations");
            Thread.sleep(3000);

            List<String> displayedCards =
                    recipientDashboardPage.getDisplayedDonorsLocation();

            logger.info("Number of displayed donor cards: "
                    + displayedCards.size());

            for(String actualLocation : displayedCards){

                logger.info("Expected Location: {}", expectedLocation);
                logger.info("Actual Location: {}", actualLocation);
                Assert.assertEquals(
                        actualLocation.trim(),
                        expectedLocation.trim(),
                        "Found donor card with incorrect Location");
            }

            logger.info("All displayed donor cards match selected Location: "
                    + expectedLocation);

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
    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC046_VerifyThatFilterByBloodGroupAndLocationWorks(String email, String password){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC046_VerifyThatFilterByBloodGroupAndLocationWorks");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        try {

            LoginUserHelper(email, password);

            logger.info("Navigating to Recipient Profile page");

            RecipientDashboardPage recipientDashboardPage =
                    new RecipientDashboardPage(driver);

            String expectedBloodType =
                    recipientDashboardPage.getRecipientBloodType();

            String expectedLocation =
                    recipientDashboardPage.getRecipientLocation();

            logger.info("Entering Required Location: " + expectedLocation);
            recipientDashboardPage.enterCity();

            logger.info("Selecting Required Blood Type: " + expectedBloodType);
            recipientDashboardPage.selBloodType();

            logger.info("Fetching all displayed donor details");

            Thread.sleep(3000);

            List<String> displayedBloodTypes =
                    recipientDashboardPage.getDisplayedDonorsBloodType();

            List<String> displayedLocations =
                    recipientDashboardPage.getDisplayedDonorsLocation();

            logger.info("Number of displayed donor cards: "
                    + displayedBloodTypes.size());

            Assert.assertEquals(
                    displayedBloodTypes.size(),
                    displayedLocations.size(),
                    "Mismatch between displayed donor blood type and location data"
            );

            for(int i = 0; i < displayedBloodTypes.size(); i++){

                String actualBloodType = displayedBloodTypes.get(i);
                String actualLocation = displayedLocations.get(i);

                logger.info("Expected Blood Type: {}", expectedBloodType);
                logger.info("Actual Blood Type: {}", actualBloodType);

                logger.info("Expected Location: {}", expectedLocation);
                logger.info("Actual Location: {}", actualLocation);

                Assert.assertEquals(
                        actualBloodType.trim(),
                        expectedBloodType.trim(),
                        "Found donor card with incorrect Blood Type"
                );

                Assert.assertEquals(
                        actualLocation.trim(),
                        expectedLocation.trim(),
                        "Found donor card with incorrect Location"
                );
            }

            logger.info("All displayed donor cards match selected Blood Type and Location");

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
