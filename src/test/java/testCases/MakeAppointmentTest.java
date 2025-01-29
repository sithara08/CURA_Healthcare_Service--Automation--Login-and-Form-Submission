package testCases;

import base.Base;
import functions.CommonFunctions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import pages.AppointmentPage;

import java.time.Duration;

public class MakeAppointmentTest extends Base {

    /// Data provider for appointment test.
    @DataProvider
    public Object[][] appointmentData(){
        return new Object[][]{
                {2, 3, "2025-03-08", "I need to get an appointment."}
        };
    }

    /// Sets up the browser before each test method.
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("edge") String browser){
        initializeDriver(browser);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        getDriver().get(getBaseUrl());
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
