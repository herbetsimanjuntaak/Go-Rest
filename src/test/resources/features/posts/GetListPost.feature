@GoRest @Posts @getListPost

Feature: Get List of Posts API
  As a user of the GoRest API
  I want to retrieve a list of posts
  So that I can view post data

  Scenario: Get List of Posts Successfully
    Given set GET request for endpoint "posts"
    When sends GET request
    Then status code should be 200 OK
    And response body should contain a list of posts users
    And response body should match the JSON schema "ListPostSchema.json"

  Scenario: Get list of posts with invalid endpoint
    Given set GET request for endpoint "postss"
    When sends GET request
    Then status code should be 404 Not Found
    And response body should contain a "Page Not Found | GO REST"

  Scenario Outline: Get list of posts with page parameter
    Given set GET request for endpoint "posts"
    When sends GET request with "page" parameter set "<Value>"
    Then status code should be 200 OK
    And response body should contain a list of posts users
    And response body should match the JSON schema "ListPostSchema.json"
    Examples:
      | Value |
      | 1     |
      | 5     |
      | 10    |

  Scenario Outline: Get list of posts with invalid page parameter
    Given set GET request for endpoint "posts"
    When sends GET request with "page" parameter set "<Value>"
    Then status code should be 200 OK
    And response body should contain a "[]"
    Examples:
      | Value |
      | 1000  |
      | 2000  |
      | 5000  |

  Scenario Outline: Get list of posts with page and per_page parameters
    Given set GET request for endpoint "posts"
    When sends GET request with "<page>" parameter and "<per_page>"
    Then status code should be 200 OK
    And response body should match the JSON schema "ListPostSchema.json"

    Examples:
      | page | per_page |
      | 1    | 1        |
      | 3    | 5        |
      | 15   | 10       |

  Scenario Outline: Get list of posts with invalid page and per_page parameters
    Given set GET request for endpoint "posts"
    When sends GET request with "<page>" parameter and "<per_page>"
    Then status code should be 200 OK
    And response body should contain a "[]"

    Examples:
      | page | per_page |
      | 499  | 1999     |
      | 500  | 2000     |
      | 501  | 2001     |