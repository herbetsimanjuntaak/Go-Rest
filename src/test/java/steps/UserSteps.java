package steps;

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

import static helper.Endpoint.*;
import static helper.ReqBody.REQ_BODY_POST;
import static helper.ReqBody.REQ_BODY_USER;

public class UserSteps {
    GoRestApiPage goRestApiPage = new GoRestApiPage();
    private Response response;
    private File JsonFile;

    @Given("set GET request for endpoint {string}")
    public void setTheGETRequestForEndpoint(String endpoint) {
        goRestApiPage.setEndpoint(endpoint);
        System.out.println(endpoint);
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
        response = goRestApiPage.sendGetRequest();
        System.out.println(response.getBody().asString());
    }

    @When("sends Post request")
    public void sendsPostRequest() {
        System.out.println(JsonFile.getAbsolutePath());
        response = goRestApiPage.sendPostRequest(JsonFile);
        System.out.println(response.getBody().asString());
    }

    @Then("status code should be {int} OK")
    public void statusCodeShouldBeOK(int statusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
    }


    @And("response body should contain a {string}")
    public void responseBodyShouldContainA(String text) {
        String respondBody = response.getBody().asString();
        System.out.println(respondBody);
        Assert.assertTrue(respondBody.contains(text));
    }

    @And("response body should match the JSON schema {string}")
    public void responseBodyShouldMatchTheJSONSchema(String file) {
        if (file.toLowerCase().contains("user")) {
            this.JsonFile = JavaHelper.getJSONFile(JSON_SCHEMA_USER + file);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JsonFile));
        } else if (file.toLowerCase().contains("todo")) {
            this.JsonFile = JavaHelper.getJSONFile(JSON_SCHEMA_TODO + file);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JsonFile));
        } else if (file.toLowerCase().contains("post")) {
            this.JsonFile = JavaHelper.getJSONFile(JSON_SCHEMA_POST + file);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JsonFile));
        } else if (file.toLowerCase().contains("comment")) {
            this.JsonFile = JavaHelper.getJSONFile(JSON_SCHEMA_POST + file);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JsonFile));
        } else {
            System.out.println("input valid url :)");
        }
    }

    @And("the request body is {string}")
    public void theRequestBodyIs(String file) {

        if (file.toLowerCase().contains("user")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_USER + file);
        } else if (file.toLowerCase().contains("todo")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_USER + file);
        } else if (file.toLowerCase().contains("post")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_POST + file);
        } else if (file.toLowerCase().contains("comment")) {
            this.JsonFile = JavaHelper.getJSONFile(REQ_BODY_USER + file);
        } else {
            System.out.println("input valid url :)");
        }
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


    @When("sends GET request with {string} parameter set {string}")
    public void sendsGETRequestWithParameterSet(String parameter, String value) {
        response = goRestApiPage.sendGetRequestParam(parameter, value);
    }

    @When("sends GET request with {string} parameter and {string}")
    public void sendsGETRequestWithParameterAnd(String page, String perPage) {
        response = goRestApiPage.sendGetRequestParams(page,perPage);
        System.out.println(response.getBody().asString());
    }


}
