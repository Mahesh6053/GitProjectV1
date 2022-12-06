package RestAssured.RestAssured.PostRequest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PartialUpdateBooking extends BaseClassForCreationBookings{
	
	@Test
	public void partialUpdate()
	{
		//create booking
		Response responsePartial = createBooking();
		responsePartial.print();
		
		//get booking id 
		
		int bookingId = responsePartial.jsonPath().getInt("bookingid");

		//create json body
		
		JSONObject body01 = new JSONObject();
		body01.put("firstname", "venu");
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "06-12-2022");
		bookingdates.put("checkout", "07-12-2022");
		
		body01.put("bookingdates", bookingdates);
		
		//partial update booking
		
		Response responsePartialUpdate = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").
				contentType(ContentType.JSON).body(body01.toString())
				.patch("/booking/" + bookingId);
		responsePartialUpdate.print();
		
		
		//verifications
		
		Assert.assertEquals(responsePartialUpdate.getStatusCode(), 200, "Status code should be 200, but it's not.");
		
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = responsePartialUpdate.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "venu", "firstname in response is not expected");

		String actualLastName = responsePartialUpdate.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Amisagadda", "lastname in response is not expected");

		int price = responsePartialUpdate.jsonPath().getInt("totalprice");
		softAssert.assertEquals(price, 150, "totalprice in response is not expected");

		boolean depositpaid = responsePartialUpdate.jsonPath().getBoolean("depositpaid");
		softAssert.assertFalse(depositpaid, "depositpaid should be true, but it's not");

		String actualCheckin = responsePartialUpdate.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2022-06-12", "checkin in response is not expected");

		String actualCheckout = responsePartialUpdate.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2022-07-12", "checkout in response is not expected");

		String actualAdditionalneeds = responsePartialUpdate.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");

		softAssert.assertAll();
		
		
		
		
	}

}
