  Feature: Google Return Search Results
  In order to test google results page
  As a normal user
  I want to make sure Google search results are accurate

  Scenario Outline: Verify that the first result returned is matching the search keyword
    Given User can access google search page
    When User entered search "<keyword>" in the search input field
    And User submit search
    And User redirected to search results page
    Then The first result is matching the search "<keyword>"

    Examples:
      | keyword     |
      | MacBook pro |