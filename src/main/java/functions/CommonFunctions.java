package functions;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

public class CommonFunctions {

    WebDriver driver;

    public CommonFunctions(WebDriver driver){
        this.driver = driver;
    }

    public void goFromHomeToLoginPage(){
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointmentBtn();
    }

    public void logIntoSystem(String username, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginBtn();
    }
}
