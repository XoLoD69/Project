package apiTestsAuthPOST;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {

    private final static String URL = "https://test-stand.gb.ru/";

    @Test
    public void unsuccessfulAuthTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.badResponseSpecification401());

        String authError = "Invalid credentials.";

        AuthData user = new AuthData("Student01", "153151651");

        Response unsuccessfulAuthData = given()
                .body(user)
                .when()
                .post("api/login")
                .then()
                .assertThat()
                .statusCode(401)
                .assertThat()
                .contentType(ContentType.JSON)
                .log().all()
                .body("error", Matchers.equalTo(authError))
                .extract().response();

        JsonPath jsonPath = unsuccessfulAuthData.jsonPath();
        String errorMessage = jsonPath.get("error");

        Assert.assertEquals(authError, errorMessage);
        Assert.assertNotNull(errorMessage);
    }


    @Test
    public void successAuthTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Integer id = 22297;
        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";
        String login = "Student01";

        AuthData user1 = new AuthData("Student01", "e3bf644ea6");

        Response response = given()
                .body(user1)
                .when()
                .post("api/login")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Integer userId = jsonPath.get("userId");
        String name = jsonPath.get("username");
        String token = jsonPath.get("token");

        Assert.assertEquals(id, userId);
        Assert.assertNotNull(userId);
        Assert.assertEquals(login, name);
        Assert.assertNotNull(name);
        Assert.assertEquals(userToken, token);
        Assert.assertNotNull(token);
    }


    @Test
    public void emptyAuthDataTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.badResponseSpecification401());

        String authError = "Invalid credentials.";

        AuthData user2 = new AuthData("","");

        Response emptyAuthData = given()
                .body(user2)
                .when()
                .post("api/login")
                .then()
                .assertThat()
                .statusCode(401)
                .assertThat()
                .contentType(ContentType.JSON)
                .log().all()
                .body("error", Matchers.equalTo(authError))
                .extract().response();

        JsonPath jsonPath = emptyAuthData.jsonPath();
        String errorMessage = jsonPath.get("error");

        Assert.assertEquals(authError, errorMessage);
        Assert.assertNotNull(errorMessage);

    }

    @Test
    public void emptyLoginAuthTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.badResponseSpecification401());

        AuthData user3 = new AuthData("","e3bf644ea6");

        Response emptyCredentials = given()
                .body(user3)
                .when()
                .post("api/login")
                .then()
                .assertThat()
                .statusCode(401)
                .assertThat()
                .contentType(ContentType.JSON)
                .log().all()
                .extract().response();

        JsonPath jsonPath = emptyCredentials.jsonPath();
        String error = jsonPath.get("error");

        String errorMessage = "Invalid credentials.";

        Assert.assertEquals(errorMessage, error);
    }

    @Test
    public void emptyPasswordAuthTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.badResponseSpecification401());

        AuthData user3 = new AuthData("Student01","");

        Response emptyCredentials = given()
                .body(user3)
                .when()
                .post("api/login")
                .then()
                .assertThat()
                .statusCode(401)
                .assertThat()
                .contentType(ContentType.JSON)
                .log().all()
                .extract().response();

        JsonPath jsonPath = emptyCredentials.jsonPath();
        String error = jsonPath.get("error");

        String errorMessage = "Invalid credentials.";

        Assert.assertEquals(errorMessage, error);
    }
}
