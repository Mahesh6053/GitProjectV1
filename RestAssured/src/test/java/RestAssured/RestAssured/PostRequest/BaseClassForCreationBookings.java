package RestAssured.RestAssured.PostRequest;

import org.json.JSONObject;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClassForCreationBookings {
	
	protected RequestSpecification spec;
	
	@BeforeTest
	public void setUp()
	{
		spec = new RequestSpecBuilder().
				setBaseUri("https://restful-booker.herokuapp.com").
				build();
	}
	
	protected Response createBooking() {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Mahesh");
		body.put("lastname", "Amisagadda");
		body.put("totalprice", 150);
		body.put("depositpaid", false);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2022-03-25");
		bookingdates.put("checkout", "2022-03-27");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Baby crib");

		// Get response
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString())
				.post("https://restful-booker.herokuapp.com/booking");
		return response;
	}

}
