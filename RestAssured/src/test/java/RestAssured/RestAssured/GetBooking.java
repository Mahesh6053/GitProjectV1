package RestAssured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import RestAssured.RestAssured.PostRequest.BaseClassForCreationBookings;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBooking extends BaseClassForCreationBookings {

	@Test
	public void getBookingTest() {
		// Get response with booking
		Response response = RestAssured.given(spec).get("/booking/5");
		response.print();

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = response.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Mary", "firstname in response is not expected");

		String actualLastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Ericsson", "lastname in response is not expected");

		int price = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(price, 492, "totalprice in response is not expected");

		boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertFalse(depositpaid, "depositpaid should be true, but it's nott");

		String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2020-06-06", "checkin in response is not expected");

		String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2021-08-11", "checkout in response is not expected");

		/*
		 * String actualAdditionalneeds =
		 * response.jsonPath().getString("additionalneeds"); so
		 Assert.assertEquals(actualAdditionalneeds, "Breakfast", "additionalneeds in response is not expected");*/
		
		softAssert.assertAll();
	}

}