package RestAssured.RestAssured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import RestAssured.RestAssured.PostRequest.BaseClassForCreationBookings;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetHttpMethod extends BaseClassForCreationBookings{
	
	@Test
	public void getBookingIds()
	{
		  //query parameter 
		spec.queryParam("firstname", "Susan");
		 
		Response response = RestAssured.given(spec).get("/booking");
		response.print();
		
		//validate the booking response statuscode ex:200
		
		Assert.assertEquals(response.statusCode(), 200, "The status code is different from");
		
		// verify atleast one booking id in response
		
		List<Integer> listId = response.jsonPath().getList("bookingid");
		Assert.assertTrue(!listId.isEmpty(), "The response code is null");
		
		
		
	}

}
