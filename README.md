# Functional API Automation Testing Using RestAssured and Cucumber on GoRest

Repository for Learning Software Quality Assurance - API Automation Using RestAssured with Cucumber

<p align="center">
    <img src="https://github.com/user-attachments/assets/8d9750ec-ddb9-4a01-9863-37b5ecd25f2f" alt="cucumber" width="500" style="margin-left: 10px;">
</p>

This is a personal project focused on practicing functional API automation testing on the GoRest API using RestAssured
with Cucumber. The goal of this project is to enhance skills in API End-to-End (E2E) testing, covering critical API
workflows such as creating a user, retrieving user details, updating user information, deleting a user, and validating
error responses.

The project leverages Cucumber for structuring the test scenarios in Gherkin syntax, RestAssured for making HTTP
requests and handling API responses, JUnit for managing the test framework, and JSON Schema Validator for ensuring
response structure compliance.

## 🚀 Project Overview

The project scenarios include:

1. User.feature:

- **Create User**: Automate the process of creating a user with valid data.
- **Retrieve User**: Automate the scenario of retrieving user details by ID or listing all users.
- **Update User**: Automate the scenario of updating user details with valid and invalid data.
- **Delete User**: Automate the scenario of deleting a user and verifying the response.
- **Negative Flows**: Automate scenarios for handling errors like creating a user with missing/invalid fields or trying
  to retrieve a non-existing user.

2. Post.feature:

- **Create Post**: Automate the process of creating a new post for an existing user.
- **Retrieve Posts**: Automate scenarios to retrieve posts by user ID or list all posts.
- **Update Post**: Automate scenarios to update post details with valid and invalid data.
- **Delete Post**: Automate scenarios to delete a post by ID.
- **Negative Flows**: Automate scenarios for handling errors like creating a post with missing/invalid fields or trying
  to retrieve a non-existing post.

3. Comment.feature:

- **Create Comments**: Automate the scenario to add a comment to a post.
- **Retrieve Comments**: Automate scenarios to retrieve comments by post ID or list all comments.
- **Update Comments**: Automate scenarios to update post details with valid and invalid data.
- **Delete Comments**: Automate scenarios to delete a comment by ID.
- **Negative Comments**: Automate scenarios for invalid operations, such as commenting on a non-existing post.

4. Todo.feature:

- **Create Todo**: Automate the process of creating a new to-do task for a user.
- **Retrieve Todo**: Automate scenarios to retrieve to-dos by user ID or list all to-dos.
- **Update Todo**: Automate the scenario of updating a to-do task with valid/invalid data.
- **Delete Todo**: Automate the scenario to delete a to-do task by ID.
- **Negative Todo**: Automate scenarios for handling errors like creating a to-do with missing/invalid fields or trying
  to retrieve a non-existing to-do.

## 🛠️ Tools Used

- **RestAssured**: A powerful Java library used for testing RESTful APIs. It is utilized to send HTTP requests and
  validate API responses for GoRest endpoints
- **Cucumber Framework**: The testing framework used to write scenarios in Gherkin syntax for API automation.
- **JUnit**:A widely used testing framework for running tests. It is integrated with Cucumber to execute API test
  scenarios.
- **JSON Schema Validator**: A tool to validate the structure of JSON responses, ensuring they match predefined schemas.
- **Gradle**: A build automation tool used to manage dependencies, compile code, and run tests.
- **GoRest API**: The target API used for practicing API automation testing scenarios (including users, posts, comments,
  and todos).
- **Postman**: A tool used for manual testing of GoRest API endpoints before automating the tests with RestAssured.

## 🚀 Installation and Running

Follow these steps to set up the project locally and run the tests:

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java 11+**: [Download Java](https://adoptopenjdk.net/)
- **Gradle**: [Install Gradle](https://gradle.org/install/)
- **JUnit**: Included as a dependency in the project.
- **RestAssured**: Also included in the dependencies.
- **Cucumber**: Cucumber dependencies are included for running Gherkin-based tests.

### 1. Clone the Repository

Clone the repository to your local machine using Git:

```
git clone https://github.com/your-username/gorest-api-automation.git
```

### 2. Clone the Repository

Navigate to the project directory and install the required dependencies:

   ```
cd gorest-api-automation
   ```

   ```
   gradle build
   ```

### 3. Run the Tests

   ```
   gradle test
   ```

### 4. View Test Results

Test results will be displayed in the console, and a detailed HTML report will be generated in the
build/reports/tests/test/index.html file.

## 🔗 Resources

- **GoRest API**: [Visit Website](https://gorest.co.in/#google_vignette)
- **Test Case Documentation
  **: [GoRest API Test Cases](https://docs.google.com/spreadsheets/d/1NdNp-IXuZAxFatbhTGYw4l08k32NiY2zsdpMQRRJlWA/edit?gid=0#gid=0)

## 🙏 Acknowledgements

Thank you for exploring this project! Your feedback, contributions, and suggestions are highly appreciated as they help
me improve and grow my skills.

Feel free to reach out, and happy testing!
