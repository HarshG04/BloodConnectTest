package utilities;

import org.apache.commons.lang3.RandomStringUtils;
import pageObjects.RegisterPage;

import java.util.Random;

public class RandomDataGeneratorUtil {
    public static String[] randomUserDataGenerator(){
        Random random = new Random();
        String[] bloodGroups = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        String[] genders = {"male", "female"};

        return new String[]{
                RandomStringUtils.randomAlphabetic(5),                         // [0] Name
                RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@gmail.com", // [1] Email
                "Pass" + RandomStringUtils.randomAlphanumeric(6),              // [2] Password (Safe mix)
                RandomStringUtils.randomNumeric(10),                           // [3] Phone number (10 digit string)
                String.valueOf(random.nextInt(48) + 18),                       // [4] Age (Generates integer between 50 and 100)
                String.valueOf(random.nextInt(91) + 60),                       // [5] Weight (Generates integer between 60 and 150)
                genders[random.nextInt(genders.length)],                       // [6] Gender ("male" or "female")
                bloodGroups[random.nextInt(bloodGroups.length)],               // [7] Blood Group
                RandomStringUtils.randomAlphabetic(8),             // [8] Address
                RandomStringUtils.randomAlphabetic(5),               // [9] City
                "State-" + RandomStringUtils.randomAlphabetic(5),              // [10] State
                RandomStringUtils.randomNumeric(6),                            // [11] ZIP Code
                "Medical condition history: " + RandomStringUtils.randomAlphabetic(10) // [12] Medical Info
        };
    }

    public static void submitUserData(RegisterPage registerPage, String[] userData){
        registerPage.setPersonalDetails(
                userData[0], // name
                userData[1], // email
                userData[2], // password
                userData[3], // phone
                Integer.parseInt(userData[4]),    // age
                Double.parseDouble(userData[5]),  // weight
                userData[7], // bloodgroup (Double check if index 6 or 7 matches your Page Object parameter order!)
                userData[6]  // gender
        );

        registerPage.setAddress(
                userData[8],  // address
                userData[9],  // city
                userData[10], // state
                Integer.parseInt(userData[11]) // zip
        );

        registerPage.setMedicalInfo(userData[12]);
        registerPage.clickAgreeTerms();
    }
}
