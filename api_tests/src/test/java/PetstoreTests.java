import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetstoreTests {
    @BeforeClass
    public void prepare() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", System.getProperty("api.key"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
//задание со звёздочкой
    @Test
    public void getInventory() {
        Map<String, Integer> data =  given()
                .contentType(ContentType.JSON)
                .when().get("/store/inventory")
                .then().statusCode(200)
                .extract().jsonPath().getMap("");

        Assert.assertTrue(data.containsKey("pending"), "Data не содержит ключа pending" );
    }


}
