package testCases;

import base.Base;
import functions.CommonFunctions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AppointmentPage;

public class MakeAppointmentTest extends Base {

    /// Data provider for appointment test.
    @DataProvider
    public Object[][] appointmentData(){
        return new Object[][]{
                {2, 3, "2025-03-08", "I need to get an appointment."}
        };
    }

    @Test(testName = "Make Appointment Test", dataProvider = "appointmentData")
    public void makeAppointmentTest(int dropdownIndex, int radioBtnIndex, String date, String comment){
        /// Initialize common functions
        CommonFunctions commonFunctions = new CommonFunctions(getDriver());

        /// Navigate to login page and perform login
        commonFunctions.goFromHomeToLoginPage();
        commonFunctions.logIntoSystem(getValidUsername(), getValidPassword());

        /// Initialize AppointmentPage and validate inputs
        AppointmentPage appointmentPage = new AppointmentPage(getDriver());
        Assert.assertTrue(appointmentPage.isDropdownIndexValid(dropdownIndex),
                "Invalid dropdown index provided: " + dropdownIndex);
        Assert.assertTrue(appointmentPage.isRadioBtnIndexValid(radioBtnIndex),
                "Invalid radio button index provided: " + radioBtnIndex);

        /// Fill appointment form and submit
        appointmentPage.setFacility(dropdownIndex);
        appointmentPage.selectReadmissionCheckbox();
        appointmentPage.selectHealthProgramRadioBtn(radioBtnIndex);
        appointmentPage.setDate(date);
        appointmentPage.setComment(comment);
        appointmentPage.clickBookAppointmentBtn();

        /// Verify appointment submission
        boolean actualUrl = getDriver().getCurrentUrl().contains("summary");
        Assert.assertTrue(actualUrl, "Appointment registration failed. Did NOT directed to appointment summary page");
    }
}
