package api.endpoints;

import api.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommentsEndpoint extends TestBase {

    public Response getCommentsFromPost(final int postId) {
        return given()
                    .queryParam("postId", postId)
               .when()
                    .get("/comments");
    }
}
