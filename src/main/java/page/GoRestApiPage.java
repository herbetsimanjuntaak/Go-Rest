package page;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static helper.Endpoint.GO_REST_BASE_URL;

public class GoRestApiPage {
    public static Response response;

    public void setEndpoint(String endpoint) {
        RestAssured.baseURI = GO_REST_BASE_URL + endpoint;
    }

    public static RequestSpecification setUpHeaders() {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 2259048ef8b50ff36a730b1d6dcd5962cd3c9aa5210a7b4ca68b92c36d8abfc9");
    }

    public Response sendGetRequest() {
        response = setUpHeaders()
                .when()
                .get()
                .then()
                .extract()
                .response();
        return response;
    }

    public Response sendGetRequestParam(String parameter, String value) {
        response = setUpHeaders()
                .queryParam(parameter, value)
                .get()
                .then()
                .extract()
                .response();
        return response;
    }

    public Response sendGetRequestParams(String page, String per_page) {
        response = setUpHeaders()
                .param("page", page)
                .param("per_page", per_page)
                .when()
                .get()
                .then()
                .extract()
                .response();
        return response;
    }

    public Response sendPostRequest(File requestBody) {
        response = setUpHeaders()
                .body(requestBody)
                .when()
                .post()
                .then()
                .extract()
                .response();
        return response;
    }

    public Response sendPutRequest(File requestBody, String endpoint) {
        response = setUpHeaders()
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
        return response;
    }

    public Response sendDeleteRequest(String endpoint) {
        response = setUpHeaders()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
        return response;
    }


}
