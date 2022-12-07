package RestAssured.RestAssured.PostRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteRequest extends BaseClassForCreationBookings {
	
	@Test
	public void deleteRequest()
	{
		//create booking
		Response responseCreate = createBooking();
		responseCreate.print();
		
		//get booking id for new booking
		
		int bookingid = responseCreate.jsonPath().getInt("bookingid");
		
		//del booking
		
		Response responseDelete = (Response) RestAssured.given().auth().preemptive().basic("admin", "password123").delete("https://restful-booker.herokuapp.com/booking/"+bookingid);
		responseDelete.print();
		
		//verify response 201
		
		Assert.assertEquals(responseDelete.statusCode(), 201, "201 is not matched with expected");
		
		// get response after delete the bookings
		Response responseget = RestAssured.given(spec).get("/booking/"+bookingid);
		responseget.print();
		
		Assert.assertEquals(responseget.getBody().asString(), "Not Found", "Not Found should not match with expected");
		
	}

}
