package utilities;

import org.apache.commons.lang3.RandomStringUtils;
import pageObjects.BloodCampPage;
import pageObjects.RegisterPage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomDataGeneratorUtil {
    private static Random random = new Random();

    public static String[] randomUserDataGenerator(){

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

    }

    public static void randomBloodCampGenerator(BloodCampPage bloodCampPage) {
        String bloodCampName = "bloodCamp-"+ RandomStringUtils.randomAlphabetic(4);
        String location = "location-" + RandomStringUtils.randomAlphabetic(4);
        String date = randomDateGenerator();
        String[] time = randomTimeGenerator();
        int capacity = random.nextInt(90)+10;
        String description = "description-" + RandomStringUtils.randomAlphabetic(8);

        bloodCampPage.setBloodCampData(bloodCampName,location,date,time[0],time[1],capacity,description);
        bloodCampPage.clickSaveCamp();
    }

    public static String randomDateGenerator(){
        Random random = new Random();
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int year = 2026 + random.nextInt(2);
        return String.format("%02d/%02d/%d", month, day, year);
    }

    public static String[] randomTimeGenerator() {
        Random random = new Random();
        int startHour = random.nextInt(10)+8;
        int startMinute = random.nextInt(60);

        int endHour = (startHour+1) + random.nextInt(24-(startHour+1));
        int endMinute = random.nextInt(60);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmma");

        LocalTime startTime = LocalTime.of(startHour,startMinute);
        LocalTime endTime = LocalTime.of(endHour,endMinute);

        return new String[]{formatter.format(startTime),formatter.format(endTime)};
    }

    public static String[] generateRandomInventoryData(){
        String[] bloodGroups = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        return new String[]{
                bloodGroups[random.nextInt(bloodGroups.length)],
                String.valueOf(random.nextInt(50) + 10),
                randomDateGenerator(),
                "src-" + RandomStringUtils.randomAlphabetic(5),
        };

    }

}
