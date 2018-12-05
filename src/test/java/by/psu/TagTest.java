package by.psu;

import com.jayway.restassured.RestAssured;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;


public class TagTest extends BaseRestApiTest {
    @Test
    public void testSaveTagWithSecondLanguage() {
        System.out.println(RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath);
        given().basePath("http://localhost").port(8008).when().get("/api/tags").then().statusCode(200);
    }
}
