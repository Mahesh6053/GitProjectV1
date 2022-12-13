package RestAssuredRahulShetty;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.PayLoad;
import Files.RawtoJson;

import static org.hamcrest.Matchers.*;

public class PostMethodOrAddPlace {

	@Test
	public void postt() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// response
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PayLoad.payload()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response()
				.asString();

		System.out.println(response);

		// parsing, extract and get response
		JsonPath js = new JsonPath(response);

		String js_placeId = js.getString("place_id");
		System.out.println("place_id : " + js_placeId);

		// update the info
		String Expected_Address = "Tangutur, ongole";
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + js_placeId + "\",\r\n" + "\"address\":\"Tangutur, ongole\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("/maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).header("Server", "Apache/2.4.41 (Ubuntu)");

		/*
		 * JsonPath js1 = new JsonPath(response1); String updated_address =
		 * js1.getString("address"); System.out.println(updated_address);
		 */
		// get the above info validate updated info is available or not.

		String response1 = given().queryParam("key", "qaclick123").queryParam("place_id", js_placeId).when()
				.get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js1 = RawtoJson.rawToJson(response1);
		String Actual_address = js1.getString("address");
		System.out.println(Actual_address);
		Assert.assertEquals(Actual_address, Expected_Address);

	}

}
