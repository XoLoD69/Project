package apiTestsNotMyPostsGet;

import apiTestsAuthPOST.Specifications;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NotMyPostsTest {
    private final static String URL = "https://test-stand.gb.ru/";

    @Test
    public void notMyPostPage1Asc() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("owner", "notMe")
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

        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void notMyPostPage0() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "0")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> data = jsonPath.get("data");
        List<Integer> id = jsonPath.get("data.id");

        Assert.assertNotNull(data);

        Assert.assertNotNull(id);
        for (int i = 0; i < id.size(); i++) {
            Assert.assertEquals(4, id.size());
        }


        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 1;

        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void notMyPostPage1Desc() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("owner", "notMe")
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
        List<Integer> data = jsonPath.get("data");
        List<Integer> id = jsonPath.get("data.id");

        Assert.assertNotNull(data);

        Assert.assertNotNull(id);
        for (int i = 0; i < id.size(); i++) {
            Assert.assertEquals(4, id.size());
        }


        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 2;

        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void notMyPostPageWithOwnerQueryOnly() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("owner", "notMe")
                //.queryParam("sort", "createdAt")
                //.queryParam("order", "DESC")
                //.queryParam("page", "1")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> data = jsonPath.get("data");
        List<Integer> id = jsonPath.get("data.id");

        Assert.assertNotNull(data);

        Assert.assertNotNull(id);
        for (int i = 0; i < id.size(); i++) {
            Assert.assertEquals(4, id.size());
        }


        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 2;

        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void notMyPostPage2withAllOrder() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());

        Response startPage = RestAssured.given()
                .header("X-Auth-Token", userToken)
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ALL")
                .queryParam("page", "2")
                .when()
                .get("api/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()
                .extract().response();

        JsonPath jsonPath = startPage.jsonPath();
        List<Integer> data = jsonPath.get("data");
        List<Integer> id = jsonPath.get("data.id");

        Assert.assertNotNull(data);

        Assert.assertNotNull(id);
        for (int i = 0; i < id.size(); i++) {
            Assert.assertEquals(4, id.size());
        }


        Integer beforePage = jsonPath.get("meta.prevPage");
        Integer afterPage = jsonPath.get("meta.nextPage");
        Integer expectedPrevPage = 1;
        Integer expectedNextPage = 4;

        Assert.assertNotNull(beforePage);
        Assert.assertEquals(expectedPrevPage, beforePage);
        Assert.assertNotNull(afterPage);
        Assert.assertEquals(expectedNextPage, afterPage);
    }

    @Test
    public void NotMyPostsPage1WithoutToken() {

        String userToken = "eab0d6ff0718ba8de74cba5982b298d3";

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.badResponseSpecification401());

        Response startPage = RestAssured.given()
                //.header("X-Auth-Token", userToken)
                .queryParam("owner", "notMe")
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
