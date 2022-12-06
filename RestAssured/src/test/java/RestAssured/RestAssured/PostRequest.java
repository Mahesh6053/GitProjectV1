package RestAssured.RestAssured;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequest {
	
	@Test
	public void createBookingId()
	{
		JSONObject body =new JSONObject();
		body.put("First Name", "mahesh");
		body.put("lname", "Amisagadda");
		body.put("id", 543);
		
		JSONObject body1 = new JSONObject();
		body1.put("Start date", "22-06-22");
		body1.put("end date", "22-07-22");
		
		//get response
		
		Response response = (Response) RestAssured.given().contentType(ContentType.JSON).
				body(body1.toString()).post("https://restful-booker.herokuapp.com/booking");
		response.print();
		
		
	}

}
