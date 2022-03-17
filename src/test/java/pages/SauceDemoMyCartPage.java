package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoMyCartPage {

    public SauceDemoMyCartPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }

    @FindBy(id="checkout")
    public WebElement checkOutButton;

}
