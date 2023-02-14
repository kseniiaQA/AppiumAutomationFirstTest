import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/kalekseenko/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }


    private WebElement waitForElement(By by, String error_message, int i) {

        WebDriverWait wait = new WebDriverWait(driver, i);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement[] getListOfElements(By by, String error_message, int i) {
        WebElement element = waitForElement(by, error_message, (int) i);


        return new WebElement[]{element};
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, (int) timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, (int) timeoutInSeconds);
        element.sendKeys(value);
        return element;


    }

    private boolean webElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement webElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, (int) timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement assertElementHasText(By by, String expectedText, String error_message) {
        WebElement element = waitForElement(by, error_message, 15);
        element.getText();
        return element;

    }

    private WebElement assertElementContainsText(By by, String expectedText, String error_message) {
        WebElement element = waitForElement(by, error_message, 15);
        element.getText();
        return element;

    }

    private WebElement getListOfElements(By by, String expectedText, String error_message) {
        WebElement element = waitForElement(by, error_message, 15);
        element.getText();
        return element;
    }


    @Test
    public void checksLocatorText() {

        assertElementHasText
                (By.xpath("//*[@text='Search Wikipedia']"), "Search Wikipedia", "we see unexpected text");
        WebElement title_element = waitForElement(
                By.xpath("//*[@text='Search Wikipedia']"), "Cannot find article title", 15);
        String element = title_element.getText();
        Assert.assertEquals("We see unexpected title", "Search Wikipedia", element);
    }

    @Test
    public void cancelSearchAndCheck() {


        waitForElement(
                By.id("org.wikipedia:id/search_container"), "cannot find element", 5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"), "cannot find element", 5);

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"), "Java", "cannot find input", 5);

        webElementPresent(
                By.id("org.wikipedia:id/page_list_item_description"), "cannot find element", 5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"), "cannot find element", 5);

        webElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_description"), "cannot find element", 5);
    }

    @Test
    public void wordSearch() {


        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"), "cannot find element", 5);
        waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"), "Java", "cannot find input", 5);

        for (WebElement element : getListOfElements(By.id("org.wikipedia:id/page_list_item_title"), "cannot find the element", 6)) {

            assertElementContainsText(

                    (By) element,

                    "Java",

                    "Search result doesn't contain a word 'Java'");

        }

    }
}




