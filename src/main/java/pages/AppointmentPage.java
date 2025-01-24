package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AppointmentPage {

    WebDriver driver;

    public AppointmentPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@id='combo_facility']")
    WebElement facilityDropdown;

    @FindBy(xpath = "//input[@id='chk_hospotal_readmission']")
    WebElement readmissionCheckbox;

    @FindBy(xpath = "//div[@class='col-sm-4']/label")
    List<WebElement> healthProgramRadioBtns;

    @FindBy(xpath = "//input[@id='txt_visit_date']")
    WebElement dateTimePicker;

    @FindBy(xpath = "//textarea[@id='txt_comment']")
    WebElement commentBox;

    @FindBy(xpath = "//button[@id='btn-book-appointment']")
    WebElement bookAppointmentBtn;


    public void setFacility(int index){
        Select select = new Select(facilityDropdown);
        select.selectByIndex(index);
    }

    public void selectReadmissionCheckbox(){
        readmissionCheckbox.click();
    }

    public void selectHealthProgramRadioBtn(int index){
        for (WebElement radioBtn : healthProgramRadioBtns ){
            if (!radioBtn.isSelected()){
                driver.findElement(By.xpath("//div[@class='col-sm-4']/label" + "[" + index + "]")).click();
                break;
            }
        }
    }

    public void setDate(String date){
        dateTimePicker.sendKeys(date);
    }

    public void setComment(String comment){
        commentBox.sendKeys(comment);
    }

    public void clickBookAppointmentBtn(){
        bookAppointmentBtn.click();
    }

    public boolean isDropdownIndexValid(int dropdownIndex) {
        return dropdownIndex <= 2;
    }

    public boolean isRadioBtnIndexValid(int radioBtnIndex) {
        return radioBtnIndex <= 3;
    }
}
