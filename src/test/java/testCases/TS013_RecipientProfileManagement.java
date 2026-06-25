package testCases;

import DataProviders.LoginDataProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;


public class TS013_RecipientProfileManagement extends BaseClass {

    private String[] recipientData;

    @BeforeMethod
    public void RegisterNewRecipient(){
        if(recipientData==null) recipientData = registerUserHelper("recipient");
    }

    @Test
    public void TC038_VerifyRecipientCanUpdateName() {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC038_VerifyRecipientCanUpdateName");
        logger.info("Target Account: [ " + recipientData[1] + " ]");
        logger.info("=========================================================");

        try {

            loginUserHelper(recipientData[1], recipientData[2]);
            logger.info("Successfully loggedIn as Recipient...");

            RecipientDashboardPage recipientDashboardPage= new RecipientDashboardPage(driver);

            logger.info("Capturing current recipient name...");
            String oldName = recipientDashboardPage.getRecipientName();
            logger.info("Current Name: " + oldName);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            boolean isNameFieldEditable = recipientEditProfilePage.isNameFieldEditable();
            Assert.assertTrue(isNameFieldEditable,"Name Field is not Editable...");

            String updatedName = RandomStringUtils.randomAlphabetic(5);

            logger.info("Updating recipient name to: " + updatedName);
            recipientEditProfilePage.setRecipientName(updatedName);

            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing updated name from profile...");
            String actualName = recipientDashboardPage.getRecipientName();

            logger.info("Verifying recipient name was updated successfully...");
            Assert.assertEquals(actualName, updatedName, "Recipient could not update name as required by SRS 4.1.");

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
    }

    @Test
    public void TC039_VerifyRecipientCanUpdateBloodGroup(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC039_VerifyRecipientCanUpdateBloodGroup");
        logger.info("Target Account: [ " + recipientData[1] + " ]");
        logger.info("=========================================================");

        RecipientDashboardPage recipientDashboardPage=null;
        try {

            loginUserHelper(recipientData[1], recipientData[2]);
            logger.info("Successfully loggedIn as Recipient...");

            recipientDashboardPage= new RecipientDashboardPage(driver);

            logger.info("Capturing recipient's current BloodType...");
            String oldBloodType = recipientDashboardPage.getRecipientBloodType();
            logger.info("Current Blood Type: " + oldBloodType);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            String updatedBloodType = RandomDataGeneratorUtil.randomBloodGroupGenerator(recipientData[7]);

            logger.info("Updating recipient's Blood Type to: " + updatedBloodType);
            recipientEditProfilePage.setRecipientBloodType(updatedBloodType);
            Thread.sleep(3000);
            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing updated Blood Type from profile...");
            String actualBloodType = recipientDashboardPage.getRecipientBloodType();

            logger.info("Verifying recipient Bloot Type was updated successfully...");
            Assert.assertEquals(actualBloodType, updatedBloodType, "Recipient could not update Blood Type as required by SRS 4.1.");

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
    }
    @Test
    public void TC040_VerifyRecipientCanUpdateContactDetails(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC040_VerifyRecipientCanUpdateContactDetails");
        logger.info("Target Account: [ " + recipientData[1] + " ]");
        logger.info("=========================================================");

        try {

            loginUserHelper(recipientData[1], recipientData[2]);
            logger.info("Successfully loggedIn as Recipient...");

            RecipientDashboardPage recipientDashboardPage= new RecipientDashboardPage(driver);

            logger.info("Capturing recipient's current MailId...");
            String oldEmail = recipientDashboardPage.getRecipientEmail();
            logger.info("Current Email: " + oldEmail);

            logger.info("Capturing recipient's current Phone Number...");
            String oldPhoneNo = recipientDashboardPage.getRecipientPhoneno();
            logger.info("Current Phone Number: " + oldPhoneNo);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            String[] updatedContact = RandomDataGeneratorUtil.randomUserDataGenerator();

            boolean isEmailFieldEditable = recipientEditProfilePage.isEmailFieldEditable();
            Assert.assertTrue(isEmailFieldEditable,"Email field is not editable...");
            boolean isPhoneFieldEditable = recipientEditProfilePage.isPhoneFieldEditable();
            Assert.assertTrue(isPhoneFieldEditable,"Phone Number field is not editable...");

            logger.info("Updating recipient's Email to: " + updatedContact[1]);
            recipientEditProfilePage.setRecipientEmail(updatedContact[1]);
            //Thread.sleep(3000);
            logger.info("Updating recipient's Phone Number to: " + updatedContact[3]);
            recipientEditProfilePage.setRecipientPhoneno(updatedContact[3]);


            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing Email from profile...");
            String actualEmail = recipientDashboardPage.getRecipientEmail();

            logger.info("Verifying recipient Email was updated successfully...");
            Assert.assertEquals(actualEmail, updatedContact[1], "Recipient could not update Email as required by SRS 4.1.");

            logger.info("SUCCESS: Recipient Blood Type updated successfully.");

            logger.info("Capturing Phone Number from profile...");
            String actualPhoneNo = recipientDashboardPage.getRecipientPhoneno();

            logger.info("Verifying recipient Phone Number was updated successfully...");
            Assert.assertEquals(actualPhoneNo, updatedContact[3], "Recipient could not update Phone Number as required by SRS 4.1.");

            logger.info("SUCCESS: Recipient Phone Number updated successfully.");


        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during test execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Recipient could not update Contact Information as required by SRS 4.1." + e.getMessage());
        }
    }


    @Test
    public void TC041_VerifyRecipientCannotUpdateEmailIfAlreadyBelongsToAnotherUser(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC041_VerifyRecipientCannotUpdateEmailIfAlreadyBelongsToAnotherUser");
        logger.info("=========================================================");


        try {
            logger.info("PHASE 1: Generating unique user dataset to seed the database...");
            String[] recipientData1 = registerUserHelper("recipient");

            logger.info("PHASE 2: Navigating back to the login screen to test duplicate constraint...");
            logger.info("Entering login credentials");

            loginUserHelper(recipientData[1],recipientData[2]);

            RecipientDashboardPage recipientDashboardPage= new RecipientDashboardPage(driver);
            logger.info("Capturing recipient's current MailId...");
            String oldEmail = recipientDashboardPage.getRecipientEmail();
            logger.info("Current Email: " + oldEmail);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            boolean isEmailFieldEditable = recipientEditProfilePage.isEmailFieldEditable();
            Assert.assertTrue(isEmailFieldEditable,"Email field is not editable...");

            String existingUserEmail = recipientData1[1];

            logger.info("Updating recipient's Email to existing User's Email: " + existingUserEmail);
            recipientEditProfilePage.setRecipientEmail(existingUserEmail);
            //Thread.sleep(3000);


            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing Email from profile...");
            String actualEmail = recipientDashboardPage.getRecipientEmail();

            logger.info("Verifying recipient Email was not updated successfully...");
            Assert.assertEquals(actualEmail, oldEmail, "Recipient was able to update email to an email address that already belongs to another user.");

            logger.info("SUCCESS: Recipient could not update to existing user's Email.");

        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during test execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail(" Exception encountered during test execution " + e.getMessage());
        }
    }

    @Test
    public void TC042_VerifyRecipientCannotUpdatePhoneNumberIfAlreadyBelongsToAnotherUser(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC042_VerifyRecipientCannotUpdatePhoneNumberIfAlreadyBelongsToAnotherUser");
        logger.info("=========================================================");

        try {
            logger.info("PHASE 1: Generating unique user dataset to seed the database...");
            String[] recipientData1 = registerUserHelper("recipient");

            logger.info("PHASE 2: Navigating back to the login screen to test duplicate constraint...");
            logger.info("Entering login credentials");

            loginUserHelper(recipientData[1],recipientData[2]);

            RecipientDashboardPage recipientDashboardPage= new RecipientDashboardPage(driver);
            logger.info("Capturing recipient's current Phone Number...");
            String oldPhoneno = recipientDashboardPage.getRecipientPhoneno();
            logger.info("Current Phone Number: " + oldPhoneno);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            boolean isPhoneFieldDisplayed = recipientEditProfilePage.isPhoneFieldDisplayed();
            Assert.assertTrue(isPhoneFieldDisplayed,"Phone Number field is not Displayed...");

            boolean isPhoneFieldEditable = recipientEditProfilePage.isPhoneFieldEditable();
            Assert.assertTrue(isPhoneFieldEditable,"Phone Number field is not editable...");

            String existingUserPhoneno = recipientData1[3];

            logger.info("Updating recipient's Phone Number to existing User's Phone Number: " + existingUserPhoneno);
            recipientEditProfilePage.setRecipientPhoneno(existingUserPhoneno);
            //Thread.sleep(3000);


            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing Phone Number from profile...");
            String actualPhoneno = recipientDashboardPage.getRecipientPhoneno();

            logger.info("Verifying recipient Email was not updated successfully...");
            Assert.assertEquals(actualPhoneno, oldPhoneno, "Recipient was able to update Phone Number to a Phone Number that already belongs to another user.");

            logger.info("SUCCESS: Recipient could not update to existing user's Phone Number.");

        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during test execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail(" Exception encountered during test execution " + e.getMessage());
        }
    }
    @Test
    public void TC043_VerifyRecipientCanUpdateLocation(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC043_VerifyRecipientCanUpdateLocation");
        logger.info("Target Account: [ " + recipientData[1] + " ]");
        logger.info("=========================================================");

        try {

            loginUserHelper(recipientData[1], recipientData[2]);

            RecipientDashboardPage recipientDashboardPage= new RecipientDashboardPage(driver);

            logger.info("Capturing current recipient Location...");
            String oldLocation = recipientDashboardPage.getRecipientLocation();
            logger.info("Current Location: " + oldLocation);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            boolean isLocationFieldDisplayed = recipientEditProfilePage.isLocationFieldDisplayed();
            Assert.assertTrue(isLocationFieldDisplayed,"Location field is not Displayed...");

            boolean isLocationFieldEditable = recipientEditProfilePage.isLocationFieldEditable();
            Assert.assertTrue(isLocationFieldEditable,"Location field is not editable...");

            String[] updatedLocation = RandomDataGeneratorUtil.randomUserDataGenerator();

            logger.info("Updating recipient Location to: " + updatedLocation[9]);
            recipientEditProfilePage.setRecipientLocation(updatedLocation[9]);

            logger.info("Clicking Save Changes button...");
            recipientEditProfilePage.clickSaveChanges();

            logger.info("Capturing updated Location from profile...");
            String actualName = recipientDashboardPage.getRecipientLocation();

            logger.info("Verifying recipient name was updated successfully...");
            Assert.assertEquals(actualName, updatedLocation[9], "Recipient could not update Location as required by SRS 4.1.");

            logger.info("SUCCESS: Recipient Location updated successfully.");
        }
        catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during test execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Recipient could not update Location as required by SRS 4.1." + e.getMessage());
        }
    }
}
