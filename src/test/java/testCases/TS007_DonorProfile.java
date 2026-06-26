package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.*;
import testBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS007_DonorProfile extends BaseClass {


    private String[] donorData;

    @BeforeMethod
    public void RegisterNewUser(){
        if(donorData==null) donorData = registerUserHelper("donor");
    }


    @Test(priority = 1,groups = "donor")
    public void TC019_updateDonorName() {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC019_updateDonorName");
        logger.info("=========================================================");


        try {
            loginUserHelper(donorData[1], donorData[2]);
            String name = RandomStringUtils.randomAlphabetic(5);

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

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC019_updateDonorName");
        logger.info("=========================================================");
    }

    @Test(priority = 2,groups = "donor")
    public void TC020_updateDonorBloodGroup() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC020_updateDonorBloodGroup");
        logger.info("=========================================================");

        try {
            loginUserHelper(donorData[1], donorData[2]);
            String bloodGroup = RandomDataGeneratorUtil.randomBloodGroupGenerator(donorData[7]);
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

            logger.info("Updating Blood Group to: " + bloodGroup);
            donorProfile.selectBloodGrp(bloodGroup);


            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Verifying navigation to Dashboard after save");
            Assert.assertTrue(donorProfile.waitForUrlToContain("/donor/dashboard"),
                    "Profile not saved or navigation failed");

            logger.info("Validating donor blood group update");

            String actualBldGrp = donorDash.getDonorBloodGrp();

            logger.info("Expected: {}", bloodGroup);
            logger.info("Actual: {}", actualBldGrp);

            Assert.assertEquals(actualBldGrp, bloodGroup,
                    "Donor blood group update validation failed");
            logger.info("TEST CASE PASSED ");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC020_updateDonorBloodGroup");
        logger.info("=========================================================");

    }

    @Test(priority = 3,groups = "donor")
    public void TC021_updateDonorContactDetails(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC021_updateDonorContactDetails");
        logger.info("=========================================================");


        try {
            loginUserHelper(donorData[1], donorData[2]);
            String newEmail = RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@gmail.com";
            String newPhno = RandomStringUtils.randomNumeric(10);
            logger.info("new email {}, new phone {}",newEmail,newPhno);

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

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }

        logger.info("=========================================================");
        logger.info("ENDING TEST CASE: TC021_updateDonorContactDetails");
        logger.info("=========================================================");
    }

    @Test(priority = 4,groups = "donor")
    public void TC022_VerifyDonorCannotUpdateEmailToExistingEmail() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC022_VerifyDonorCannotUpdateEmailToExistingEmail");
        logger.info("=========================================================");

        try {
            String[] userData1 = registerUserHelper("donor");
            loginUserHelper(donorData[1], donorData[2]);

            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();
            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            logger.info("Checking if Email field is editable");

            Assert.assertTrue(donorProfile.isEmailEditable(),
                    "Email field is NOT editable");

            logger.info("Updating Email to: " + userData1[1]);
            donorProfile.enterEmail(userData1[1]);

            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Validating duplicate email behavior...");

            // Case 1: If it navigates to dashboard → BUG → FAIL TEST
            boolean isRedirected = donorProfile.waitForUrlToContain("/donor/dashboard");

            if (isRedirected) {
                logger.error("BUG: Duplicate email was accepted and profile updated!");
                Assert.fail("Test FAILED: System allowed duplicate email update");
                donorDash.logoutUser();
            }
            // Case 2: Check error message (expected behavior)
            else {
                logger.info("Duplicate email correctly rejected");
                Assert.assertTrue(true);
            }

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }

            logger.info("=========================================================");
            logger.info("ENDING TEST CASE: TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber");
            logger.info("=========================================================");

    }

    @Test(priority = 5,groups = "donor")
    public void TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber");
        logger.info("=========================================================");

        try{
            String[] userData1 = registerUserHelper("donor");
            loginUserHelper(donorData[1], donorData[2]);
            DonorDashboardPage donorDash = new DonorDashboardPage(driver);
            donorDash.clickEditProfile();

            logger.info("Verifying Edit Profile page load");
            Assert.assertTrue(donorDash.waitForUrlToContain("/edit-profile"),
                    "Edit Profile page not loaded");

            DonorProfilePage donorProfile = new DonorProfilePage(driver);

            logger.info("Checking if Phone No. field is editable");

            Assert.assertTrue(donorProfile.isPhoneNoEditable(),
                    "Phone No. field is NOT editable");

            logger.info("Updating Phone No. to: " + userData1[3]);
            donorProfile.enterPhoneno(userData1[3]);

            logger.info("Clicking Save Changes");
            donorProfile.clickSaveChanges();

            logger.info("Validating duplicate phone number behavior...");

            // Case 1: If it navigates to dashboard → BUG → FAIL TEST
            boolean isRedirected = donorProfile.waitForUrlToContain("/donor/dashboard");

            if (isRedirected) {
                logger.error("BUG: Duplicate phone number was accepted and profile updated!");
                Assert.fail("Test FAILED: System allowed duplicate phone number update");
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
            logger.info("=========================================================");
            logger.info("ENDING TEST CASE: TC023_VerifyDonorCannotUpdatePhoneNumberToExistingUserNumber");
            logger.info("=========================================================");


    }

    @Test(priority = 6,groups = "donor")
    public void TC024_updateDonorLocation(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC024_updateDonorLocation");
        logger.info("=========================================================");

        try{
            loginUserHelper(donorData[1], donorData[2]);
            String newLocation = RandomStringUtils.randomAlphabetic(5);

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

//            logger.info("Validating donor Location update");
//
//            String actualLocation = donorDash.getDonorLocation();
//
//            logger.info("Expected: {}", newLocation);
//            logger.info("Actual: {}", actualLocation);
//
//            Assert.assertEquals(actualLocation, newLocation,
//                    "Donor Location update validation failed");
            logger.info("TEST CASE PASSED ");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED : " + ae.getMessage());
            throw ae; // rethrow to mark test as FAILED

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED : " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception");
        }
            logger.info("=========================================================");
            logger.info("ENDING TEST CASE: TC024_updateDonorLocation");
            logger.info("=========================================================");

    }

}