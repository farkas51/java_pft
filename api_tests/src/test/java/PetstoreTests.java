import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojos.OrderPojo;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class PetstoreTests {
    @BeforeMethod
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

    @Test
    public void createOrder(){
        OrderPojo order = new OrderPojo();
        int pet_id = new Random().nextInt(500000);
        int order_id = 9;
        order.setId(order_id);
        order.setPetId(pet_id);
            given()
                        .body(order)
                    .when()
                        .post("/store/order")
                    .then()
                        .statusCode(200);
        OrderPojo actual_order =
            given()
                        .pathParam("orderId",order_id)
                    .when()
                        .get("/store/order/{orderId}")
                    .then()
                        .statusCode(200)
                    .extract().body()
                    .as(OrderPojo.class);

             Assert.assertEquals(actual_order.getId(), order.getId());
    }

    @Test
    public void deleteOrder(){
        int order_id = 9;
            given()
                        .pathParam("orderId",order_id)
                    .when()
                        .delete("/store/order/{orderId}")
                    .then()
                        .statusCode(200);
            given()
                        .pathParam("orderId",order_id)
                    .when()
                        .get("/store/order/{orderId}")
                    .then()
                        .statusCode(404);
    }
}
