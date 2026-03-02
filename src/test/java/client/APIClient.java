
package client;

import Base.BaseAPI;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class APIClient {

    public static Response post(String endpoint, Object body) {

        return given()
                .spec(BaseAPI.getRequestSpec())
                .body(body)
        .when()
                .post(endpoint).then()
                .log().all()          // logs response
                .extract().response();
    
    }

    public static Response get(String endpoint, Object pathParam) {

        return given()
                .spec(BaseAPI.getRequestSpec())
                .pathParam("petId", pathParam)
        .when()
                .get(endpoint).then()
                .log().all()          // logs response
                .extract().response();
    
    }

    public static Response put(String endpoint, Object body) {

        return given()
                .spec(BaseAPI.getRequestSpec())
                .body(body)
        .when()
                .put(endpoint).then()
                .log().all()          // logs response
                .extract().response();
    
    }

    public static Response delete(String endpoint, Object pathParam) {

        return given()
                .spec(BaseAPI.getRequestSpec())
                .pathParam("petId", pathParam)
        .when()
                .delete(endpoint).then()
                .log().all()          // logs response
                .extract().response();
    
    }
}