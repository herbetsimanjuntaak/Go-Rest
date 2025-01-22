@GoRest @Post @updatePost

Feature: Update Post API
  As a user of the GoRest API,
  I want to update the details of an existing post,
  So that I can modify and manage the content of the post as needed.

  Scenario: Update post with valid payload
    Given set PUT request to update the registered post
    And the request body is "ValidUpdatePost.json"
    When sends PUT request
    Then status code should be 200 OK
    And value of response "title" is "Update title Post"
    And value of response "body" is "this post has been updated by Herbet Simanjuntak"
    And response body should match the JSON schema "CreatePostSchema.json"

  Scenario Outline: Update post with an invalid endpoint and invalid id posts
    Given set PUT request for endpoint "posts" and "<id>"
    And the request body is "ValidUpdatePost.json"
    When sends PUT request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"
    Examples:
      | id      |
      | invalid |
      | 676760  |
      | 440680  |

  Scenario: Update post without an authentication token
    Given set PUT request for endpoint "posts" and "187200"
    And the request body is "ValidUpdatePost.json"
    When sends PUT request invalid auth token
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"

  Scenario Outline: Update a posts with an invalid body "<empty title, body, over char limits>"
    Given set PUT request for endpoint "posts" and "187200"
    And the request body is "<Payload>"
    When sends PUT request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "<Field>" is "<Message>"
    And response body should match the JSON schema "CreatePostErrorSchema.json"
    Examples:
      | Payload                      | Field | Message                                 |
      | BodyPostEmptyFieldTitle.json | title | can't be blank                          |
      | BodyPostEmptyFieldBody.json  | body  | can't be blank                          |
      | BodyPostOverCharLimits.json  | body  | is too long (maximum is 500 characters) |
