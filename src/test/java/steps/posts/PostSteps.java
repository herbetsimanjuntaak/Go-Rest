package steps.posts;

import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.junit.Assert;
import page.GoRestApiPage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;

public class PostSteps {

    @And("value of response {string} is {string}")
    public void valueOfResponseIs(String jsonPath, String expectedJsonPathValue) {
        Response response = GoRestApiPage.response;

        String jsonPathValue = response.jsonPath().get(jsonPath);
        System.out.println(jsonPathValue);
        System.out.println("jsonPathValue " + jsonPath + " : " + jsonPathValue);
        Assert.assertEquals(expectedJsonPathValue, jsonPathValue);
    }

    @And("response body should contain a list of posts users")
    public void responseBodyShouldContainAListOfPostsUsers() {
        List<Map<String, Object>> responseList = GoRestApiPage.response.jsonPath().getList("");
        Assert.assertNotNull("The response list is null", responseList);
        boolean allFieldsNotNull = responseList.stream().allMatch(
                item ->
                        item.get("id") != null &&
                                item.get("user_id") != null &&
                                item.get("title") != null &&
                                item.get("body") != null
        );
        Assert.assertTrue("One or more fields (id, user_id, title, body) are null", allFieldsNotNull);

    }

    @And("response body should match {string} is {string}")
    public void responseBodyShouldMatchIs(String jsonPath, String expectedJsonPathValue) {
        Response response = GoRestApiPage.response;
        response.then().body(jsonPath, containsString(expectedJsonPathValue));
    }
}
