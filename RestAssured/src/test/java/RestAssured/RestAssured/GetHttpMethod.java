package RestAssured.RestAssured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetHttpMethod {
	
	@Test
	public void getBookingIds()
	{
		//get response from booking id table
		
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
		response.print();
		
		//validate the booking response statuscode ex:200
		
		Assert.assertEquals(response.statusCode(), 200, "The status code is different from");
		
		// verify atleast one booking id in response
		
		List<Integer> listId = response.jsonPath().getList("bookingid");
		Assert.assertTrue(!listId.isEmpty(), "The response code is null");
		
		
		
	}

}
