package testCases;

import DataProviders.RegisterDataProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;
import java.util.Random;

public class TS001_Registration extends BaseClass {

    @Test(priority = 1)
    public void TC001_verifyDonorRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC001_verifyDonorRegistration");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);

            logger.info("Generating random user data and populating the registration form...");
            RandomDataGeneratorUtil.submitUserData(registerPage, RandomDataGeneratorUtil.randomUserDataGenerator());

            logger.info("Clicking on the Agree Terms...");
            registerPage.clickAgreeTerms();

            logger.info("Submitting the donor registration form...");
            registerPage.clickRegister();

            logger.info("Fetching registration alert confirmation message from the UI...");
            String alertMessage = registerPage.getAlertMessage();
            logger.info("Actual Alert Message captured: '" + alertMessage + "'");

            logger.info("Verifying alert message matches expected confirmation...");
            Assert.assertEquals(alertMessage, "User Registered Successfully");

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

    @Test(priority = 2)
    public void TC002_verifyRecepientRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC002_verifyRecepientRegistration");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);

            logger.info("Unselecting the default 'Donor' checkbox option...");
            registerPage.clickDonor();  // unSelect donor

            logger.info("Selecting the 'Recipient' checkbox option...");
            registerPage.clickRecipient();

            logger.info("Generating random user data and populating the registration form...");
            RandomDataGeneratorUtil.submitUserData(registerPage, RandomDataGeneratorUtil.randomUserDataGenerator());

            logger.info("Clicking on the Agree Terms...");
            registerPage.clickAgreeTerms();

            logger.info("Submitting the recipient registration form...");
            registerPage.clickRegister();

            logger.info("Fetching registration alert confirmation message from the UI...");
            String alertMessage = registerPage.getAlertMessage();
            logger.info("Actual Alert Message captured: '" + alertMessage + "'");

            logger.info("Verifying alert message matches expected confirmation...");
            Assert.assertEquals(alertMessage, "User Registered Successfully");

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

    @Test(priority = 3)
    public void TC003_verifyBothRegistration(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC003_verifyBothRegistration");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            logger.info("Selecting 'Recipient' option (keeping 'Donor' selected)...");
            registerPage.clickRecipient();

            logger.info("Populating dual-role profile form fields...");
            RandomDataGeneratorUtil.submitUserData(registerPage, RandomDataGeneratorUtil.randomUserDataGenerator());

            logger.info("Clicking on the Agree Terms...");
            registerPage.clickAgreeTerms();

            logger.info("Submitting the form...");
            registerPage.clickRegister();

            String alertMessage = registerPage.getAlertMessage();
            logger.info("Actual Alert Message captured: '" + alertMessage + "'");

            logger.info("Verifying alert message...");
            Assert.assertEquals(alertMessage, "User Registered Successfully");
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

    @Test(priority = 4)
    public void TC004_verifyIncorrectEmailRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC004_verifyIncorrectEmailRegistration");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);

            logger.info("Generating baseline data and overriding email field with an invalid format...");
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData[1] = "abcdef"; // Intentionally malformed email syntax (missing @ and domain)
            logger.info("Malformed email string injected into test array: '" + userData[1] + "'");

            logger.info("Populating form fields with the invalid email dataset...");
            RandomDataGeneratorUtil.submitUserData(registerPage, userData);

            // Note: Depending on your UI, you might need to click clickAgreeTerms()
            // or trigger a form blur/submit event here to fire the validation rules!
            logger.info("Clicking on the Agree Terms checkbox to trigger verification rules...");
            registerPage.clickAgreeTerms();

            logger.info("Fetching inline email validation error message from the UI page...");
            String errorMsg = registerPage.getEmailErrorMessage();
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

    @Test(priority = 5)
    public void TC005_verifyDuplicateEmailRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC005_verifyDuplicateEmailRegistration");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);

            // --- PHASE 1: Create a unique user baseline ---
            logger.info("PHASE 1: Generating unique user dataset to seed the database...");
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

            // --- PHASE 2: Navigate back and try to duplicate the email ---
            logger.info("PHASE 2: Navigating back to the registration screen to test duplicate constraint...");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForUrlToContain("/login");
            loginPage.clickRegister();

            // Re-instantiate the RegisterPage object to sync with the freshly loaded page layout
            RegisterPage registerPage2 = new RegisterPage(driver);

            logger.info("Generating a brand new user dataset, but injecting the duplicate email address...");
            String[] userData1 = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData1[1] = userData[1]; // Overriding new email with the first user's email

            logger.info("Populating form fields with the duplicate email dataset...");
            RandomDataGeneratorUtil.submitUserData(registerPage2, userData1);

            logger.info("Clicking on the Agree Terms checkbox for the duplicate attempt...");
            registerPage2.clickAgreeTerms();

            logger.info("Submitting the duplicate registration attempt...");
            registerPage2.clickRegister();

            logger.info("Fetching duplicate error conflict alert message from the UI...");
            String alertMessage = registerPage2.getAlertMessage();
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

    @Test(priority = 6)
    public void TC006_verifyDuplicatePhoneRegistration(){

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC006_verifyDuplicatePhoneRegistration");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);

            // --- PHASE 1: Create a unique user baseline ---
            logger.info("PHASE 1: Generating unique user dataset to seed the database...");
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            logger.info("Target phone number to seed: '" + userData[3] + "'");

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

            // --- PHASE 2: Navigate back and try to duplicate the phone number ---
            logger.info("PHASE 2: Navigating back to the registration screen to test duplicate constraint...");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForUrlToContain("/login");
            loginPage.clickRegister();

            // Re-instantiate the RegisterPage object to sync with the freshly loaded page layout
            RegisterPage registerPage2 = new RegisterPage(driver);

            String[] userData1 = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData1[3] = userData[3]; // Overriding new phone number with the first user's phone number

            logger.info("Populating form fields with the duplicate phone number dataset...");
            RandomDataGeneratorUtil.submitUserData(registerPage2, userData1);

            logger.info("Clicking on the Agree Terms checkbox for the duplicate attempt...");
            registerPage2.clickAgreeTerms();

            logger.info("Submitting the duplicate registration attempt...");
            registerPage2.clickRegister();

            logger.info("Fetching duplicate error conflict alert message from the UI...");
            String alertMessage = registerPage2.getAlertMessage();
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
