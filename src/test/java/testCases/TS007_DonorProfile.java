package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorDashboardPage;
import pageObjects.DonorProfilePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TS007_DonorProfile extends BaseClass {

    @Test(priority = 1, dataProvider = "DonorNameUpdate", dataProviderClass = LoginDataProvider.class)
    public void TC0019_updateDonorName(String email, String pwd, String name) {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC0019_updateDonorName");
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

            logger.info("Step 10: Verifying navigation to Dashboard after save");
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
        logger.info("ENDING TEST CASE: TC0019_updateDonorName");
        logger.info("=========================================================");
    }
}