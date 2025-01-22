@GoRest @Comments @CreateComments

Feature: Create New Comment API
  As a user of the GoRest API
  I want to create a new comment
  So that I can associate feedback or remarks with a post

  post_id required to determine which post will be commented on.


  Scenario: Successfully create a new Comment with valid data
    Given set Post request for endpoint "comments"
    And the request body is "ValidBodyComment.json"
    When sends Post request
    Then status code should be 201 Created
    And value of response "name" is "Herbet Simanjuntak"
    And value of response "email" is "herbet@test"
    And response body should match "body" is "herbet create a comments here"
    And response body should match the JSON schema "CreateCommentSchema.json"


  Scenario: Create a new comment without an invalid auth token
    Given set Post request for endpoint "posts"
    And the request body is "ValidBodyComment.json"
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

  Scenario Outline: Create a new posts with an invalid body "<empty user_id,title, body, over char limits>"
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