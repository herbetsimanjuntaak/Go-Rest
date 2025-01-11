@GoRest @User @getListUser

Feature: Get List of Users API
  As a user of the GoRest API
  I want to retrieve a list of users
  So that I can view user data

  Scenario: Get list of users with a valid endpoint
    Given set GET request for endpoint "users"
    When sends GET request
    Then status code should be 200 OK
    And response body should contain a "name"
    And response body should match the JSON schema "user_list_schema.json"

  Scenario: Get list of users with invalid endpoint
    Given set GET request for endpoint "userss"
    When sends GET request
    Then status code should be 404 Not Found
    And response body should contain a "Page Not Found | GO REST"

  Scenario Outline: Get list of users with page parameter
    Given set GET request for endpoint "users"
    When sends GET request with "page" parameter set "<Value>"
    Then status code should be 200 OK
    And response body should contain a "name"
    And response body should match the JSON schema "user_list_schema.json"
    Examples:
      | Value |
      | 1     |
      | 5     |
      | 10    |

  Scenario Outline: Get list of users with invalid page parameter
    Given set GET request for endpoint "users"
    When sends GET request with "page" parameter set "<Value>"
    Then status code should be 200 OK
    And response body should contain a "[]"
    Examples:
      | Value |
      | 1000  |
      | 2000  |
      | 5000  |

  Scenario Outline: Get list of users with page and per_page query parameters
    Given set GET request for endpoint "users"
    When sends GET request with "<page>" parameter and "<per_page>"
    Then status code should be 200 OK
    And response body should match the JSON schema "user_list_schema.json"

    Examples:
      | page | per_page |
      | 1    | 1        |
      | 3    | 5        |
      | 15   | 10       |

  Scenario Outline: Get list of users with invalid page and per_page query parameters
    Given set GET request for endpoint "users"
    When sends GET request with "<page>" parameter and "<per_page>"
    Then status code should be 200 OK
    And response body should contain a "[]"

    Examples:
      | page | per_page |
      | 499  | 1999     |
      | 500  | 2000     |
      | 501  | 2001     |