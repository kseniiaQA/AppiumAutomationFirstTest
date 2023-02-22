package tests;
import org.junit.Test;
import lib.CoreCaseTest;
import lib.ui.SearchPageObject;
public class SearchTests extends CoreCaseTest {

    @Test
    public void testCancelSearchAndCheck() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchInput("Java");
        searchPageObject.seeDescription();
        searchPageObject.clickCloseButton();
        searchPageObject.checkAbsence();
    }

    @Test
    public void testSwipeUp() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchInput("Appium");
        searchPageObject.seeResultAndClick();
        searchPageObject.swipeElement();
    }


    @Test
    public void testAmountOfEmptySearchResults() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchInput("grhhtht");
        searchPageObject.seeResultAndClick();
        searchPageObject.checkPresence();
        searchPageObject.checkItemContainerPresence();
    }

}
