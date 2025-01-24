package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='txt-username']")
    private WebElement usernameBox;

    @FindBy(xpath = "//input[@id='txt-password']")
    private WebElement passwordBox;

    @FindBy(xpath = "//button[@id='btn-login']")
    private WebElement loginBtn;

    @FindBy(xpath = "//p[contains(text(),'Login failed')]")
    private static WebElement errorMessage;


    public void setUsername(String username){
        usernameBox.sendKeys(username);
    }

    public void setPassword(String password){
        passwordBox.sendKeys(password);
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }

    public static String getErrorMessage(){
        return errorMessage.getText();
    }
}
