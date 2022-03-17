package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

import java.util.List;

public class SauceLabsTests extends TestBase {

    @Test(priority=1, groups = {"regression", "smoke"})
    public void validateLoginTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SauceDemoLoginPage loginPage=new SauceDemoLoginPage();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String actualPageHeader=driver.findElement(By.xpath("//span[@class='title']")).getText();
        String expectedMessage="PRODUCTS";
        Assert.assertEquals(actualPageHeader,expectedMessage);

    }
    @Test(priority=2, groups = {"regression"})
    public void validateFilterByPriceTest(){
        SauceDemoLoginPage loginPage=new SauceDemoLoginPage();
        loginPage.username.sendKeys("standard_user");
        loginPage.password.sendKeys("secret_sauce");
        loginPage.loginButton.click();
        WebElement filter=driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select=new Select(filter);
        select.selectByValue("lohi");
        List<WebElement> prices=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        for(int i=1; i<prices.size(); i++){
            String price=prices.get(i).getText().substring(1);
            double priceDouble=Double.parseDouble(price);

            String price2=prices.get(i-1).getText().substring(1);
            double price2Double=Double.parseDouble(price2);

            Assert.assertTrue(priceDouble>=price2Double);
        }


    }
    @Test(priority=3, groups = {"regression", "smoke"})
    public void validateOrderFunctionalityTest(){
        SauceDemoLoginPage loginPage=new SauceDemoLoginPage();
        loginPage.username.sendKeys("standard_user");
        loginPage.password.sendKeys("secret_sauce");
        loginPage.loginButton.click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        String price=driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        String checkOutPrice=driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
        Assert.assertEquals(checkOutPrice.substring(checkOutPrice.lastIndexOf(" ")+1),price);
        driver.findElement(By.id("finish")).click();

        String actualSuccessMessage=driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
        String expectedSuccessMessage="THANK YOU FOR YOUR ORDER";

        Assert.assertEquals(actualSuccessMessage,expectedSuccessMessage);

    }
    @Test(priority=4,groups = {"regression", "smoke"})
    public void validateCheckOutTotalTest(){

        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SauceDemoLoginPage loginPage=new SauceDemoLoginPage();
        loginPage.login();

        SauceDemoHomePage homePage=new SauceDemoHomePage();
        homePage.backPackProduct.click();
        homePage.bikeLightProduct.click();
        String backPackPrice=homePage.backPackPrice.getText();
        String bikeLightPrice=homePage.bikeLightPrice.getText();
        homePage.cart.click();

        SauceDemoMyCartPage myCartPage=new SauceDemoMyCartPage();
        myCartPage.checkOutButton.click();

        SauceDemoCheckOutPage checkOutPage=new SauceDemoCheckOutPage();
        checkOutPage.checkOutWithValidInfo();

        SauceDemoCheckOutOverviewPage checkOutOverviewPage=new SauceDemoCheckOutOverviewPage();
        String actualTotalPrice=checkOutOverviewPage.totalPrice.getText();

        double backPackPriceDouble=Double.parseDouble(backPackPrice.substring(1));
        double bikeLightPriceDouble=Double.parseDouble(bikeLightPrice.substring(1));

        double actualTotalPriceDouble=Double.parseDouble(actualTotalPrice.substring(actualTotalPrice.lastIndexOf("$")+1));

        Assert.assertTrue(backPackPriceDouble+bikeLightPriceDouble==actualTotalPriceDouble);



    }
}
