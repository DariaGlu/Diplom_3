package api;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserDeletion {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/api";
    public static final String USER_AUTH_POST = "/auth/login";
    public static final String USER_DEL_DELETE = "/auth/user";

    public static RequestSpecification getBaseReqSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .build();
    }

    @Step("Delete user")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getBaseReqSpec())
                .header("authorization", accessToken)
                .when()
                .delete(USER_DEL_DELETE)
                .then();
    }
}
