package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;
import java.util.Random;

public class TS001_Registration extends BaseClass {

    @Test(priority = 1,groups = {"auth","smoke"})
    public void TC001_verifyDonorRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC001_verifyDonorRegistration");
        logger.info("=========================================================");

        try {
            registerUserHelper("donor");
            logger.info("SUCCESS: TC001_verifyDonorRegistration passed successfully!");
        }
        catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC001 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 2,groups = {"auth","smoke"})
    public void TC002_verifyRecipientRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC002_verifyRecepientRegistration");
        logger.info("=========================================================");

        try {
            registerUserHelper("recipient");
            logger.info("SUCCESS: TC002_verifyRecepientRegistration passed successfully!");
        }
        catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC002 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 3,groups = {"auth","sanity"})
    public void TC003_verifyBothRegistration(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC003_verifyBothRegistration");
        logger.info("=========================================================");

        try {
            registerUserHelper("both");
            logger.info("SUCCESS: TC003_verifyBothRegistration passed successfully!");
        }
        catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC003 execution!", e);
            Assert.fail();
        }
    }

    @Test(priority = 4,groups = {"auth","sanity"})
    public void TC004_verifyIncorrectEmailRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC004_verifyIncorrectEmailRegistration");
        logger.info("=========================================================");

        try {
            logger.info("Generating baseline data and overriding email field with an invalid format...");
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData[1] = "abcdef";
            logger.info("Malformed email string injected into test array: '" + userData[1] + "'");
            registerUserHelper(userData,"donor",true);

            logger.info("Fetching inline email validation error message from the UI page...");
            String errorMsg = new RegisterPage(driver).getEmailErrorMessage();
            logger.info("Actual error message text captured from UI: '" + errorMsg + "'");

            logger.info("Verifying error message matches the expected format validation rule...");
            Assert.assertEquals(errorMsg, "Please enter a valid email");

            logger.info("SUCCESS: TC004_verifyIncorrectEmailRegistration passed successfully!");
        }
        catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the error back up to TestNG so it fails gracefully in the report
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC004 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 5,groups = {"auth","sanity"})
    public void TC005_verifyDuplicateEmailRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC005_verifyDuplicateEmailRegistration");
        logger.info("=========================================================");

        try {

            // --- PHASE 1: Create a unique user baseline ---
            logger.info("PHASE 1: Adding new user to the database ...");
            String[] userData = registerUserHelper("donor");
            logger.info("Target email to seed: '" + userData[1] + "'");


            // --- PHASE 2: Navigate back and try to duplicate the email ---
            logger.info("PHASE 2: Navigating back to the registration screen to test duplicate constraint...");
            new LoginPage(driver).clickHome();

            logger.info("Generating a brand new user dataset, but injecting the duplicate email address...");
            String[] userData1 = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData1[1] = userData[1]; // Overriding new email with the first user's email

            logger.info("trying to register with the duplicate email...");
            registerUserHelper(userData1,"donor",true);

            logger.info("Fetching duplicate error conflict alert message from the UI...");
            String alertMessage = new RegisterPage(driver).getAlertMessage();
            logger.info("Actual error response captured: '" + alertMessage + "'");

            logger.info("Verifying system blocks registration and displays the correct duplicate error message...");
            Assert.assertEquals(alertMessage, "Email already registered");

            logger.info("SUCCESS: TC005_verifyDuplicateEmailRegistration passed successfully!");
        }
        catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Ensures TestNG logs the failure perfectly in the HTML reports
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC005 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 6,groups = {"auth","sanity"})
    public void TC006_verifyDuplicatePhoneRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC006_verifyDuplicatePhoneRegistration");
        logger.info("=========================================================");

        try {
            // --- PHASE 1: Create a unique user baseline ---
            logger.info("PHASE 1: Adding new user to the database ...");
            String[] userData = registerUserHelper("donor");
            logger.info("Target phone number to seed: '" + userData[3] + "'");


            // --- PHASE 2: Navigate back and try to duplicate the phone number ---
            logger.info("PHASE 2: Navigating back to the registration screen to test duplicate constraint...");
            new LoginPage(driver).clickHome();

            logger.info("Generating a brand new user dataset, but injecting the duplicate phone number...");
            String[] userData1 = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData1[3] = userData[3];

            logger.info("trying to register with the duplicate email...");
            registerUserHelper(userData1,"donor",true);

            logger.info("Fetching duplicate error conflict alert message from the UI...");
            String alertMessage = new RegisterPage(driver).getAlertMessage();
            logger.info("Actual error response captured: '" + alertMessage + "'");

            logger.info("Verifying system blocks registration and displays the correct duplicate error message...");
            Assert.assertEquals(alertMessage, "Phone already registered");

            logger.info("SUCCESS: TC006_verifyDuplicatePhoneRegistration passed successfully!");
        }
        catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the assertion failure directly to TestNG for reporting
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC006 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

}
