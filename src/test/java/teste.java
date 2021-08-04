import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class teste {

    @Test
    public void testeApiSimples(){

        given()
                .log().all()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .log().all()
                .statusCode(200).extract().path("");
    }

    @Test
    public void testeApiUtilizandoGiven(){

        String path = "1";

        given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .basePath("posts")
                .pathParam("id", path)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body(
                        "userId", is(1),
                        "title", is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                );
    }

    @Test()
    public void testeApiUtilizandoObjeto(){

        String path = "1";

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .basePath("posts")
                .pathParam("id", path)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("{id}")
                .then()
                .log().all()
                .extract().response();

        assertEquals(response.body().path("userId").toString(), "1");
        assertEquals(response.body().path("title"), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }
}
