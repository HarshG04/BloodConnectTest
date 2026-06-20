package DataProviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "LoginData")

    public Object[][] LoginData() {
        return new Object[][] {
                {"testcase@gmail.com", "password123","Valid"},
                {"invalid@gmail.com","password123","Invalid"},
                {"testcase@gmail.com","invalidpass","Invalid"}
        };
    }


    @DataProvider(name = "DonorNameUpdate")
    public Object[][] DonorNameUpdate() {
        return new Object[][]{
                {"john@bms.com", "password123", "john cena"}
        };
    }

    @DataProvider(name = "DonorBloodGrpUpdate")
    public Object[][] DonorBloodGrpUdate(){
        return new Object[][]{
                {"john@bms.com","password123","A+"}
        };
    }

    @DataProvider(name="DonorContactUpdate")
    public Object[][] DonorContactUpdate(){
        return new Object[][]{
                {"john@bms.com","password123","johncena@bms.com","9876543210"}
        };
    }

    @DataProvider(name = "DonorLocationUpdate")
    public Object[][] DonorLocationUpdate(){
        return new Object[][]{
                {"john@bms.com","password123","Hyderabad"}
        };
    }

    @DataProvider(name = "recipientLoginData")
    public Object[][] recipientLoginData() {
        return new Object[][] {{"johncena2@gmail.com", "password123"}};
    }

    @DataProvider(name="donorLogin")
    public Object[][] donorLogin(){
        return new Object[][] {{"johncena@gmail.com", "password123"}};
    }

    @DataProvider(name = "adminLoginData")
    public Object[][] adminLoginData() {
        return new Object[][] {{"admin@bms.com", "password123"}};
    }

    @DataProvider(name="bothLoginData")
    public Object[][] bothLoginData() {
        return new Object[][] {{"john@bms.com", "password123"}};
    }

    @DataProvider(name = "donorLoginData")
    public Object[][] donorLoginData() {
        return new Object[][] {{"john@bms.com", "password123"}};
    }

    @DataProvider(name = "donorRecipientData")
    public Object[][] donorRecipientData() {
        return new Object[][] {
                {
                        "ts008recipient@bms.com", "password123",
                        "ts008donor@bms.com", "password123"
                }
        };
    }



//    @DataProvider(name = "recipientRegistrationData")
//    public Object[][] recipientData() {
//        return new Object[][] {{"John Cena", "johncena2@gmail.com", "password123", "123456789", 25, 65.0, "male", "O+", "Chil Sez IT Park", "Coimbatore", "Tamilnadu", 123098, "no medical info"}};
//    }
//
//    @DataProvider(name = "bothRegistrationData")
//    public Object[][] bothData() {
//        return new Object[][] {{"John Cena", "johncena5@gmail.com", "password123", "123456789", 25, 65.0, "male", "O+", "Chil Sez IT Park", "Coimbatore", "Tamilnadu", 123098, "no medical info"}};
//    }
//
//    @DataProvider(name = "incorrectEmailRegistrationData")
//    public Object[][] incorrectEmailData() {
//        return new Object[][] {{"John Cena", "abcdefgh", "password123", "123456789", 25, 65.0, "male", "O+", "Chil Sez IT Park", "Coimbatore", "Tamilnadu", 123098, "no medical info"}};
//    }
//
//    @DataProvider(name = "duplicateEmailRegistrationData")
//    public Object[][] duplicateEmailData() {
//        return new Object[][] {
//                {"John Cena", "testcase@gmail.com", "password123", "123456789", 25, 65.0, "male", "O+", "Chil Sez IT Park", "Coimbatore", "Tamilnadu", 123098, "no medical info"},
//        };
//    }
//
//    @DataProvider(name = "duplicatePhoneRegistrationData")
//    public Object[][] duplicatePhoneData() {
//        return new Object[][] {
//                {"John Cena", "random@gmail.com", "password123", "123456789", 25, 65.0, "male", "O+", "Chil Sez IT Park", "Coimbatore", "Tamilnadu", 123098, "no medical info"},
//        };
//    }
}
