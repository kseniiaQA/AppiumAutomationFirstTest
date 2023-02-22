package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.AppiumDriver;

public class BackgroundPageObject extends MainPageObject {


        private static final String
                ELEMENT = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.View";


    public BackgroundPageObject(AppiumDriver driver) {
            super(driver);
        }


        public static void backgroundCheck() {
            MainPageObject.webElementPresent(
                    By.xpath(ELEMENT ), "cannot find element", 5);
            driver.runAppInBackground(2);
            MainPageObject.webElementPresent(
                    By.xpath(ELEMENT ), "cannot find element after returning from background", 5);
        }
    }

