package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoCheckOutPage {
    public SauceDemoCheckOutPage(){

        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
    @FindBy(id="first-name")
    public WebElement firstName;

    @FindBy(id="last-name")
    public WebElement lastName;

    @FindBy(id="postal-code")
    public WebElement zipCode;

    @FindBy(id="continue")
    public WebElement continueButton;

    public void checkOutWithValidInfo(){
        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        zipCode.sendKeys("12345");
        continueButton.click();

    }


}
