package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class HRPage extends TestBase {
    @DataProvider(name="registerData")
    public static Object[][] registerData() {
        Object[][] data = new Object[][]{
                {"Mindtek1", "MindtekStudent"},
                {"Mindtek", "MindtekStudent123"},
                {"Mindtekk", "MindtekStudent123"}
        };
        return data;
    }

    @Test(priority=1, groups = {"smoke", "regression"})
    public void validateHRLoginTest(){
        driver.get(ConfigReader.getProperty("HRPageURL"));
        HRPage loginHRPage=new HRPage();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Mindtek");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("MindtekStudent");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

        String actualText=driver.findElement(By.xpath("//label[@for='departments']")).getText();
        String expectedTxt="Select Department";

        Assert.assertEquals(actualText,expectedTxt);

    }
    @Test(priority = 2, groups = {"smoke", "regression"}, dataProvider = "registerData")
    public void validateHRLoginNegativeTest(String userName, String password){
        driver.get(ConfigReader.getProperty("HRPageURL"));
        HRPage loginHRPage=new HRPage();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

        String actualText=driver.findElement(By.xpath("//div[@class='alert alert-waring']")).getText();
        String expectedTxt="Invalid credentials";

        Assert.assertEquals(actualText,expectedTxt);


    }
    @Test(priority = 3, groups = {"smoke", "regression"})
    public void validateHRLogOutTest(){

        driver.get(ConfigReader.getProperty("HRPageURL"));
        HRPage logOutHRPage=new HRPage();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Mindtek");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("MindtekStudent");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        driver.findElement(By.xpath("//a[@routerlink='/logout']")).click();

        String actualText=driver.findElement(By.tagName("h1")).getText();
        String expectedTxt="You are logged out";

        Assert.assertEquals(actualText,expectedTxt);



    }
}
