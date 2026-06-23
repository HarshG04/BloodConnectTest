package DataProviders;
import org.testng.annotations.Test;
import testBase.BaseClass;

public class DataSeeder extends BaseClass {

    @Test
    public void seedDatabaseUsers() {
        logger.info("Starting database seeding process...");

//        // 1. Seed "john@bms.com" (Both Donor and Recipient)
//        String[] johnData = {
//                "John Cena",            // [0] Name
//                "john@bms.com",         // [1] Email
//                "password123",          // [2] Password
//                "9876543210",           // [3] Phone
//                "35",                   // [4] Age
//                "85.0",                 // [5] Weight
//                "male",                 // [6] Gender
//                "A+",                   // [7] Blood Group
//                "Plot No 123",          // [8] Address
//                "Hyderabad",            // [9] City
//                "Telangana",            // [10] State
//                "500081",               // [11] ZIP Code (Must be numeric string)
//                "No medical history"    // [12] Medical Info
//        };
//        logger.info("Seeding user: john@bms.com (Both)");
//        registerUserHelper(johnData, "both");

        // 2. Seed "johncena@gmail.com" (Donor Only)
        String[] donorJohnData = {
                "John Donor",
                "johncena@gmail.com",
                "password123",
                "9876543211",
                "30",
                "75.5",
                "male",
                "O+",
                "Street 54",
                "Hyderabad",
                "Telangana",
                "500081",
                "None"
        };
        logger.info("Seeding user: johncena@gmail.com (Donor)");
        registerUserHelper(donorJohnData, "donor");

        // 3. Seed "johncena2@gmail.com" (Recipient Only)
        String[] recipientJohnData = {
                "John Recipient",
                "johncena2@gmail.com",
                "password123",
                "9876543212",
                "28",
                "68.0",
                "male",
                "B+",
                "Avenue 3",
                "Hyderabad",
                "Telangana",
                "500081",
                "None"
        };
        logger.info("Seeding user: johncena2@gmail.com (Recipient)");
        registerUserHelper(recipientJohnData, "recipient");

        // 4. Seed "testcase@gmail.com" (Donor for standard login checks)
        String[] testcaseData = {
                "Test Case User",
                "testcase@gmail.com",
                "password123",
                "9876543213",
                "40",
                "70.0",
                "female",
                "AB+",
                "Lane 2",
                "Hyderabad",
                "Telangana",
                "500081",
                "Healthy"
        };
        logger.info("Seeding user: testcase@gmail.com (Donor)");
        registerUserHelper(testcaseData, "donor");

        logger.info("Database seeding completed successfully!");
    }
}