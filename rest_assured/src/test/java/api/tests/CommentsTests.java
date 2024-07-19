package api.tests;

import api.TestBase;
import api.assertions.ApiAssertions;
import api.endpoints.CommentsEndpoint;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class CommentsTests extends TestBase {

    private CommentsEndpoint commentsApi;
    private ApiAssertions apiAssertions;

    @BeforeClass
    public void init() {
        commentsApi = new CommentsEndpoint();
        apiAssertions = new ApiAssertions();
    }

    @Test
    public void getCommentsFromExistingPost() {
        Response response = commentsApi.getCommentsFromPost(1);
        File responseSchema = new File("resources/commentSchema.json");

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");
        apiAssertions.validateJsonSchema(response, responseSchema);
    }

    @Test
    public void getCommentsFromUnexistingPost() {
        Response response = commentsApi.getCommentsFromPost(0);

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");
    }
}
