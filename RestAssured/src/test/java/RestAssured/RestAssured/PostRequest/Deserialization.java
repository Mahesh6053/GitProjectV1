package RestAssured.RestAssured.PostRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Deserialization extends BaseClassForCreationBookings {
	@Test
	public void createBookingWithPOJOTest() {
		// Create body using POJOs
		Bookingdates bookingdates = new Bookingdates("2020-03-25", "2020-03-27");
		Bookingserialization booking = new Bookingserialization("Mahesh", "Amisagadda", 200, false, bookingdates,
				"Baby crib");

		// Get response
		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(booking).post("/booking");
		response.print();
		Booking111 bookingid = response.as(Booking111.class);

		// Verifications
		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		System.out.println("Request booking : " + booking.toString());
		System.out.println("Response booking: " + bookingid.getBookingdates().toString());

		// Verify All fields
		Assert.assertEquals(bookingid.getBookingdates().toString(), booking.toString());
	}

}
