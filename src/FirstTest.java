import static org.openqa.selenium.By.id;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        driver.rotate(ScreenOrientation.PORTRAIT);
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


    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction().moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);

    }

    protected void swipeUpElement(By by, String error_message, int max_swipes) {
        swipeUp(200);
        driver.findElements(by);
        driver.findElements(by).size();
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElement(by, "cannot find the element", Integer.parseInt(0 + error_message));
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }

    protected void swipeUpElementToLeft(By by, String error_message) {
        WebElement element = webElementPresent(by, error_message, 300);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = left_x + element.getSize().getHeight();
        int middle_y = upper_y + lower_y / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(200)
                .moveTo(left_x, middle_y)
                .release()
                .perform();


    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();

    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String message = "an element " + by.toString() + "supposed to be not present";
            throw new AssertionError(message + "" + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, int timeoutInSeconds) {

        WebElement element = waitForElement(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


    private void assertElementPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements < 0) {
            String message = "an element " + by.toString() + "supposed to be present";
            throw new AssertionError(message + "" + error_message);
        }

//
//    @Test
//    public void checksLocatorText() {
//
//        assertElementHasText
//                (By.xpath("//*[@text='Search Wikipedia']"), "Search Wikipedia", "we see unexpected text");
//        WebElement title_element = waitForElement(
//                By.xpath("//*[@text='Search Wikipedia']"), "Cannot find article title", 15);
//        String element = title_element.getText();
//        Assert.assertEquals("We see unexpected title", "Search Wikipedia", element);
//    }
//
//
//    @Test
//    public void cancelSearchAndCheck() {
//
//
//        waitForElement(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//        waitForElementAndClick(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[@text='Search…']"), "Java", "cannot find input", 5);
//
//        webElementPresent(
//                id("org.wikipedia:id/page_list_item_description"), "cannot find element", 5);
//
//        waitForElementAndClick(
//                id("org.wikipedia:id/search_close_btn"), "cannot find element", 5);
//
//        webElementNotPresent(
//                id("org.wikipedia:id/page_list_item_description"), "cannot find element", 5);
//    }
//
//    @Test
//    public void wordSearch() {
//
//
//        waitForElementAndClick(
//                By.xpath("//*[@text='Search Wikipedia']"), "cannot find element", 5);
//        waitForElementAndSendKeys(
//                By.xpath("//*[@text='Search…']"), "Java", "cannot find input", 5);
//
//        for (WebElement element : getListOfElements(id("org.wikipedia:id/page_list_item_title"), "cannot find the element", 6)) {
//
//            assertElementContainsText(
//
//                    (By) element,
//
//                    "Java",
//
//                    "Search result doesn't contain a word 'Java'");
//
//        }
//
//    }
//
//    @Test
//    public void swipeUp() {
//
//        waitForElement(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//        waitForElementAndClick(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//
//        waitForElementAndSendKeys(
//                id("org.wikipedia:id/search_src_text"), "Appium", "cannot find input", 5);
//
//        waitForElementAndClick(
//                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.View"), "cannot find element", 5);
//
//        swipeUpElement(
//                By.xpath("//*[@text='View page in browser']"), "cannot find the element", 20);
//
//
//    }

    }
        @Test
        public void saveArticlesToMyListDeleteAndCheckTitles () {

            waitForElement(
                    id("org.wikipedia:id/search_container"), "cannot find element", 5);
            waitForElementAndClick(
                    id("org.wikipedia:id/search_container"), "cannot find element", 5);

            waitForElementAndSendKeys(
                    id("org.wikipedia:id/search_src_text"), "Appium", "cannot find input", 5);

            waitForElementAndClick(
                    By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.View"), "cannot find element", 5);

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"), "cannot find element", 5);

            waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"), "cannot find element to add article to a list", 5);

            waitForElementAndClick(
                    id("org.wikipedia:id/onboarding_button"), "cannot find element overlay", 5);

            waitForElementAndClear(
                    id("org.wikipedia:id/text_input"), "cannot find element to clear the input", 5);

            waitForElementAndSendKeys(
                    id("org.wikipedia:id/text_input"), "My folder", "cannot find the input", 5);

            waitForElementAndClick(
                    id("android:id/button1"), "cannot find element to go to main page", 5);


            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"), "cannot find element to navigate to my list", 5);


            waitForElement(
                    id("org.wikipedia:id/search_container"), "cannot find element", 5);
            waitForElementAndClick(
                    id("org.wikipedia:id/search_container"), "cannot find element", 5);

            waitForElementAndSendKeys(
                    id("org.wikipedia:id/search_src_text"), "Appium", "cannot find input", 5);

            waitForElementAndClick(
                    By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]"), "cannot find element to navigate to my list", 5);


            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"), "cannot find element", 5);

            waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"), "cannot find element to add article to a list", 5);

            waitForElementAndClick(
                    By.xpath("//*[@text='My folder']"), "cannot find element overlay", 5);


            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"), "cannot find element to go to main page", 5);

            waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]/android.widget.ImageView"), "cannot find element navigate to my list", 5);

            waitForElementAndClick(
                    By.xpath("//*[@text= 'My folder']"), "cannot find my article in My list", 5);

            swipeUpElementToLeft(
                    By.xpath("//*[@text='Appium']"), "cannot delete my article in My list");
            String title1 = "//*[@text='Appius Claudius Caecus']";

            webElementPresent(
                    By.xpath(title1), "cannot find the element", 5);

            String title2 = "//*[@text='Appius Claudius Caecus']";
            driver.rotate(ScreenOrientation.LANDSCAPE);
            waitForElementAndClick(
                    By.xpath(title2), "cannot find my article in My list", 5);

            Assert.assertEquals("titles are not the same", title1, title2);

        }


        @Test
        public void assertTitleIsPresent () {
            waitForElement(
                    id("org.wikipedia:id/search_container"), "cannot find element", 5);
            waitForElementAndClick(
                    id("org.wikipedia:id/search_container"), "cannot find element", 5);

            waitForElementAndSendKeys(
                    id("org.wikipedia:id/search_src_text"), "Appium", "cannot find input", 5);

            waitForElementAndClick(
                    By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.View"), "cannot find element", 5);

            assertElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"), "the element supposed to be here");

        }
    }



//
//    @Test
//    public void amountOfEmptySearchResults() {
//        waitForElement(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//        waitForElementAndClick(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//        String value = "qweasdzxc";
//        waitForElementAndSendKeys(
//                id("org.wikipedia:id/search_src_text"), value, "cannot find input", 5);
//        String empty_string_label = "//*[@text='No results found']";
//        webElementPresent(
//                By.xpath("//*[@text='No results found']"), "cannot find empty result label", 15);
//        assertElementNotPresent(
//                id("org.wikipedia:id/page_list_item_container"), "we found no result");
//
//
//    }
//
//
//    @Test
//    public void changeScreenOrientation() {
//        waitForElement(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//        waitForElementAndClick(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//
//        waitForElementAndSendKeys(
//                id("org.wikipedia:id/search_src_text"), "Appium", "cannot find input", 5);
//
//        waitForElementAndClick(
//                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.View"), "cannot find element", 5);
//        String title_before_rotation = String.valueOf(waitForElement(
//                By.id("org.wikipedia:id/view_page_title_text"), "the element is not found", 10));
//
//        driver.rotate(ScreenOrientation.LANDSCAPE);
//
//        String title_after_rotation = String.valueOf(waitForElement(
//                By.id("org.wikipedia:id/view_page_title_text"), "the element is not found", 10));
//        Assert.assertEquals("titles are not the same", title_before_rotation, title_after_rotation);
//    }
//
//
//    @Test
//    public void checkSearchInBackground() {
//        waitForElement(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//        waitForElementAndClick(
//                id("org.wikipedia:id/search_container"), "cannot find element", 5);
//
//        waitForElementAndSendKeys(
//                id("org.wikipedia:id/search_src_text"), "Appium", "cannot find input", 5);
//
//        webElementPresent(
//                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.View"), "cannot find element", 5);
//
//        driver.runAppInBackground(2);
//
//        webElementPresent(
//                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.View"), "cannot find element after returning from background", 5);
//
//    }
//}



