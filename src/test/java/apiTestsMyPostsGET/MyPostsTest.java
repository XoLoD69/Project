package apiTestsMyPostsGET;

import apiTestsAuthPOST.Specifications;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class MyPostsTest {
    private final static String URL = "https://test-stand.gb.ru/";

    @Test
    public void myPostsPage1Asc() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();

        List<Integer> id = jsonPath.get("data.id");

        Assert.assertNotNull(id);
        for (int i = 0; i < id.size(); i++) {
            Assert.assertEquals(4, id.size());
        }


        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 2;

        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertEquals(expectedNextPage, afterPage);


        List<Integer> authorId = jsonPath.get("data.authorId");
        Integer auID = 22297;

        Assert.assertNotNull(authorId);
        for (int i = 0; i < authorId.size(); i++) {
            Assert.assertEquals(auID, authorId.get(i));
        }
    }

    @Test
    public void myPostsPage1Desc() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> id = jsonPath.get("data.id");
        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        List<Integer> authorId = jsonPath.get("data.authorId");
        List<String> title = jsonPath.get("data.title");

        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 2;
        String firstTitle = "пост 10";
        Integer auID = 22297;

        Assert.assertNotNull(title);
        for (int i = 0; i < title.size(); i++) {
            Assert.assertEquals(firstTitle, title.get(0));
        }

        Assert.assertNotNull(id);
        for (int i = 0; i < id.size(); i++) {
            Assert.assertEquals(4, id.size());
        }

        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertEquals(expectedNextPage, afterPage);

        Assert.assertNotNull(authorId);
        for (int i = 0; i < authorId.size(); i++) {
            Assert.assertEquals(auID, authorId.get(i));
        }
    }

    @Test
    public void myPostsPage3WithoutOrder() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("sort", "createdAt")
                //.queryParam("order", "DESC")
                .queryParam("page", "2")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> data = jsonPath.get("data");
        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 3;

        Assert.assertNotNull(data);
        for (int i = 0; i < data.size(); i++) {
            Assert.assertEquals(4, data.size());
        }
        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void myPostsPage1WithoutParams() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                //.queryParam("sort", "createdAt")
                //.queryParam("order", "DESC")
                //.queryParam("page", "6")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> data = jsonPath.get("data");
        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 2;

        Assert.assertNotNull(data);
        for (int i = 0; i < data.size(); i++) {
            Assert.assertEquals(4, data.size());
        }

        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void myPostsPageWithoutPage() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                //.queryParam("page", "1")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> data = jsonPath.get("data");
        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 2;

        Assert.assertNotNull(data);
        for (int i = 0; i < data.size(); i++) {
            Assert.assertEquals(4, data.size());
        }

        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void myPostsPage10() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", 10)
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> data = jsonPath.get("data");
        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 9;
        Integer expectedNextPage = null;

        for (int i = 0; i < data.size(); i++) {
            Assert.assertEquals(null, data.size());
        }

        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void myPostsPage1WithoutToken() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.badResponseSpecification401());

        Response startPage = RestAssured.given()
                //.header("X-Auth-Token", userToken)
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", 1)
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(401)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        String message = jsonPath.get("message");


        String text = "Auth header required X-Auth-Token";

        Assert.assertEquals(text, message);
    }
}
