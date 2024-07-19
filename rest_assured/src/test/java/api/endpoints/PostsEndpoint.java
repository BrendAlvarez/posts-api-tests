package api.endpoints;

import api.TestBase;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.File;


public class PostsEndpoint extends TestBase  {

    public Response getAllPosts() {
        return given()
               .when()
                    .get("/posts");
    }

    public Response getPostById(final int postId) {
         return given()
                    .pathParam("postId", postId)
                .when()
                    .get("/posts/{postId}");

         /*.then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .assertThat()
                .body(
                        "userId", equalTo(1),
                        "id", equalTo(1),
                        "title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")

                )
                 .log().all()
                 .extract().path("body"); */
        // System.out.println(bodyValue);
    }

    public Response getPostComments(final int postId) {
        return given()
                    .pathParam("postId", postId)
               .when()
                    .get("/posts/{postId}/comments");
    }

    public Response createPost(File body) {
        return given()
                    .contentType(ContentType.JSON)
                    .body(body)
              .when()
                    .post("/posts");

        /*.then()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created")
                .assertThat()
                .body(
                        "userId", equalTo(1037),
                        "body", equalTo("Body Example"),
                        "title", equalTo("New Single Resource")

                )
                .log().body()
                .extract().path("body");*/
    }

    public Response updatePost(final int postId, File body) {
        return given()
                    .pathParam("postId", postId)
                    .contentType(ContentType.JSON)
                    .body(body)
               .when()
                    .put("/posts/{postId}");
    }

    public Response patchPost(final int postId, File body) {
        return given()
                    .pathParam("postId", postId)
                    .contentType(ContentType.JSON)
                    .body(body)
               .when()
                    .patch("/posts/{postId}");
    }

    public Response deletePost(final int postId) {
        return given()
                    .pathParam("postId", postId)
               .when()
                    .delete("/posts/{postId}");
    }
}
