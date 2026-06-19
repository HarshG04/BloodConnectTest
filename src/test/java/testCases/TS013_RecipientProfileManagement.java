package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;


public class TS013_RecipientProfileManagement extends BaseClass {

    private void loginAsRecipient(String email, String password) {

        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();
    }


    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC038_VerifyRecipientCanUpdateName(String email, String password) {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC038_VerifyRecipientCanUpdateName");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        RecipientDashboardPage recipientDashboardPage=null;
        try {

            loginAsRecipient(email, password);

            recipientDashboardPage= new RecipientDashboardPage(driver);

            logger.info("Capturing current recipient name...");
            String oldName = recipientDashboardPage.getRecipientName();
            logger.info("Current Name: " + oldName);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            String[] updatedName = RandomDataGeneratorUtil.randomUserDataGenerator();

            logger.info("Updating recipient name to: " + updatedName[0]);
            recipientEditProfilePage.setRecipientName(updatedName[0]);

            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing updated name from profile...");
            String actualName = recipientDashboardPage.getRecipientName();

            logger.info("Verifying recipient name was updated successfully...");
            Assert.assertEquals(actualName, updatedName[0], "Recipient could not update name as required by SRS 4.1.");

            logger.info("SUCCESS: Recipient name updated successfully.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during test execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Recipient could not update name as required by SRS 4.1." + e.getMessage());
        }
        finally {
            if(recipientDashboardPage!=null){
                try{
                    recipientDashboardPage.clickUserDropDown();
                    recipientDashboardPage.clickLogout();
                }
                catch(Exception e){
                    logger.warn("Unable to Logout");
                }
            }
        }
    }

    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC039_VerifyRecipientCanUpdateBloodGroup(String email, String password) throws InterruptedException {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC039_VerifyRecipientCanUpdateBloodGroup");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        RecipientDashboardPage recipientDashboardPage=null;
        try {

            loginAsRecipient(email, password);

            recipientDashboardPage= new RecipientDashboardPage(driver);

            logger.info("Capturing recipient's current BloodType...");
            String oldBloodType = recipientDashboardPage.getRecipientBloodType();
            logger.info("Current Blood Type: " + oldBloodType);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            String[] updatedBloodType = RandomDataGeneratorUtil.randomUserDataGenerator();

            logger.info("Updating recipient's Blood Type to: " + updatedBloodType[7]);
            recipientEditProfilePage.setBloodType(updatedBloodType[7]);
            Thread.sleep(3000);
            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing updated Blood Type from profile...");
            String actualBloodType = recipientDashboardPage.getRecipientBloodType();

            logger.info("Verifying recipient Bloot Type was updated successfully...");
            Assert.assertEquals(actualBloodType, updatedBloodType[7], "Recipient could not update Blood Type as required by SRS 4.1.");

            logger.info("SUCCESS: Recipient Blood Type updated successfully.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during test execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Recipient could not update Blood Type as required by SRS 4.1." + e.getMessage());
        }
        finally {
            if(recipientDashboardPage!=null){
                try{
                    recipientDashboardPage.clickUserDropDown();
                    recipientDashboardPage.clickLogout();
                    logger.info("Logout Success");
                }
                catch(Exception e){
                    logger.warn("Unable to Logout");
                }
            }
        }
    }
    @Test
    public void TC040_VerifyRecipientCanUpdateContactDetails(){

    }
    @Test
    public void TC041_VerifyRecipientCannotUpdateEmailIfAlreadyBelongsToAnotherUser(){

    }
    @Test
    public void TC042_VerifyRecipientCannotUpdatePhoneNumberIfAlreadyBelongsToAnotherUser(){

    }
    @Test
    public void TC043_VerifyRecipientCanUpdateLocation(){

    }
}
