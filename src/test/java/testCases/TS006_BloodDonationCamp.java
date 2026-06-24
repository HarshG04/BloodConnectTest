package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.BloodCampPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS006_BloodDonationCamp extends BaseClass {

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
    public void TC017_verifyActiveBloodCampsVisibility(String email,String password){
        logger.info("=========================================================");
        logger.info("Starting Test: TC017_verifyActiveBloodCampsVisibility");
        logger.info("=========================================================");

        try {
            int initialBloodCampsCount = new HomePage(driver).getBloodCampsCount();
            logger.info("Initial blood camps count: " + initialBloodCampsCount);

            logger.info("Logging in with Admin: " + email);
            LoginUserHelper(email,password);

            AdminPage adminPage = new AdminPage(driver);
            logger.info("Clicking Blood Camps Menu");
            adminPage.clickBloodCampsMenu();

            BloodCampPage bloodCamp = new BloodCampPage(driver);
            logger.info("Clicking Add Camp Button");
            bloodCamp.clickAddCampButton();

            logger.info("Generating random blood camp data");
            RandomDataGeneratorUtil.randomBloodCampGenerator(bloodCamp);

            logger.info("Waiting for 3 seconds...");
            Thread.sleep(3000);

            logger.info("Navigating back to Home");
            adminPage.clickHome();

            int updatedBloodCampsCount = new HomePage(driver).getBloodCampsCount();
            logger.info("Updated blood camps count: " + updatedBloodCampsCount);

            logger.info("Verifying blood camp count increased by 1");
            Assert.assertEquals(updatedBloodCampsCount,initialBloodCampsCount+1);
            logger.info("Test TC017 passed successfully");

        } catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the assertion failure directly to TestNG for reporting
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC017 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
    public void TC018_verifyNoBloodCampMessage(String email,String password){
        logger.info("=========================================================");
        logger.info("Starting Test: TC018_verifyNoBloodCampMessage");
        logger.info("=========================================================");

        try {
            logger.info("Logging in with user: " + email);
            LoginUserHelper(email,password);

            AdminPage adminPage = new AdminPage(driver);
            logger.info("Clicking Blood Camps Menu");
            adminPage.clickBloodCampsMenu();

            logger.info("Deleting all blood camps");
            new BloodCampPage(driver).deleteAllBloodCamps();

            logger.info("Waiting for 3 seconds...");
            Thread.sleep(3000);

            logger.info("Navigating back to Home");
            adminPage.clickHome();

            logger.info("Checking visibility of 'No Blood Camp' message");
            boolean isNoBloodCampMessageVisible = new HomePage(driver).checkNoBloodCampMessageVisibility();

            logger.info("Verifying message visibility status");
            Assert.assertTrue(isNoBloodCampMessageVisible);
            logger.info("Test TC018 passed successfully");

        }
        catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the assertion failure directly to TestNG for reporting
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC018 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}