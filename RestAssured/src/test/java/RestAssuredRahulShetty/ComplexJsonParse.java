package RestAssuredRahulShetty;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	@Test
	public void complexParse() {
		// print no.of courses

		JsonPath js2 = new JsonPath(PayLoad.payload1());
		int count = js2.getInt("courses.size()");
		System.out.println(count);

		// print the purchase amount

		int totalcost = js2.getInt("dashboard.purchaseAmount");
		System.out.println(totalcost);

		// print title of 1st course

		String first_title = js2.getString("courses[0].title");
		System.out.println(first_title);

		// print all courses w.r.to title

		for (int i = 0; i < count; i++) {
			String coursetitles = js2.get("courses[" + i + "].title");
			System.out.print(coursetitles + ":");
			System.out.println(js2.get("courses[" + i + "].price").toString());
		}

		// no of copies sold by rpa

		for (int i = 0; i < count; i++) {
			String coursetitles = js2.get("courses[" + i + "].title");
			if (coursetitles.equalsIgnoreCase("Selenium python")) {
				System.out.println(js2.get("courses[" + i + "].copies").toString());
				break;
			}

		}

		// find the total-cost

		int finalCost = 0;

		for (int i = 0; i < count; i++) {
			int total_price = js2.getInt("courses[" + i + "].price");
			int total_copies = js2.getInt("courses[" + i + "].copies");
			finalCost = finalCost + total_price * total_copies;
		}
		System.out.println("Final cost : " + finalCost);
		
		Assert.assertEquals(totalcost, finalCost, "actual finall! cost is not as expected");

	}

}
