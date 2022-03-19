package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppHomePage {

    public StoreAppHomePage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver, this);

    }
    @FindBy(xpath ="//a[@class='login']")
    public WebElement loginButton;

    @FindBy(xpath = "(//div[@class='product-container'])[1]")
    public WebElement product1;

    @FindBy(xpath = "(//a[@data-id-product='1'])[1]")
    public WebElement product1AddCart;

    @FindBy(xpath = "(//h2)[1]")
    public WebElement addCartSuccessMessage;

    @FindBy(xpath = "//span[@title='Close window']")
    public  WebElement closeWindow;

    @FindBy(id = "layer_cart_product_title")
    public WebElement addedProductName;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    public WebElement cart;






}
