package RestAssuredRahulShetty;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.PayLoad;
import Files.RawtoJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetAndExtractResponse {
	
	@Test
	public void extract()
	{
RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//response
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
		 body(PayLoad.payload()).when().post("/maps/api/place/add/json").then().
		 assertThat().statusCode(200).body("scope", equalTo("APP")).
		 header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		//raw to json conversion and extract
		JsonPath js = RawtoJson.rawToJson(response);
		
		String js_response = js.getString("place_id");
		System.out.println("place_id : "+js_response);
		
		
		
	}

}
