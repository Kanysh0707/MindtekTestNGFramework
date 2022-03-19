package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppCartPage {
    public StoreAppCartPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//td[@class='cart_description']//p[@class='product-name']")
    public WebElement productName;
}
