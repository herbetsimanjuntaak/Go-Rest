@GoRest @CreateUser

Feature: Create Users API

  Scenario: Get list of users with a valid endpoint
    Given set Post request for endpoint "users"
    And the request body is "ValidBodyUser.json"
    When sends Post request
    Then status code should be 201 Created
    And response body should contain a "name"
    And response body should match the JSON schema "user_create_schema.json"