@GoRest @CreateUser

Feature: Create Users API

  Scenario: Get list of users with a valid endpoint
    Given set Post request for endpoint "posts"
    And the request body is "ValidBodyPost.json"
    When sends Post request
    Then status code should be 201 Created
    And value of response "title" is "Create Post"
    And value of response "body" is "this post created by Herbet Simanjuntak"
    And response body should match the JSON schema "CreatePostSchema.json"