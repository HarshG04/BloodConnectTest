package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS007_DonorProfile extends BaseClass {

    @Test(priority = 1, dataProvider = "DonorNameUpdate", dataProviderClass = LoginDataProvider.class)
    public void TC019_updateDonorName(String email, String pwd, String name) {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC019_updateDonorName");
        logger.info("=========================================================");


        try {
            logger.info("Navigating to Login Page");
            HomePage homePage = new HomePage(driver);
            homePage.clickLogin();

            logger.info("Entering login credentials");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(pwd);

            logger.info("Clicking Login button");
            loginPage.clickLogin();

            logger.info("Verifying login success (Dashboard page)");
            Assert.assertTrue(homePage.waitForUrlToContain("/donor/dashboard"),
                    "Login failed: Dashboard URL not loaded");

            logger.info("Navigating to Edit Profile page");
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();

            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            logger.info("Checking if Full Name field is editable");
            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            Assert.assertTrue(donorProfile.isFullNameEditable(),
                    "Full Name field is NOT editable");

            logger.info("Updating Full Name to: " + name);
            donorProfile.enterFullName(name);

            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Verifying navigation to Dashboard after save");
            Assert.assertTrue(donorProfile.waitForUrlToContain("/donor/dashboard"),
                    "Profile not saved or navigation failed");

            logger.info("Validating donor name update");

            String actualName = donorDash.getDonorName();

            logger.info("Expected: {}", name);
            logger.info("Actual: {}", actualName);

            Assert.assertEquals(actualName, name,
                    "Donor name update validation failed");
            logger.info("TEST CASE PASSED ");
            donorDash.clickUserDropDown();
            donorDash.clickLogout();

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }

        finally {
            try {
                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
                logger.info("Logged out in finally block");
            } catch (Exception e) {
                logger.warn("Logout skipped: " + e.getMessage());
            }
        }


        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC019_updateDonorName");
        logger.info("=========================================================");
    }

    @Test(priority = 2, dataProvider = "DonorBloodGrpUpdate",dataProviderClass = LoginDataProvider.class)
    public void TC020_updateDonorBloodGroup(String email,String pwd,String bd) {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC020_updateDonorBloodGroup");
        logger.info("=========================================================");


        try {
            logger.info("Navigating to Login Page");
            HomePage homePage = new HomePage(driver);
            homePage.clickLogin();

            logger.info("Entering login credentials");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(pwd);

            logger.info("Clicking Login button");
            loginPage.clickLogin();

            logger.info("Verifying login success (Dashboard page)");
            Assert.assertTrue(homePage.waitForUrlToContain("/donor/dashboard"),
                    "Login failed: Dashboard URL not loaded");

            logger.info("Navigating to Edit Profile page");
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();

            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            logger.info("Checking if Blood Group field is editable");
            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            Assert.assertTrue(donorProfile.isBloodGroupEditable(),
                    "Blood Group field is NOT editable");

            logger.info("Updating Blood Group to: " + bd);
            donorProfile.selectBloodGrp(bd);


            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Verifying navigation to Dashboard after save");
            Assert.assertTrue(donorProfile.waitForUrlToContain("/donor/dashboard"),
                    "Profile not saved or navigation failed");

            logger.info("Validating donor blood group update");

            String actualBldGrp = donorDash.getDonorBloodGrp();

            logger.info("Expected: {}", bd);
            logger.info("Actual: {}", actualBldGrp);

            Assert.assertEquals(actualBldGrp, bd,
                    "Donor blood group update validation failed");
            logger.info("TEST CASE PASSED ");
            donorDash.clickUserDropDown();
            donorDash.clickLogout();

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        finally {
            try {
                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
                logger.info("Logged out in finally block");
            } catch (Exception e) {
                logger.warn("Logout skipped: " + e.getMessage());
            }
        }

        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC020_updateDonorBloodGroup");
        logger.info("=========================================================");

    }

    @Test(priority = 3, dataProvider = "DonorContactUpdate", dataProviderClass = LoginDataProvider.class)
    public void TC021_updateDonorContactDetails(String email,String pwd,String newEmail,String newPhno){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC021_updateDonorContactDetails");
        logger.info("=========================================================");


        try {
            logger.info("Navigating to Login Page");
            HomePage homePage = new HomePage(driver);
            homePage.clickLogin();

            logger.info("Entering login credentials");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(pwd);

            logger.info("Clicking Login button");
            loginPage.clickLogin();

            logger.info("Verifying login success (Dashboard page)");
            Assert.assertTrue(homePage.waitForUrlToContain("/donor/dashboard"),
                    "Login failed: Dashboard URL not loaded");

            logger.info("Navigating to Edit Profile page");
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();

            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            logger.info("Checking if Email field is editable");
            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            Assert.assertTrue(donorProfile.isEmailEditable(),
                    "Email field is NOT editable");

            logger.info("Updating Email to: " + newEmail);
            donorProfile.enterEmail(newEmail);

            logger.info("Checking if Phone No. field is editable");

            Assert.assertTrue(donorProfile.isPhoneNoEditable(),
                    "Phone No. field is NOT editable");

            logger.info("Updating Phone No. to: " + newPhno);
            donorProfile.enterPhoneno(newPhno);

            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Verifying navigation to Dashboard after save");
            Assert.assertTrue(donorProfile.waitForUrlToContain("/donor/dashboard"),
                    "Profile not saved or navigation failed");

            logger.info("Validating donor contact update");

            String actualEmail = donorDash.getDonorEmail();
            String actualPhone = donorDash.getDonorPhone();

            logger.info("Expected: {}{}", newEmail,newPhno);
            logger.info("Actual: {}{}", actualEmail,actualPhone);

            Assert.assertEquals(actualEmail, newEmail,
                    "Donor email update validation failed");
            Assert.assertEquals(actualPhone,newPhno,"Donor phone no. update validation failed");
            logger.info("TEST CASE PASSED ");
            donorDash.clickUserDropDown();
            donorDash.clickLogout();

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        finally {
            try {
                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
                logger.info("Logged out in finally block");
            } catch (Exception e) {
                logger.warn("Logout skipped: " + e.getMessage());
            }
        }

        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC021_updateDonorContactDetails");
        logger.info("=========================================================");
    }

    @Test(priority = 4,dataProvider = "donorLoginData",dataProviderClass = LoginDataProvider.class)
    public void TC022_VerifyDonorCannotUpdateEmailToExistingEmail(String email,String pwd){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC022_VerifyDonorCannotUpdateEmailToExistingEmail");
        logger.info("=========================================================");

        try{
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
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

            logger.info("PHASE 2: Navigating back to the login screen to test duplicate constraint...");
            logger.info("Entering login credentials");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForUrlToContain("/login");
            loginPage.setEmail(email);
            loginPage.setPassword(pwd);

            logger.info("Clicking Login button");
            loginPage.clickLogin();

            logger.info("Verifying login success (Dashboard page)");
            Assert.assertTrue(homePage.waitForUrlToContain("/donor/dashboard"),
                    "Login failed: Dashboard URL not loaded");

            logger.info("Navigating to Edit Profile page");
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();

            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            logger.info("Checking if Email field is editable");

            Assert.assertTrue(donorProfile.isEmailEditable(),
                    "Email field is NOT editable");

            logger.info("Updating Email to: " + userData[1]);
            donorProfile.enterEmail(userData[1]);

            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Validating duplicate email behavior...");

            // Case 1: If it navigates to dashboard → BUG → FAIL TEST
            boolean isRedirected = donorProfile.waitForUrlToContain("/donor/dashboard");

            if (isRedirected) {
                logger.error("BUG: Duplicate phone number was accepted and profile updated!");
                Assert.fail("Test FAILED: System allowed duplicate phone number update");
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
            }
            // Case 2: Check error message (expected behavior)
            else{
                logger.info("Duplicate phone number correctly rejected");
                Assert.assertTrue(true);
            }

        }catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        finally {
            try {
                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
                logger.info("Logged out in finally block");
            } catch (Exception e) {
                logger.warn("Logout skipped: " + e.getMessage());
            }
            logger.info("=========================================================");
            logger.info("ENDING TEST CASE: TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber");
            logger.info("=========================================================");
        }
    }

    @Test(priority = 5,dataProvider = "donorLoginData",dataProviderClass = LoginDataProvider.class)
    public void TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber(String email,String pwd){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber");
        logger.info("=========================================================");

        try{
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Registration page from Home page...");
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
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

            logger.info("PHASE 2: Navigating back to the login screen to test duplicate constraint...");
            logger.info("Entering login credentials");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForUrlToContain("/login");
            loginPage.setEmail(email);
            loginPage.setPassword(pwd);

            logger.info("Clicking Login button");
            loginPage.clickLogin();

            logger.info("Verifying login success (Dashboard page)");
            Assert.assertTrue(homePage.waitForUrlToContain("/donor/dashboard"),
                    "Login failed: Dashboard URL not loaded");

            logger.info("Navigating to Edit Profile page");
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();

            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            logger.info("Checking if Phone No. field is editable");

            Assert.assertTrue(donorProfile.isPhoneNoEditable(),
                    "Phone No. field is NOT editable");

            logger.info("Updating Phone No. to: " + userData[3]);
            donorProfile.enterPhoneno(userData[3]);

            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Validating duplicate phone number behavior...");

            // Case 1: If it navigates to dashboard → BUG → FAIL TEST
            boolean isRedirected = donorProfile.waitForUrlToContain("/donor/dashboard");

            if (isRedirected) {
                logger.error("BUG: Duplicate phone number was accepted and profile updated!");
                Assert.fail("Test FAILED: System allowed duplicate phone number update");
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
            }
            // Case 2: Check error message (expected behavior)
            else{
                logger.info("Duplicate phone number correctly rejected");
                Assert.assertTrue(true);
            }

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        finally {
            try {
                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
                logger.info("Logged out in finally block");
            } catch (Exception e) {
                logger.warn("Logout skipped: " + e.getMessage());
            }
            logger.info("=========================================================");
            logger.info("ENDING TEST CASE: TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber");
            logger.info("=========================================================");
        }

    }

    @Test(priority = 6,dataProvider = "DonorLocationUpdate",dataProviderClass = LoginDataProvider.class)
    public void TC024_updateDonorLocation(String email,String pwd,String newLocation){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC024_updateDonorLocation");
        logger.info("=========================================================");

        try{
            logger.info("Navigating to Login Page");
            HomePage homePage = new HomePage(driver);
            homePage.clickLogin();

            logger.info("Entering login credentials");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(pwd);

            logger.info("Clicking Login button");
            loginPage.clickLogin();

            logger.info("Verifying login success (Dashboard page)");
            Assert.assertTrue(homePage.waitForUrlToContain("/donor/dashboard"),
                    "Login failed: Dashboard URL not loaded");

            logger.info("Navigating to Edit Profile page");
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();

            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            logger.info("Checking if Location field is editable");
            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            Assert.assertTrue(donorProfile.isLocationEditable(),
                    "Location field is NOT editable");

            logger.info("Updating Location to: " + newLocation);
            donorProfile.enterLocation(newLocation);


            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Verifying navigation to Dashboard after save");
            Assert.assertTrue(donorProfile.waitForUrlToContain("/donor/dashboard"),
                    "Profile not saved or navigation failed");

            logger.info("Validating donor Location update");

            String actualLocation = donorDash.getDonorLocation();

            logger.info("Expected: {}", newLocation);
            logger.info("Actual: {}", actualLocation);

            Assert.assertEquals(actualLocation, newLocation,
                    "Donor Location update validation failed");
            logger.info("TEST CASE PASSED ");
            donorDash.clickUserDropDown();
            donorDash.clickLogout();
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
        finally {
            try {
                DonorDashboardPage donorDash = new DonorDashboardPage(driver);
                donorDash.clickUserDropDown();
                donorDash.clickLogout();
                logger.info("Logged out in finally block");
            } catch (Exception e) {
                logger.warn("Logout skipped: " + e.getMessage());
            }
            logger.info("=========================================================");
            logger.info("ENDING TEST CASE: TC024_updateDonorLocation");
            logger.info("=========================================================");
        }
    }

}