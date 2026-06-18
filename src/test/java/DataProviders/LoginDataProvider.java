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
    public Object[][] DonorNameUpdate(){
        return new Object[][]{
                {"john@bms.com","password123","john cena"}
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
