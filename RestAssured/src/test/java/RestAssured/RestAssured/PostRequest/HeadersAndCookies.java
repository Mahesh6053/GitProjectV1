package RestAssured.RestAssured.PostRequest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersAndCookies extends BaseClassForCreationBookings{
	
	@Test
	public void HeaderAndCookie()
	{
		
		Response responseheader = (Response) RestAssured.given(spec).get("/booking");
		
		//first way to get the headers
		
		Headers headers = responseheader.getHeaders();
		System.out.println(headers);
		
		//second way to get headers
		
		Header header = headers.get("server");
		System.out.println(header.getName()+":"+header.getValue());
		
		//get cookies
		
		System.out.println("\ncookies are");
		Cookies coookies = responseheader.detailedCookies();
		System.out.println(coookies);
		
		//another way to get cookies
		
		Cookie cokkie = coookies.get("server");
		System.out.println(cokkie);
		
	}
	
	
	
	
	
	

}
