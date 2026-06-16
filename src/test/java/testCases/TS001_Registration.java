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

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            RandomDataGeneratorUtil.submitUserData(registerPage,RandomDataGeneratorUtil.randomUserDataGenerator());
            registerPage.clickRegister();
            String alertMessage = registerPage.getAlertMessage();
            Assert.assertEquals(alertMessage,"User Registered Successfully");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }


    }

    @Test(priority = 2)
    public void TC002_verifyRecepientRegistration(){

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickDonor();  //unSelect donor
            registerPage.clickRecipient();
            RandomDataGeneratorUtil.submitUserData(registerPage,RandomDataGeneratorUtil.randomUserDataGenerator());
            registerPage.clickRegister();
            String alertMessage = registerPage.getAlertMessage();
            Assert.assertEquals(alertMessage,"User Registered Successfully");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }

    }

    @Test(priority = 3)
    public void TC003_verifyBothRegistration(){

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickRecipient();
            RandomDataGeneratorUtil.submitUserData(registerPage,RandomDataGeneratorUtil.randomUserDataGenerator());
            registerPage.clickRegister();
            String alertMessage = registerPage.getAlertMessage();
            Assert.assertEquals(alertMessage,"User Registered Successfully");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }

    }

    @Test(priority = 4)
    public void TC004_verifyIncorrectEmailRegistration(){

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData[1] = "abcdef";
            RandomDataGeneratorUtil.submitUserData(registerPage,userData);
            String errorMsg = registerPage.getEmailErrorMessage();
            Assert.assertEquals(errorMsg,"Please enter a valid email");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }

    }

    @Test(priority = 5)
    public void TC005_verifyDuplicateEmailRegistration(){

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            RandomDataGeneratorUtil.submitUserData(registerPage,userData);
            Thread.sleep(2000);
            registerPage.clickRegister();
            String alertMsg = registerPage.getAlertMessage();
            if(!alertMsg.equals("User Registered Successfully")) Assert.fail();

            Thread.sleep(2000);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRegister();
            
            String[] userData1 = RandomDataGeneratorUtil.randomUserDataGenerator();
            userData1[1] = userData[1];
            Thread.sleep(2000);
            RandomDataGeneratorUtil.submitUserData(registerPage,userData1);
            Thread.sleep(2000);
            registerPage.clickRegister();
            String alertMessage = registerPage.getAlertMessage();
            Assert.assertEquals(alertMessage,"Email already registered");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }

    }

    @Test(priority = 6)
    public void TC006_verifyDuplicatePhoneRegistration(){

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickRegister();

            RegisterPage registerPage = new RegisterPage(driver);
            String[] userData = RandomDataGeneratorUtil.randomUserDataGenerator();
            RandomDataGeneratorUtil.submitUserData(registerPage,userData);
            Thread.sleep(2000);
            registerPage.clickRegister();
            String alertMsg = registerPage.getAlertMessage();
            if(!alertMsg.equals("User Registered Successfully")) Assert.fail();

            Thread.sleep(2000);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRegister();

            String[] userData1 = RandomDataGeneratorUtil.randomUserDataGenerator();
            Thread.sleep(2000);
            userData1[3] = userData[3];
            Thread.sleep(2000);
            RandomDataGeneratorUtil.submitUserData(registerPage,userData1);
            String alertMessage = registerPage.getAlertMessage();
            
            Assert.assertEquals(alertMessage,"Phone already registered");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }

    }

}
