package steps.posts;

import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.junit.Assert;
import page.GoRestApiPage;

public class PostSteps {

    private Response response;

    @And("value of response {string} is {string}")
    public void valueOfResponseIs(String jsonPath, String expectedJsonPathValue) {
        response = GoRestApiPage.response;

        String jsonPathValue = response.jsonPath().get(jsonPath);
        System.out.println(jsonPathValue);
        System.out.println("jsonPathValue " + jsonPath + " : " + jsonPathValue);
        Assert.assertEquals(expectedJsonPathValue, jsonPathValue);
    }
}
