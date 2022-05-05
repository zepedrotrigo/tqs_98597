Feature: Example
    Scenario: Search for world data in a given day
        When I load the webpage 'http://localhost:3000/'
        And I choose a date on the calendar
        And I click the search button
        Then data field with id 'last-updated-text' is loaded from the API

    Scenario: Search for Regional data for a given Country and a given day
        When I load the webpage 'http://localhost:3000/'
        And I click tab with id 'side-bar-opt-1' in the sidebar
        And I choose a date on the calendar
        And I choose 'Poland' from the select element
        And I click the search button
        Then data field with id 'country-population-val' is loaded from the API

    Scenario: See Cache stats
        When I load the webpage 'http://localhost:3000/'
        And I click tab with id 'side-bar-opt-2' in the sidebar
        Then data field with id 'cache-requests-val' is loaded from the API