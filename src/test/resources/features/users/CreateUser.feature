@GoRest @User @createUser

Feature: Create User API
  As a user of the GoRest API
  I want to create a new user
  So that I can store the details of that specific user


  Scenario: Successfully create a new user with valid data
    Given set Post request for endpoint "users"
    And the request body is "ValidBodyUser.json"
    When sends Post request
    Then status code should be 201 Created
    And value of response "name" is "herbet created"
    And value of response "email" is "herbetcreated@example.com"
    And value of response "gender" is "male"
    And value of response "status" is "active"
    And response body should match the JSON schema "CreateUserSchema.json"

  Scenario Outline: Create a new user with an invalid endpoint
    Given set Post request for endpoint "<Endpoint>"
    And the request body is "ValidBodyUser.json"
    When sends Post request
    Then status code should be 404 Not Found
    And response body should contain a "Page Not Found | GO REST"
    Examples:
      | Endpoint |
      | userss   |
      | @#MD12   |
      | 440680   |

  Scenario: Create a new user with an invalid auth token
    Given set Post request for endpoint "users"
    And the request body is "ValidBodyUser.json"
    When sends Post request invalid auth token
    Then status code should be 401 Unauthorized
    And value of response "message" is "Authentication failed"

  Scenario: Create a new user with all fields empty
    Given set Post request for endpoint "users"
    And the request body is "EmptyAllFieldsUser.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "name" is "can't be blank"
    And value of response for field "email" is "can't be blank"
    And value of response for field "gender" is "can't be blank, can be male of female"
    And value of response for field "status" is "can't be blank"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario: Create a new user with an empty name
    Given set Post request for endpoint "users"
    And the request body is "EmptyNameUser.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "name" is "can't be blank"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario: Create a user with a name longer than 200 characters
    Given set Post request for endpoint "users"
    And the request body is "UserWithLongName.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "name" is "is too long (maximum is 200 characters)"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario: Create a new user with an empty email
    Given set Post request for endpoint "users"
    And the request body is "EmptyEmailUser.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "email" is "can't be blank"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario: Create a user with an already registered email
    Given set Post request for endpoint "users"
    And the request body is "ValidBodyUserWithEmailRegistered.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "email" is "has already been taken"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario Outline: Create a new user with an invalid email (missing @)
    Given set Post request for endpoint "users"
    And the request body is "<Payload>"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "email" is "is invalid"
    And response body should match the JSON schema "CreateUserErrorSchema.json"
    Examples:
      | Payload                       |
      | InvalidEmailUser.json         |
      | InvalidEmailNoDomainUser.json |

  Scenario: Create a new user with an empty gender
    Given set Post request for endpoint "users"
    And the request body is "EmptyGenderUser.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "gender" is "can't be blank, can be male of female"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario: Create a new user with a gender that is not classified as male or female.
    Given set Post request for endpoint "users"
    And the request body is "InvalidGenderUser.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "gender" is "can't be blank, can be male of female"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario: Create a new user with an empty status
    Given set Post request for endpoint "users"
    And the request body is "EmptyStatusUser.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "status" is "can't be blank"
    And response body should match the JSON schema "CreateUserErrorSchema.json"

  Scenario: Create a new user with a status that is not classified as active or inactive.
    Given set Post request for endpoint "users"
    And the request body is "InvalidStatusUser.json"
    When sends Post request
    Then status code should be 422 Unprocessable Entity
    And value of response for field "status" is "can't be blank"
    And response body should match the JSON schema "CreateUserErrorSchema.json"









