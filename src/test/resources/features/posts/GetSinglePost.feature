@GoRest @Posts @getSinglePosts

Feature: Get Posts by ID
  As a user of the GoRest API
  I want to retrieve a specific post by its ID
  So that I can view detailed information about the post

  Scenario: Get single post detail registered
    Given set Get request single post detail registered
    When sends GET request
    Then status code should be 200 OK
    And response body should match the JSON schema "SinglePostSchema.json"

  Scenario Outline: Get single post detail unregistered id
    Given set GET request for endpoint "posts" and "<id>"
    When sends GET request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"
    Examples:
      | id      |
      | 7627697 |
      | 7123127 |
      | 4356573 |
      | 3454354 |

