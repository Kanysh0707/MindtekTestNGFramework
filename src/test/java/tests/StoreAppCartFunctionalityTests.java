package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoreAppCartPage;
import pages.StoreAppHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;

public class StoreAppCartFunctionalityTests extends TestBase {

    @Test
    public void validateAddCartFunctionalityTest() throws InterruptedException, IOException {
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        BrowserUtils.scrollUpOrDown(600);

        StoreAppHomePage homePage=new StoreAppHomePage();
        BrowserUtils.hoverOver(homePage.product1);
        homePage.product1AddCart.click();

        Thread.sleep(15000);

        BrowserUtils.takeScreenshot();

        String actualMessage=homePage.addCartSuccessMessage.getText();
        String expectedMessage="Product successfully added to your shopping cart";

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);

        String addedProductName=homePage.addedProductName.getText();

        homePage.closeWindow.click();

        homePage.cart.click();

        StoreAppCartPage cartPage=new StoreAppCartPage()
;
        String addedProductNameInCart=cartPage.productName.getText();

        softAssert.assertEquals(addedProductNameInCart, addedProductName);
        softAssert.assertAll();

    }
}
