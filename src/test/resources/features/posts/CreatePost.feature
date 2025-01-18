@GoRest @User @createPost

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