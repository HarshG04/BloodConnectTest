package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TS002_Login extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = LoginDataProvider.class)
    public void verifyLogin(String email, String password, String expectedResult) {

        logger.info("=========================================================");
        logger.info("STARTING DATA ROW EXECUTION: Login Verification");
        logger.info("Target Account: [ " + email +": " + password + " ] | Expected Profile: " + expectedResult);
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to Login page from Home Page...");
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Entering email and password ...");
            loginPage.setEmail(email);
            loginPage.setPassword(password);

            logger.info("Submitting login form...");
            loginPage.clickLogin();

            logger.info("Waiting to verify if target dashboard URL loads...");
            boolean isLoggedIn = loginPage.waitForUrlToContain("/dashboard");


            if (expectedResult.equalsIgnoreCase("Valid")) {
                if (isLoggedIn) {
                    logger.info("SUCCESS: Logged in successfully with Valid credentials as expected.");

                    DashboardPage dashboardPage = new DashboardPage(driver);
                    logger.info("Logging out...");
                    dashboardPage.clickUserDropDown();
                    dashboardPage.clickLogout();
                } else {
                    logger.error("ASSERTION FAILED: Valid Credentials Not LoggedIn!");
                    Assert.fail("Failed to login with valid credentials: " + email+":"+password);
                }
            }
            else if (expectedResult.equalsIgnoreCase("Invalid")) {
                if (!isLoggedIn) {
                    logger.info("Not able to logIn with Invalid Credentials.");

                    logger.info("Getting Alert Message...");
                    String actualAlert = loginPage.getAlertMessage();
                    logger.info("Alert Message: '" + actualAlert + "'");

                    Assert.assertEquals(actualAlert, "Bad credentials");
                } else {
                    logger.error("logged into dashboard with Invalid credentials!");

                    DashboardPage dashboardPage = new DashboardPage(driver);
                    dashboardPage.clickUserDropDown();
                    dashboardPage.clickLogout();

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