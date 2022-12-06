package RestAssured.RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheck {
	
	@Test
	public void healthcheckTest()
	{
		given().when().
		get("https://restful-booker.herokuapp.com/apidoc/index.html#api-Ping-Ping").
		then().
		assertThat().
		statusCode(200);
	}

}
