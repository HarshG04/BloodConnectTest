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
            recipientEditProfilePage.setRecipientBloodType(updatedBloodType[7]);
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
    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC040_VerifyRecipientCanUpdateContactDetails(String email, String password){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC040_VerifyRecipientCanUpdateContactDetails");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        RecipientDashboardPage recipientDashboardPage=null;
        try {

            loginAsRecipient(email, password);

            recipientDashboardPage= new RecipientDashboardPage(driver);

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


    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC041_VerifyRecipientCannotUpdateEmailIfAlreadyBelongsToAnotherUser(String email, String password){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC041_VerifyRecipientCannotUpdateEmailIfAlreadyBelongsToAnotherUser");
        logger.info("=========================================================");

        RecipientDashboardPage recipientDashboardPage=null;
        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            logger.info("PHASE 1: Generating unique user dataset to seed the database...");
            logger.info("Unselecting the default 'Donor' checkbox option...");
            registerPage.clickDonor();  // unSelect donor

            logger.info("Selecting the 'Recipient' checkbox option...");
            registerPage.clickRecipient();
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            logger.info("Target email to seed: '" + userData[1] + "'");

            logger.info("Populating form fields for the initial baseline user...");
            RandomDataGeneratorUtil.submitUserData(registerPage, userData);

            logger.info("Clicking on the Agree Terms checkbox...");
            registerPage.clickAgreeTerms();

            logger.info("Submitting the initial registration...");
            registerPage.clickRegister();

            String alertMsg = registerPage.getAlertMessage();
            logger.info("Initial registration system response: '" + alertMsg + "'");

            logger.info("Verifying baseline user registered successfully...");
            Assert.assertEquals(alertMsg, "User Registered Successfully", "Baseline registration failed!");


            logger.info("PHASE 2: Navigating back to the login screen to test duplicate constraint...");
            logger.info("Entering login credentials");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForUrlToContain("/login");
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin();

            recipientDashboardPage= new RecipientDashboardPage(driver);
            logger.info("Capturing recipient's current MailId...");
            String oldEmail = recipientDashboardPage.getRecipientEmail();
            logger.info("Current Email: " + oldEmail);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            String existingUserEmail = userData[1];

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

    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC042_VerifyRecipientCannotUpdatePhoneNumberIfAlreadyBelongsToAnotherUser(String email, String password){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC042_VerifyRecipientCannotUpdatePhoneNumberIfAlreadyBelongsToAnotherUser");
        logger.info("=========================================================");

        RecipientDashboardPage recipientDashboardPage=null;
        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            logger.info("PHASE 1: Generating unique user dataset to seed the database...");
            logger.info("Unselecting the default 'Donor' checkbox option...");
            registerPage.clickDonor();  // unSelect donor

            logger.info("Selecting the 'Recipient' checkbox option...");
            registerPage.clickRecipient();
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            logger.info("Target email to seed: '" + userData[1] + "'");

            logger.info("Populating form fields for the initial baseline user...");
            RandomDataGeneratorUtil.submitUserData(registerPage, userData);

            logger.info("Clicking on the Agree Terms checkbox...");
            registerPage.clickAgreeTerms();

            logger.info("Submitting the initial registration...");
            registerPage.clickRegister();

            String alertMsg = registerPage.getAlertMessage();
            logger.info("Initial registration system response: '" + alertMsg + "'");

            logger.info("Verifying baseline user registered successfully...");
            Assert.assertEquals(alertMsg, "User Registered Successfully", "Baseline registration failed!");


            logger.info("PHASE 2: Navigating back to the login screen to test duplicate constraint...");
            logger.info("Entering login credentials");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForUrlToContain("/login");
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin();

            recipientDashboardPage= new RecipientDashboardPage(driver);
            logger.info("Capturing recipient's current Phone Number...");
            String oldPhoneno = recipientDashboardPage.getRecipientPhoneno();
            logger.info("Current Phone Number: " + oldPhoneno);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

            String existingUserPhoneno = userData[3];

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
    @Test(
            dataProvider = "recipientLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void TC043_VerifyRecipientCanUpdateLocation(String email, String password){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC043_VerifyRecipientCanUpdateLocation");
        logger.info("Target Account: [ " + email + " ]");
        logger.info("=========================================================");

        RecipientDashboardPage recipientDashboardPage=null;
        try {

            loginAsRecipient(email, password);

            recipientDashboardPage= new RecipientDashboardPage(driver);

            logger.info("Capturing current recipient Location...");
            String oldLocation = recipientDashboardPage.getRecipientLocation();
            logger.info("Current Location: " + oldLocation);

            logger.info("Clicking Edit Profile button...");
            recipientDashboardPage.clickEdit();

            RecipientEditProfilePage recipientEditProfilePage=new RecipientEditProfilePage(driver);

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
}
