@GoRest @Post @createPost

Feature: Create Post API
  As a user of the GoRest API
  I want to create a new post
  So that I can store the details of that specific post


  Scenario: Successfully create a new posts with valid data
    Given set Post request for endpoint "posts"
    And the request body is "ValidBodyPost.json"
    When sends Post request
    Then status code should be 201 Created
    And value of response "title" is "Create Post"
    And value of response "body" is "this post created by Herbet Simanjuntak"
    And response body should match "body" is "this post created by Herbet Simanjuntak"
    And response body should match the JSON schema "CreatePostSchema.json"


  Scenario: Create a new posts with an invalid auth token
    Given set Post request for endpoint "posts"
    And the request body is "ValidBodyPost.json"
    When sends Post request invalid auth token
    Then status code should be 401 Unauthorized
    And value of response "message" is "Authentication failed"

  Scenario Outline: Create a new posts with an invalid endpoint
    Given set Post request for endpoint "<Endpoint>"
    And the request body is "ValidBodyPost.json"
    When sends Post request
    Then status code should be 404 Not Found
    And response body should contain a "Page Not Found | GO REST"
    Examples:
      | Endpoint |
      | postsss  |
      | invalid  |
      | 4355464  |

  Scenario Outline: Create a new posts with an invalid body "<empty user_id>"
    Given set Post request for endpoint "posts"
    And the request body is "<Payload>"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "<Field>" is "<Message>"
    And response body should match the JSON schema "CreatePostErrorSchema.json"
    Examples:
      | Payload                      | Field | Message                                 |
      | BodyPostEmptyFieldID.json    | user  | must exist                              |
      | BodyPostEmptyFieldTitle.json | title | can't be blank                          |
      | BodyPostEmptyFieldBody.json  | body  | can't be blank                          |
      | BodyPostAllFieldEmpty.json   | user  | must exist                              |
      | BodyPostOverCharLimits.json  | body  | is too long (maximum is 500 characters) |