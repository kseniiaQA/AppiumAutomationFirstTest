import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
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
        capabilities.setCapability("appPackage", "ru.guptek.mobile_field_staff.dev");
        capabilities.setCapability("appActivity", "ru.guptek.mobile_field_staff.ui.MainActivity");
        capabilities.setCapability("app", "C:/Users/kalekseenko/GUPTEK_automation/apks/GUPTEK-Android-304083-dev-debug.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        waitForElementAndClick
                (By.xpath("//*[@text='While using the app']"), "Search Wikipedia", 5);
        waitForElementAndClick
                (By.xpath("//*[@text='While using the app']"), "Search Wikipedia", 5);
        waitForElementAndClick
                (By.xpath("//*[@text='While using the app']"), "Search Wikipedia", 5);

        waitForElementAndClick
                (By.xpath("//*[@text='Allow']"), "Search Wikipedia", 5);
        waitForElementAndClick
                (By.xpath("//*[@text='Allow']"), "Search Wikipedia", 5);


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

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, (int) timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, long timeoutInSeconds) {
        WebElement element = waitForElement(by, value, (int) timeoutInSeconds);
        element.sendKeys(value);
        return element;

    }

    private WebElement webElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    private Boolean webElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }



    @Test
    public void logInAndSee() {
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]"), "test3", 5);
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]"), "Aaaaa1", 5);
        waitForElementAndClick
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button"), "cannot find the element", 8);
        waitForElementAndClick
                (By.xpath("//*[@text='CANCEL']"), "cannot find this element", 10);
        webElementPresent(
                By.xpath("//*[@text='Tasks']"), "cannot find element", 10);
    }

    @Test
    public void incorrectLogin() {
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]"), "wrongCreds", 5);
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]"), "wrongCreds", 5);

        waitForElementAndClick
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button"), "cannot find the element", 8);

        webElementPresent(
                By.xpath("//*[@text='Неверный логин или пароль']"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='Connection error. Access denied.']"), "cannot find element", 10);
    }

    @Test
    public void canRevealPassword() {

        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]"), "password", 5);

        waitForElementAndClick
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]/android.view.View/android.view.View/android.widget.CheckBox"), "cannot find the element", 5);

        webElementPresent(
                By.xpath("//*[@text='password']"), "cannot find element", 10);
    }


    @Test
    public void logInAndCheckMainMenu() {
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]"), "test3", 5);
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]"), "Aaaaa1", 5);
        waitForElementAndClick
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button"), "cannot find the element", 8);
        waitForElementAndClick
                (By.xpath("//*[@text='CANCEL']"), "cannot find this element", 10);

        waitForElementAndClick
                (By.xpath("//android.view.View[@content-desc=\"Menu\"]"), "cannot find this element", 5);

        webElementPresent(
                By.xpath("//*[@text='Макаров Макар']"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='Data download history']"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='Change equipment state']"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='Emergency message']"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='Help']"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='About the app']"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='Main menu']"), "cannot find element", 10);

        webElementNotPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[2]"), "cannot find element", 5);


    }

    @Test
    public void logInAndCheckMainButtons() {
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]"), "test3", 5);
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]"), "Aaaaa1", 5);
        waitForElementAndClick
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button"), "cannot find the element", 8);
        waitForElementAndClick
                (By.xpath("//*[@text='CANCEL']"), "cannot find this element", 10);
        webElementPresent(
                By.xpath("//android.view.View[@content-desc=\"Tasks\"]"), "cannot find element", 5);
        webElementPresent(
                By.xpath("//android.view.View[@content-desc=\"Defects\"]"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//android.view.View[@content-desc=\"Education\"]"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//android.view.View[@content-desc=\"Menu\"]"), "cannot find element", 10);

        webElementPresent(
                By.xpath("//*[@text='Current']"), "cannot find element", 5);

        webElementPresent(
                By.xpath("//*[@text='Archive']"), "cannot find element", 5);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[1]"), "cannot find element", 5);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[2]"), "cannot find element", 5);

    }


    @Test
    public void logInAndCheckEducationTab() {
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]"), "test3", 5);
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]"), "Aaaaa1", 5);
        waitForElementAndClick
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button"), "cannot find the element", 8);
        waitForElementAndClick
                (By.xpath("//*[@text='CANCEL']"), "cannot find this element", 10);

        waitForElementAndClick
                (By.xpath("//android.view.View[@content-desc=\"Education\"]"), "cannot find this element", 10);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView"), "cannot find element", 5);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button"), "cannot find element", 5);

        webElementPresent(
                By.xpath("//*[@text='Current']"), "cannot find element", 5);

        webElementPresent(
                By.xpath("//*[@text='Archive']"), "cannot find element", 5);
        webElementNotPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[2]"), "cannot find element", 5);

    }


    @Test
    public void logInAndCheckDefectsTab() {
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]"), "test3", 5);
        waitForElementAndSendKeys
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]"), "Aaaaa1", 5);
        waitForElementAndClick
                (By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button"), "cannot find the element", 8);
        waitForElementAndClick
                (By.xpath("//*[@text='CANCEL']"), "cannot find this element", 10);

        waitForElementAndClick
                (By.xpath("//android.view.View[@content-desc=\"Defects\"]"), "cannot find this element", 10);

        webElementPresent(
                By.xpath("//*[@text='Get defects by equipments']"), "cannot find element", 5);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]"), "cannot find element", 5);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]"), "cannot find element", 5);

        webElementPresent(
                By.xpath("//*[@text='Defects are uploaded \n" +
                        " by equipment in the task.\n" +
                        " To upload defects by job \n" +
                        " pull the screen down']"), "cannot find element", 5);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.ImageView"), "cannot find element", 5);

        webElementPresent(
                By.xpath("//*[@text='Add defect']"), "cannot find element", 5);

        webElementPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"), "cannot find element", 5);

    }
}


