package testCases;

import DataProviders.LoginDataProvider;
import org.testng.annotations.Test;
import testBase.BaseClass;

public class TS008_DonorIncomingRequestHandling extends BaseClass {
    @Test(dataProvider = "donorRecipientData", dataProviderClass = LoginDataProvider.class)
    public void TC025_verifyDonorCanViewIncomingRequests(String recipientEmail,String recipientPwd,String donorEmail,String donorPwd){
        try{

        }

    }
}
