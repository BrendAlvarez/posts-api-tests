package api.tests;

import api.TestBase;
import api.assertions.ApiAssertions;
import api.endpoints.PostsEndpoint;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;


public class PostsTests extends TestBase  {

    private PostsEndpoint postsApi;
    private ApiAssertions apiAssertions;

    @BeforeClass
    public void init() {
        postsApi = new PostsEndpoint();
        apiAssertions = new ApiAssertions();
    }

    @Test
    public void getAllPosts() {
        Response response = postsApi.getAllPosts();
        File responseSchema = new File("resources/postsArraySchema.json");

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");
        apiAssertions.validateJsonSchema(response, responseSchema);
    }

    @Test
    public void getExistingPost() {
        Response response = postsApi.getPostById(1);
        File responseSchema = new File("resources/singlePostSchema.json");

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");
        apiAssertions.validateJsonSchema(response, responseSchema);
        // TODO: validate body
    }

    @Test
    public void getUnexistingPost() {
        Response response = postsApi.getPostById(0);

        apiAssertions.validateStatusCode(response, 404);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 404 Not Found");
    }

    @Test
    public void getCommentsFromExistingPost() {
        Response response = postsApi.getPostComments(1);
        File responseSchema = new File("resources/commentSchema.json");

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");
        apiAssertions.validateJsonSchema(response, responseSchema);
    }

    @Test
    public void getCommentsFromNotExistingPost() {
        Response response = postsApi.getPostComments(0);

        apiAssertions.validateStatusCode(response, 404);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 404 Not Found");
    }

    @Test
    public void createPost() {
        File body = new File("resources/TestData/postResource.json");

        Response response = postsApi.createPost(body);

        apiAssertions.validateStatusCode(response, 201);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 201 Created");
        // TODO: check if post is created
    }

    @Test
    public void createPostWithInvalidFields() { // check if valid
        File body = new File("resources/TestData/postResourceInvalidFields.json");

        Response response = postsApi.createPost(body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is created
    }

    @Test
    public void createPostWithMissingFields() { // check
        File body = new File("resources/TestData/postResourceMissingFields.json");

        Response response = postsApi.createPost(body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is created
    }

    @Test
    public void createPostWithEmptyBody() {
        File body = new File("resources/TestData/emptyBody.json");

        Response response = postsApi.createPost(body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is created
    }

    @Test
    public void updatePost() {
        File body = new File("resources/TestData/postResource.json");

        Response response = postsApi.updatePost(1, body);

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");
        // TODO: check if post is updated
    }

    @Test
    public void updateNotExistingPost() {
        File body = new File("resources/TestData/postResource.json");

        Response response = postsApi.updatePost(0, body);

        apiAssertions.validateStatusCode(response, 404);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 404 Not Found");
    }

    @Test
    public void updatePostWithInvalidFields() {
        File body = new File("resources/TestData/postResourceInvalidFields.json");

        Response response = postsApi.updatePost(1, body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is updated
    }

    @Test
    public void updatePostWithMissingFields() {
        File body = new File("resources/TestData/postResourceMissingFields.json");

        Response response = postsApi.updatePost(1, body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is updated
    }

    @Test
    public void updatePostWithEmptyBody() {
        File body = new File("resources/TestData/emptyBody.json");

        Response response = postsApi.updatePost(1, body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is updated
    }

    @Test
    public void patchPost() {
        File body = new File("resources/TestData/postResource.json");

        Response response = postsApi.patchPost(1, body);

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");
        // TODO: check if post is patched
    }

    @Test
    public void patchNotExistingPost() {
        File body = new File("resources/TestData/postResource.json");

        Response response = postsApi.patchPost(0, body);

        apiAssertions.validateStatusCode(response, 404);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 404 Not Found");
    }

    @Test
    public void patchPostWithInvalidFields() {
        File body = new File("resources/TestData/postResourceInvalidFields.json");

        Response response = postsApi.patchPost(1, body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is patched
    }

    @Test
    public void patchPostWithEmptyBody() {
        File body = new File("resources/TestData/emptyBody.json");

        Response response = postsApi.patchPost(1, body);

        apiAssertions.validateStatusCode(response, 400);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 400 Bad Request");
        // TODO: check if post is patched
    }

    @Test
    public void deletePost() {
        Response response = postsApi.deletePost(100);

        apiAssertions.validateStatusCode(response, 200);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 200 OK");

        // TODO: check if post was deleted successfully
    }

    @Test
    public void deleteNotExistingPost() {
        Response response = postsApi.deletePost(0);

        apiAssertions.validateStatusCode(response, 404);
        apiAssertions.validateStatusLine(response, "HTTP/1.1 404 Not Found");
    }
}
