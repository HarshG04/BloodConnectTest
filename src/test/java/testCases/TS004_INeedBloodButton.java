package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RecipientDashboardPage;
import testBase.BaseClass;

public class TS004_INeedBloodButton extends BaseClass {

    @Test(priority = 1)
    public void TC012_verifyINeedButtonForUnauthenticatedUsers(){
        logger.info("=========================================================");
        logger.info("STARTING TEST: TC012_verifyINeedButtonUnauthenticatedUsers");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Clicking on 'I Need Blood' button as a guest user...");
            homePage.clickINeedBlood();

            logger.info("Checking if the guest user is redirected to the Login page...");
            boolean isNavigatedToLoginPage = homePage.waitForUrlToContain("/login");

            Assert.assertTrue(isNavigatedToLoginPage, "Guest user was not redirected to the Login page!");
            logger.info("SUCCESS: Guest user was successfully redirected to the Login page.");
        }
        catch(AssertionError ae){
            logger.error("TEST FAILED (Assertion): " + ae.getMessage());
            throw ae;
        }
        catch (Exception e){
            logger.error("TEST CRASHED (Exception): " + e.getMessage());
            Assert.fail("Test failed due to a system exception: " + e.getMessage());
        }
    }


    @Test(dataProvider = "recipientLoginData", dataProviderClass = LoginDataProvider.class, priority = 2)
    public void TC013_verifyINeedButtonForAuthenticatedUsers(String email, String password){
        logger.info("=========================================================");
        logger.info("STARTING TEST: TC013_verifyINeedButtonAuthenticatedUsers");
        logger.info("Testing with account: " + email+" : "+password);
        logger.info("=========================================================");

        try {
            LoginUserHelper(email, password);

            logger.info("Going back to the Home page from the dashboard...");
            new RecipientDashboardPage(driver).clickHome();

            HomePage homePage = new HomePage(driver);
            logger.info("Clicking on 'I Need Blood' button as a logged-in user...");
            homePage.clickINeedBlood();

            logger.info("Checking if the user goes directly back to their dashboard without logging in again...");
            boolean isNavigated = homePage.waitForUrlToContain("/dashboard");

            Assert.assertTrue(isNavigated, "Logged-in user was blocked from accessing their dashboard!");
            logger.info("SUCCESS: Logged-in user bypassed login page and accessed dashboard.");

        }
        catch(AssertionError ae){
            logger.error("Assertion FAILED Assertion: " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("TEST Exception: " + e.getMessage());
            Assert.fail("Test failed due to a exception: " + e.getMessage());
        }
    }
}