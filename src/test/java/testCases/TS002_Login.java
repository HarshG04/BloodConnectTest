package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TS002_Login extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = LoginDataProvider.class)
    public void verifyLogin(String email, String password, String expectedResult) {

        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TS002_Login.verifyLogin");
        logger.info("Target Account: [ " + email +" ] | Expected Profile: " + expectedResult);
        logger.info("=========================================================");

        try {
            boolean isLoggedIn = loginUserHelper(email, password);

            if (expectedResult.equalsIgnoreCase("Valid")) {
                if (isLoggedIn) {
                    logger.info("SUCCESS: Logged in successfully with Valid credentials as expected.");
                    new DashboardPage(driver).logoutUser();
                    logger.info("Logged out...");
                } else {
                    logger.error("ASSERTION FAILED: Valid Credentials Not LoggedIn!");
                    Assert.fail("Failed to login with valid credentials: " + email+":"+password);
                }
            }
            else if (expectedResult.equalsIgnoreCase("Invalid")) {
                if (!isLoggedIn) {
                    logger.info("Not able to logIn with Invalid Credentials.");

                    logger.info("Getting Alert Message...");
                    String actualAlert = new LoginPage(driver).getAlertMessage();
                    logger.info("Alert Message: '" + actualAlert + "'");

                    Assert.assertEquals(actualAlert, "Bad credentials");
                } else {
                    logger.error("logged into dashboard with Invalid credentials!");
                    new DashboardPage(driver).logoutUser();
                    logger.info("Logged out...");

                    Assert.fail("Able to logIn into dashboard with Invalid credentials!" + email+":"+password);
                }
            }

            logger.info("SUCCESS: Data iteration row processed successfully.");
        }
        catch(AssertionError ae) {
            logger.error("Assertion Failed: " + ae.getMessage());
            throw ae;
        }
        catch(Exception e) {
            logger.error("Exception: " + e.getMessage());
            Assert.fail("Exception: " + e.getMessage());
        }
    }
}