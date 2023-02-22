package tests;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import lib.CoreCaseTest;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;

public class ArticleTests extends CoreCaseTest {

    @Test
    public void testSaveArticlesToMyListDeleteAndCheckTitles() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchInput("Appium");
        searchPageObject.seeResultAndClick();
        articlePageObject.clickMoreOptionsButton();
        articlePageObject.addToList();
        articlePageObject.clickOnboardingButton();
        articlePageObject.clearFolderInput();
        articlePageObject.newFolderName("My folder");
        articlePageObject.saveToFolder();
        searchPageObject.initSearchInput();
        searchPageObject.searchInput("Appium");
        articlePageObject.selectAnotherArticle();
        articlePageObject.clickMoreOptionsButton();
        articlePageObject.addToList();
        articlePageObject.goToFolder();
        articlePageObject.deleteArticleFromList();
        String title1 = "//*[@text='Appius Claudius Caecus']";
        articlePageObject.checkArticlePresense();
        String title2 = "//*[@text='Appius Claudius Caecus']";
        driver.rotate(ScreenOrientation.LANDSCAPE);
        articlePageObject.selectAnotherArticle();
        assertEquals("titles are not the same", title1, title2);

    }


    @Test
    public void testAssertTitleIsPresent() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchInput("Appium");
        searchPageObject.seeResultAndClick();
        searchPageObject.checkTitlePresence();

    }

}
