package testCases;

import DataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DonorRecepientPage;
import testBase.BaseClass;

public class TS011_SwitchViewWorks extends BaseClass {

    @Test(dataProvider="bothLoginData", dataProviderClass = LoginDataProvider.class)
    public void TC034_SwitchToRecipient(String email, String password){
        logger.info("Starting TC34: Switching view to Recipient perspective");

        try {
            LoginUserHelper(email, password);
            DonorRecepientPage donorRecepientPage = new DonorRecepientPage(driver);

            // Switch perspective cleanly
            donorRecepientPage.clickSwitchView("recipient");

            String actualHeading = donorRecepientPage.getHeading();
            logger.info("Retrieved heading text: " + actualHeading);

            Assert.assertEquals(actualHeading, "Recipient Dashboard", "Failed to switch context to Recipient view!");
            logger.info("TC34 passed successfully");

        } catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the assertion failure directly to TestNG for reporting
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC034 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(dataProvider="bothLoginData", dataProviderClass = LoginDataProvider.class)
    public void TC35_SwitchToDonor(String email, String password){
        logger.info("Starting TC35: Testing round-trip perspective switch to Donor");

        try {
            LoginUserHelper(email, password);
            DonorRecepientPage donorRecepientPage = new DonorRecepientPage(driver);

            // Toggle states sequence loop
            donorRecepientPage.clickSwitchView("recipient");
            donorRecepientPage.clickSwitchView("donor");

            String actualHeading = donorRecepientPage.getHeading();
            logger.info("Retrieved heading text: " + actualHeading);

            Assert.assertEquals(actualHeading, "Donor Dashboard", "Failed to cycle context back to Donor view!");
            logger.info("TC35 passed successfully");

        } catch(AssertionError ae){
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae; // Pass the assertion failure directly to TestNG for reporting
        }
        catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC035 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}