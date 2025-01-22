@GoRest @Post @deletePost

Feature: Delete Post API
  As a user of the GoRest API,
  I want to delete an existing post,
  So that I can remove posts that are no longer needed.

  Scenario: Delete a posts successfully
    Given set DELETE request to delete the registered post
    When sends DELETE request
    Then status code should be 204 No Content

  Scenario: Delete Existing posts Multiple Times
    Given set DELETE request to delete the registered post
    And sends DELETE request
    And status code should be 204 No Content
    When sends DELETE request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"

  Scenario: Delete post with Missing post ID
    Given set DELETE request for endpoint "posts" and ""
    When sends DELETE request
    Then status code should be 404 Not Found
    And response body should contain a "Page Not Found | GO REST"

  Scenario Outline: Delete post with an invalid ID
    Given set DELETE request for endpoint "posts" and "<id>"
    When sends DELETE request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"
    Examples:
      | id      |
      | 7632720 |
      | 7568530 |
      | 4406800 |


