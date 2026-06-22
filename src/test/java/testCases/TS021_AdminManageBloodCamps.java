//package testCases;
//
//import DataProviders.LoginDataProvider;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pageObjects.AdminPage;
//import pageObjects.BloodCampPage;
//import pageObjects.BloodRequestsPage;
//import testBase.BaseClass;
//import utilities.RandomDataGeneratorUtil;
//
//public class TS021_AdminManageBloodCamps extends BaseClass {
//
//    @Test(dataProvider = "adminLoginData",dataProviderClass = LoginDataProvider.class)
//    public void TC057_VerifyAdminCanAddsBloodCamp(String email,String password) {
//        LoginUserHelper(email, password);
//        logger.info("clicking Blood Camps Button...");
//        new AdminPage(driver).clickBloodCampsMenu();
//
//        BloodCampPage bloodCampPage = new BloodCampPage(driver);
//        logger.info("Clicking Add Camp Button...");
//        bloodCampPage.clickAddCampButton();
//        logger.info("Generating random blood camp data");
//        RandomDataGeneratorUtil.randomBloodCampGenerator(bloodCampPage);
//
//        logger.info("Getting alert message from admin message");
//        String alertMessage = new AdminPage(driver).getAlertMessage();
//        Assert.assertEquals(alertMessage,"");
//    }
//
//
//}
