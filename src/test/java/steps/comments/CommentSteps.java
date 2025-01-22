package steps.comments;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.Assert;
import page.GoRestApiPage;

import java.util.List;
import java.util.Map;

public class CommentSteps {
    GoRestApiPage goRestApiPage = new GoRestApiPage();
    private Response response;

    @And("response body should contain a list of comments users")
    public void responseBodyShouldContainAListOfCommentsUsers() {
        List<Map<String, Object>> responseList = GoRestApiPage.response.jsonPath().getList("");
        Assert.assertNotNull("The response list is null", responseList);
        boolean allFieldsNotNull = responseList.stream().allMatch(
                item ->
                        item.get("id") != null &&
                                item.get("post_id") != null &&
                                item.get("name") != null &&
                                item.get("email") != null &&
                                item.get("body") != null
        );
        Assert.assertTrue("One or more fields (id, post_id, name, email, body) are null", allFieldsNotNull);
    }

    @Given("set Get request single comment detail registered")
    public void setGetRequestSingleCommentDetailRegistered() {
        goRestApiPage.setEndpoint("comments");
        response = goRestApiPage.sendGetRequest(true);
        int id = response.jsonPath().getInt("[9].id");
        goRestApiPage.setEndpoint("comments/" + id);
    }
}
