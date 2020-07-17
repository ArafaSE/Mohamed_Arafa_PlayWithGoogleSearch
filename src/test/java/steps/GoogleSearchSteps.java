package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.GoogleHomePage;
import pages.GoogleResultsPage;
import tests.TestBase;

import java.util.List;

public class GoogleSearchSteps extends TestBase {
    // page objects
    GoogleHomePage searchPageObject;
    GoogleResultsPage resultsPageObject;

    // Steps
    @Given("User can access google search page")
    public void userCanAccessGoogleSearchPage() {
        searchPageObject = new GoogleHomePage(driver);
        driver.navigate().to(HOME_URL);
        Assert.assertEquals(driver.getCurrentUrl(), HOME_URL);
    }

    @When("User entered search {string} in the search input field")
    public void userEnteredSearchKeywordInTheSearchInputField(String key) throws InterruptedException {
        searchPageObject.setSearchBoxTxt(key);
        Thread.sleep(500);
    }

    @Then("Suggested results returned matches typed {string}")
    public void suggestedResultsReturnedMatchesTypedKeyword(String key) {
        List<WebElement> returnedResultTxt = searchPageObject.getSuggestedSearchResult();
        String firstSuggestedElement = returnedResultTxt.get(0).getText();
        Assert.assertTrue(firstSuggestedElement.contains(key.toLowerCase()));
    }

    @When("User click search without enter any search keyword")
    public void userClickSearchWithoutEnterAnySearchKeyword() {
        searchPageObject.submitSearch();
    }

    @Then("Check that nothing was happened and user still in the search page")
    public void checkThatNothingWasHappenedAndUserStillInTheSearchPage() {
        Assert.assertEquals(HOME_URL, driver.getCurrentUrl());
    }

    @And("Suggested results returned with values")
    public void suggestedResultsReturnedWithValues() {
        List<WebElement> returnedResultTxt = searchPageObject.getSuggestedSearchResult();
        Assert.assertFalse(returnedResultTxt.isEmpty());
    }

    @And("User click on the first suggestion link")
    public void userClickOnTheFirstSuggestionLink() {
        List<WebElement> returnedResultTxt = searchPageObject.getSuggestedSearchResult();
        returnedResultTxt.get(0).click();
    }

    @Then("User redirected to search results page")
    public void userRedirectedToSearchResultsPage() throws InterruptedException {
        resultsPageObject = new GoogleResultsPage(driver);
        Thread.sleep(1000);
        Assert.assertTrue(driver.getCurrentUrl().contains("search"));
    }

    @And("User submit search")
    public void userSubmitSearch() {
        searchPageObject.submitSearch();
    }

    @Then("The first result is matching the search {string}")
    public void theFirstResultIsMatchingTheSearch(String key) {
        String firstResultTitle = resultsPageObject.getTitleOfTheFirstResult();
        Assert.assertTrue(firstResultTitle.toLowerCase().contains(key.toLowerCase()));
    }
}
