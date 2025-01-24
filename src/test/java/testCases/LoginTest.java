package testCases;

import base.Base;
import functions.CommonFunctions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends Base {

    /// Data provider for login test with multiple credentials.
    @DataProvider
    public Object[][] loginData(){
        return new Object[][]{
                {getValidUsername(), getValidPassword(), "pass", "https://katalon-demo-cura.herokuapp.com/#appointment"},
                {getValidUsername(), "invalidPassword", "fail", "Login failed! Please ensure the username and password are valid."},
                {"invalidUsername", getValidPassword(), "fail", "Login failed! Please ensure the username and password are valid."},
                {"invalidUsername", "invalidPassword", "fail", "Login failed! Please ensure the username and password are valid."}
        };
    }

    @Test(testName = "Login Test with Multiple Credentials", dataProvider = "loginData")
    public void loginTest(String username, String password, String status, String expected) {
        /// Initialize common functions
        CommonFunctions commonFunctions = new CommonFunctions(getDriver());

        /// Navigate to login page and perform login
        commonFunctions.goFromHomeToLoginPage();
        commonFunctions.logIntoSystem(username, password);

        if (status.equals("pass")){
            /// Validate URL for successful login
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertEquals(actualUrl, expected, "Login unsuccessful. Expected URL: " + expected + ", but got: " + actualUrl);
        } else if (status.equals("fail")) {
            /// Validate error message for failed login
            String actualErrorMsg = LoginPage.getErrorMessage();
            Assert.assertNotNull(actualErrorMsg, "Error message not displayed for invalid credentials.");
            Assert.assertEquals(actualErrorMsg, expected, "Unexpected error message.");
        }
    }
}
