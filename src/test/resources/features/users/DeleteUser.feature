@GoRest @User @deleteUser

Feature: Delete User API
  As a user of the GoRest API
  I want to delete a specific user
  So that the user is removed from the system

  Scenario: Delete a user successfully
    Given set DELETE request to delete the registered user
    When sends DELETE request
    Then status code should be 204 No Content

  Scenario: Delete user with an invalid auth token
    Given set DELETE request for endpoint "users" and "7632711"
    When sends DELETE request invalid auth token
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"

  Scenario Outline: Delete user with an invalid endpoint
    Given set DELETE request for endpoint "users" and "<id>"
    When sends DELETE request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"
    Examples:
      | id      |
      | 7632720 |
      | 7568530 |
      | 4406800 |