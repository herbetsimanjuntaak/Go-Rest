@GoRest @User @updateUser

Feature: Update User API
  As a user of the GoRest API
  I want to update an existing user
  So that I can modify the details of that specific user

  Scenario: Update user with valid payload
    Given set PUT request to update the registered user
    And the request body is "ValidUpdateUser.json"
    When sends PUT request
    Then status code should be 200 OK
    And value of response "name" is "herbet updated"
    And value of response "email" is "herbetupdated@example"
    And value of response "gender" is "male"
    And value of response "status" is "active"
    And response body should match the JSON schema "UpdateUserSchema.json"

  Scenario Outline: Update user with an invalid endpoint
    Given set PUT request for endpoint "users" and "<id>"
    And the request body is "ValidUpdateUser.json"
    When sends PUT request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"
    Examples:
      | id     |
      | 13132  |
      | 67676  |
      | 440680 |

  Scenario: Update user with an invalid auth token
    Given set PUT request for endpoint "users" and "7632711"
    And the request body is "ValidUpdateUser.json"
    When sends PUT request invalid auth token
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"

  Scenario: Update user with an invalid email (missing @)
    Given set PUT request to update the registered user
    And the request body is "InvalidEmailUser.json"
    When sends PUT request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "email" is "is invalid"
    And response body should match the JSON schema "UpdateUserErrorSchema.json"

  Scenario: Update user with empty name
    Given set PUT request to update the registered user
    And the request body is "EmptyNameUser.json"
    When sends PUT request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "name" is "can't be blank"
    And response body should match the JSON schema "UpdateUserErrorSchema.json"

  Scenario: Update user with email already registered
    Given set PUT request to update the registered user
    And the request body is "ValidBodyUserWithEmailRegistered.json"
    When sends PUT request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "email" is "has already been taken"
    And response body should match the JSON schema "UpdateUserErrorSchema.json"

  Scenario Outline: Update user with invalid status
    Given set PUT request to update the registered user
    And the request body is "<Payload>"
    When sends PUT request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "status" is "can't be blank"
    And response body should match the JSON schema "UpdateUserErrorSchema.json"
    Examples:
      | Payload                |
      | InvalidStatusUser.json |
      | EmptyStatusUser.json   |


