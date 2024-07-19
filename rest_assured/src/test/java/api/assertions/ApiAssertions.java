package api.assertions;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiAssertions {

    public void validateStatusCode(Response response, int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    public void validateStatusLine(Response response, String statusLine) {
        assertThat(response.getStatusLine(), equalTo(statusLine));
    }

    public void validateJsonSchema(Response response, File schema) {
        assertThat(response.getBody().asString(), matchesJsonSchema(schema));
    }
}
