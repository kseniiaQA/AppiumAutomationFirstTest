package lib.ui;

import static org.openqa.selenium.By.id;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class ArticlePageObject extends MainPageObject {


    private static final String
            MORE_OPTION_BUTTON = "//android.widget.ImageView[@content-desc=\"More options\"]",
            ADD_TO_LIST = "//*[@text='Add to reading list']",
            ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button",
            FOLDER_NAME = "org.wikipedia:id/text_input",
            OK_BUTTON = "android:id/button1",
            NAVIGATE_UP = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            MY_FOLDER = "//*[@text='My folder']",
            MENU_BUTTON = "//android.widget.FrameLayout[@content-desc=\"My lists\"]/android.widget.ImageView",
            SECOND_ARTICLE = "//*[@text='Appius Claudius Caecus']",
            FIRST_ARTICLE = "//*[@text='Appium']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    public static void clickMoreOptionsButton() {
        waitForElementAndClick(
                By.xpath(MORE_OPTION_BUTTON), "cannot find element", 5);

    }

    public static void addToList() {
        waitForElementAndClick(
                By.xpath(ADD_TO_LIST), "cannot find element to add article to a list", 5);

    }

    public static void clickOnboardingButton() {
        waitForElementAndClick(
                id(ONBOARDING_BUTTON), "cannot find element overlay", 5);

    }

    public static void clearFolderInput() {
        MainPageObject.waitForElementAndClear(
                id(FOLDER_NAME), "cannot find element to clear the input", 5);

    }


    public static void newFolderName(String name) {
        MainPageObject.waitForElementAndSendKeys(
                id(FOLDER_NAME), name, "cannot find the input", 5);

    }


    public static void saveToFolder() {

        waitForElementAndClick(
                id(OK_BUTTON), "cannot find element to go to main page", 5);


        waitForElementAndClick(
                By.xpath(NAVIGATE_UP), "cannot find element to navigate to my list", 5);

    }


    public static void goToFolder() {
        waitForElementAndClick(
                By.xpath(MY_FOLDER), "cannot find element overlay", 5);


        waitForElementAndClick(
                By.xpath(NAVIGATE_UP), "cannot find element to go to main page", 5);

        waitForElementAndClick(
                By.xpath(MENU_BUTTON), "cannot find element navigate to my list", 5);

        waitForElementAndClick(
                By.xpath(MY_FOLDER), "cannot find my article in My list", 5);

    }

    public static void selectAnotherArticle() {
        waitForElementAndClick(
                By.xpath(SECOND_ARTICLE), "cannot find element overlay", 5);
    }

    public static void deleteArticleFromList() {
        MainPageObject.swipeUpElementToLeft(
                By.xpath(FIRST_ARTICLE), "cannot delete my article in My list");
    }

    public static void checkArticlePresense() {
        MainPageObject.webElementPresent(
                By.xpath(SECOND_ARTICLE), "cannot find the element", 5);
    }
}

