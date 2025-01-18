package steps.users;

import helper.JavaHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;
import page.GoRestApiPage;

import java.io.File;
import java.util.List;
import java.util.Map;

import static helper.Endpoint.*;
import static helper.ReqBody.*;
import static org.junit.Assert.assertTrue;

public class UserSteps {
    GoRestApiPage goRestApiPage = new GoRestApiPage();
    private Response response;
    private File JsonFile;


    @Given("set GET request for endpoint {string}")
    public void setTheGETRequestForEndpoint(String endpoint) {
        goRestApiPage.setEndpoint(endpoint);
    }

    @Given("set GET request for endpoint {string} and {string}")
    public void setGETRequestForEndpointAnd(String endpoint, String id) {
        goRestApiPage.setEndpoint(endpoint+"/"+id);
    }

    @Given("set Post request for endpoint {string}")
    public void setPostRequestForEndpoint(String endpoint) {
        goRestApiPage.setEndpoint(endpoint);
    }

    @When("sends GET request")
    public void sendsAGETRequest() {
        response = goRestApiPage.sendGetRequest(true);
        System.out.println(response.getBody().asString());
    }

    @When("sends Post request")
    public void sendsPostRequest() {
        response = goRestApiPage.sendPostRequest(JsonFile,true);
        System.out.println(response.getBody().asString());
    }

    @Then("status code should be {int} OK")
    public void statusCodeShouldBeOK(int statusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
    }

    @Then("status code should be {int} Created")
    public void statusCodeShouldBeCreated(int statusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
    }

    @Then("status code should be {int} Not Found")
    public void statusCodeShouldBeNotFound(int statusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
    }

    @Then("status code should be {int} Unauthorized")
    public void statusCodeShouldBeUnauthorized(int statusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
    }

    @Then("status code should be {int} Unprocessable Entity")
    public void statusCodeShouldBeUnprocessableEntity(int statusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
    }

    @Then("status code should be {int} No Content")
    public void statusCodeShouldBeNoContent(int statusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
    }

        @And("response body should contain a {string}")
    public void responseBodyShouldContainA(String message) {
        String respondBody = response.getBody().asString();
        System.out.println(respondBody);
        assertTrue(respondBody.contains(message));
    }

    @And("response body should match the JSON schema {string}")
    public void responseBodyShouldMatchTheJSONSchema(String file) {
        String schemaPath;
        if (file.toLowerCase().contains("user")) {
            schemaPath = JSON_SCHEMA_USER + file;
        } else if (file.toLowerCase().contains("todo")) {
            schemaPath = JSON_SCHEMA_TODO + file;
        } else if (file.toLowerCase().contains("post")) {
            schemaPath = JSON_SCHEMA_POST + file;
        } else if (file.toLowerCase().contains("comment")) {
            schemaPath = JSON_SCHEMA_COMMENT + file;
        } else {
            throw new IllegalArgumentException("Invalid schema file type provided: " + file + ". Please provide a valid schema file name.");
        }

        this.JsonFile = JavaHelper.getJSONFile(schemaPath);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JsonFile));
    }

    @And("the request body is {string}")
    public void theRequestBodyIs(String file) {
        if (file.toLowerCase().contains("user")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_USER + file);
        } else if (file.toLowerCase().contains("todo")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_TODO + file);
        } else if (file.toLowerCase().contains("post")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_POST + file);
        } else if (file.toLowerCase().contains("comment")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_COMMENT + file);
        } else {
            throw new IllegalArgumentException("Invalid file type provided: " + file + ". Please provide a valid JSON file name.");
        }
    }

    @When("sends GET request with {string} parameter set {string}")
    public void sendsGETRequestWithParameterSet(String parameter, String value) {
        response = goRestApiPage.sendGetRequestParam(parameter, value,true);
    }

    @When("sends GET request with {string} parameter and {string}")
    public void sendsGETRequestWithParameterAnd(String page, String perPage) {
        response = goRestApiPage.sendGetRequestParams(page,perPage,true);
        System.out.println(response.getBody().asString());
    }

    @Given("set Get request single user detail registered")
    public void setGetRequestSingleUserDetailRegistered() {
        goRestApiPage.setEndpoint("users");
        response = goRestApiPage.sendGetRequest(true);
        int id = response.jsonPath().getInt("[0].id");
        goRestApiPage.setEndpoint("users/"+id);
    }

    @When("sends Post request invalid auth token")
    public void sendsPostRequestInvalidAuthToken() {
        response = goRestApiPage.sendPostRequest(JsonFile,false);
    }


    @And("response body should contain a list of users")
    public void responseBodyShouldContainAListOfUsers() {
        List<Map<String, Object>> responseList = response.jsonPath().getList("");
        Assert.assertNotNull("The response list is null", responseList);
        boolean allFieldsNotNull = responseList.stream().allMatch(
                item ->
                                item.get("id") != null &&
                                item.get("name") != null &&
                                item.get("email") != null &&
                                item.get("gender") != null
                );
        Assert.assertTrue("One or more fields (id, name, email, gender) are null", allFieldsNotNull);
    }

    @And("value of response for field {string} is {string}")
    public void valueOfResponseForFieldIs(String fieldName, String expectedMessage) {
        List<Map<String, String>> errors = response.jsonPath().getList("");
        boolean messageFound = errors.stream()
                .anyMatch(
                        error -> fieldName.equals(error.get("field")) && expectedMessage.equals(error.get("message")));
        Assert.assertTrue("Expected message not found for field: " + fieldName, messageFound);
    }

    @Given("set PUT request for endpoint {string}")
    public void setPUTRequestForEndpoint(String endpoint) {
        System.out.println("endpoint : " + endpoint);
        goRestApiPage.setEndpoint(endpoint);
    }

    @When("sends PUT request")
    public void sendsPUTRequest() {
        response = goRestApiPage.sendPutRequest(JsonFile,true);
        System.out.println(response.getBody().asString());
    }


    @Given("set PUT request for endpoint {string} and {string}")
    public void setPUTRequestForEndpointAnd(String endpoint, String id) {
        goRestApiPage.setEndpoint(endpoint+"/"+id);
    }

    @Given("set DELETE request for endpoint {string} and {string}")
    public void setDELETERequestForEndpointAnd(String endpoint, String id) {
        goRestApiPage.setEndpoint(endpoint+"/"+id);
    }

    @Given("set PUT request to update the registered user")
    public void setPUTRequestToUpdateTheRegisteredUser() {
        goRestApiPage.setEndpoint("users");
        response = goRestApiPage.sendGetRequest(true);
        int id = response.jsonPath().getInt("[9].id");
        goRestApiPage.setEndpoint("users/"+id);
    }

    @When("sends PUT request invalid auth token")
    public void sendsPUTRequestInvalidAuthToken() {
        response = goRestApiPage.sendPutRequest(JsonFile,false);
        System.out.println(response.getBody().asString());
    }

    @Given("set DELETE request to delete the registered user")
    public void setDELETERequestToDeleteTheRegisteredUser() {
        goRestApiPage.setEndpoint("users");
        response = goRestApiPage.sendGetRequest(true);
        int id = response.jsonPath().getInt("[9].id");
        goRestApiPage.setEndpoint("users/"+id);
    }

    @When("sends DELETE request")
    public void sendsDELETERequest() {
        response = goRestApiPage.sendDeleteRequest(true);
    }

    @When("sends DELETE request invalid auth token")
    public void sendsDELETERequestInvalidAuthToken() {
        response = goRestApiPage.sendDeleteRequest(false);
    }
}

