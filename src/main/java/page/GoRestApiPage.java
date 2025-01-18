package page;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static helper.Endpoint.GO_REST_BASE_URL;
import static io.restassured.RestAssured.given;

public class GoRestApiPage {
    public static Response response;

    /**
     * Sets the base URI for RestAssured requests by appending the provided endpoint
     * to the base URL (GO_REST_BASE_URL).
     *
     * @param endpoint The endpoint to be appended to the base URL.
     */
    public void setEndpoint(String endpoint) {
        RestAssured.baseURI = GO_REST_BASE_URL + endpoint;
    }

    /**
     * Sets up the default headers for the request, including Content-Type, Accept,
     * and an optional Authorization header.
     *
     * @param withAuth Specifies whether to include the Authorization header.
     * @return A RequestSpecification with the configured headers.
     */
    public static RequestSpecification setUpHeaders(boolean withAuth) {
        RequestSpecification request = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json");
        if (withAuth) {
            request.header("Authorization", "Bearer 2259048ef8b50ff36a730b1d6dcd5962cd3c9aa5210a7b4ca68b92c36d8abfc9");
        }
        return request;
    }


    /**
     * Sends a GET request with a query parameter and returns the response.
     *
     * @param parameter The query parameter.
     * @param value     The value for the query parameter.
     * @param withAuth  Whether to include the Authorization header.
     * @return The response from the GET request.
     */
    public Response sendGetRequestParam(String parameter, String value, boolean withAuth) {
        response = setUpHeaders(withAuth)
                .queryParam(parameter, value)
                .get()
                .then()
                .extract()
                .response();
        return response;
    }

    /**
     * Sends a GET request with multiple query parameters and returns the response.
     *
     * @param page     The page number for pagination.
     * @param per_page The number of items per page.
     * @param withAuth Whether to include the Authorization header.
     * @return The response from the GET request.
     */
    public Response sendGetRequestParams(String page, String per_page, boolean withAuth) {
        response = setUpHeaders(withAuth)
                .param("page", page)
                .param("per_page", per_page)
                .when()
                .get()
                .then()
                .extract()
                .response();
        return response;
    }

    /**
     * Sends a GET request with predefined headers and returns the response.
     *
     * @param withAuth Whether to include the Authorization header.
     * @return The response from the GET request.
     */
    public Response sendGetRequest(boolean withAuth) {
        response = setUpHeaders(withAuth)
                .when()
                .get()
                .then()
                .extract()
                .response();
        return response;
    }

    /**
     * Sends a POST request with a request body and returns the response.
     *
     * @param requestBody The file containing the body of the POST request.
     * @param withAuth    Whether to include the Authorization header.
     * @return The response from the POST request.
     */
    public Response sendPostRequest(File requestBody, boolean withAuth) {
        response = setUpHeaders(withAuth)
                .body(requestBody)
                .when()
                .post()
                .then()
                .extract()
                .response();
        return response;
    }

    /**
     * Sends a PUT request with a request body and returns the response.
     *
     * @param requestBody The file containing the body of the PUT request.
     * @param withAuth    Whether to include the Authorization header.
     * @return The response from the PUT request.
     */
    public Response sendPutRequest(File requestBody, boolean withAuth) {
        response = setUpHeaders(withAuth)
                .body(requestBody)
                .when()
                .put()
                .then()
                .extract()
                .response();
        return response;
    }

    /**
     * Sends a DELETE request with or without authorization headers.
     *
     * @param withAuth Specifies whether the request requires authorization.
     * @return The Response object containing the server's response.
     */
    public Response sendDeleteRequest(boolean withAuth) {
        response = setUpHeaders(withAuth)
                .when()
                .delete()
                .then()
                .extract()
                .response();
        return response;
    }


}
