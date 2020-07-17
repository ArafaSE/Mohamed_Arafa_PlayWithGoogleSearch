Feature: Google Suggestion Search
  In order to test google home search page
  As a normal user
  I want to make sure google suggestion results are working

  Scenario Outline: Verify that when user start typing word in text box it should suggest words that matches typed keyword
    Given User can access google search page
    When User entered search "<keyword>" in the search input field
    Then Suggested results returned matches typed "<keyword>"

    Examples:
      | keyword     |
      | MacBook Pro |

  Scenario Outline: Verify that user can make search using different languages
    Given User can access google search page
    When User entered search "<keyword>" in the search input field
    Then Suggested results returned matches typed "<keyword>"

    Examples:
      | keyword      |
      | لابتوب       |
      | ラップトップ       |
      | bärbar dator |
      | dizüstü      |

  Scenario: Verify that If the user entered nothing and just click search the nothing should occur
    Given User can access google search page
    When User click search without enter any search keyword
    Then Check that nothing was happened and user still in the search page

  Scenario Outline: Verify that user can open the first suggested result link
    Given User can access google search page
    When User entered search "<keyword>" in the search input field
    And Suggested results returned with values
    And User click on the first suggestion link
    Then User redirected to search results page

    Examples:
      | keyword     |
      | MacBook Pro |