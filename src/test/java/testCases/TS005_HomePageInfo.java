package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS005_HomePageInfo extends BaseClass {

    @Test(groups = "home")
    public void TC014_WhyDonateBloodSectionVisibility(){
        logger.info("=========================================================");
        logger.info("STARTING TEST: TC014_WhyDonateBloodSectionVisibility");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Checking if the 'Why Donate Blood' section is visible on the Home page...");
            boolean isVisible = homePage.checkWhyDonateBloodVisibility();

            Assert.assertTrue(isVisible, "'Why Donate Blood' section is missing or hidden!");
            logger.info("SUCCESS: 'Why Donate Blood' section is visible.");
        }
        catch(AssertionError ae){
            logger.error("TEST FAILED (Assertion): " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("TEST CRASHED (Exception): " + e.getMessage());
            Assert.fail("Test failed due to a system exception: " + e.getMessage());
        }
    }

    @Test(groups = "home")
    public void TC015_BenefitsOfDonatingBloodSectionVisibility(){
        logger.info("=========================================================");
        logger.info("STARTING TEST: TC015_BenefitsOfDonatingBloodSectionVisibility");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Checking if the 'Benefits of Donating Blood' section is visible on the Home page...");
            boolean isVisible = homePage.checkBenefitsOfDonatingBloodVisibility();

            Assert.assertTrue(isVisible, "'Benefits of Donating Blood' section is missing or hidden!");
            logger.info("SUCCESS: 'Benefits of Donating Blood' section is visible.");
        }
        catch(AssertionError ae){
            logger.error("TEST FAILED (Assertion): " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("TEST CRASHED (Exception): " + e.getMessage());
            Assert.fail("Test failed due to a system exception: " + e.getMessage());
        }
    }

    @Test(groups = "home")
    public void TC016_sectionEligibilityCriteriaSectionVisibility(){
        logger.info("=========================================================");
        logger.info("STARTING TEST: TC016_sectionEligibilityCriteriaSectionVisibility");
        logger.info("=========================================================");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Checking if the 'Eligibility Criteria' section is visible on the Home page...");
            boolean isVisible = homePage.checkEligibilityCriteriaVisibility();

            Assert.assertTrue(isVisible, "'Eligibility Criteria' section is missing or hidden!");
            logger.info("SUCCESS: 'Eligibility Criteria' section is visible.");
        }
        catch(AssertionError ae){
            logger.error("TEST FAILED (Assertion): " + ae.getMessage());
            throw ae;
        }
        catch (Exception e) {
            logger.error("TEST CRASHED (Exception): " + e.getMessage());
            Assert.fail("Test failed due to a system exception: " + e.getMessage());
        }
    }
}