package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BrowserUtils {
    /**
     * Method will select value in propdown by value name
     * @param element
     * @param value
     */

    public static void selectDropDownByValue(WebElement element, String value) {

        Select select = new Select(element);
        select.selectByValue(value);
    }

    /**
     * Method will scroll up or down by the number of pixels
     * that is provided as parameter.
     * Positive number of pixels: Scrolls down
     * Negative number of pixels: Scrolls up
     * @param pixels
     */

    public static void scrollUpOrDown(int pixels) {
        WebDriver driver = Driver.getDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    /**
     * Method moves to element in browser
     * @param element
     */

    public static void hoverOver(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * Take screenshot of current page and stores it under screenshot folder
     * @return
     * @throws IOException
     */

    public static String takeScreenshot() throws IOException {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = System.currentTimeMillis() + ".png";
        String path = "/Users/kanysaiorozbekkyzy/IdeaProjects/MindtekTestNGFramework/src/test/resources/screenshots/" + screenshotName;
        File file = new File(path);
        FileUtils.copyFile(screenshot, file);
        return screenshotName;
    }

    /**
     * Method waits explicitly for element to be clickable
     * @param element
     * @return
     */

    public static WebElement waitElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        return clickableElement;
    }

    /**
     * Method waits explicitly for element to be visible
     * @param element
     * @return
     */

    public static WebElement waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        return visibleElement;
    }
}
