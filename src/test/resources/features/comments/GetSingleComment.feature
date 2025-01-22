@GoRest @Comments @getSingleComments

Feature: Get Comment by ID
  As a user of the GoRest API
  I want to retrieve a specific comment by its ID
  So that I can view detailed information about the comment

  Scenario: Get single comment detail registered
    Given set Get request single comment detail registered
    When sends GET request
    Then status code should be 200 OK
    And response body should match the JSON schema "SingleCommentSchema.json"

  Scenario Outline: Get single post detail unregistered id
    Given set GET request for endpoint "comments" and "<id>"
    When sends GET request
    Then status code should be 404 Not Found
    And value of response "message" is "Resource not found"
    Examples:
      | id     |
      | 187207 |
      | 187204 |
      | 184073 |
      | 184337 |

