@GoRest @User @getSingleUser

Feature: Get Single User API
  As a user of the GoRest API
  I want to retrieve a single user's data
  So that I can view the details of that specific user

  Scenario: Get list of users with a valid endpoint
    Given set GET request for endpoint "users" and "7627657"
    When sends GET request
    Then status code should be 200 OK
    And response body should contain a "7627657"
    And response body should match the JSON schema "SingleUserSchema.json"

  Scenario Outline: Get single user detail unregistered id
    Given set GET request for endpoint "users" and "<id>"
    When sends GET request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"
    Examples:
      | id      |
      | 7627697 |
      | 7123127 |
      | 4356573 |
      | 3454354 |

