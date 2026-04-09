package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.Assert;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utilities {

	RequestSpecification reqSpec;
	ResponseSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;

	@Given("Create Payload for AddPlace API with {int}, {string}, {string}")
	public void create_payload_for_add_place_api_with(int accuracy, String address, String lang) throws IOException {

		reqSpec = given().spec(requestSpecification()).body(data.addPlacePayLoad(accuracy, address, lang));

	}

	@When("Call {string} with {string} http request")
	public void call_with_http_request(String resource, String method) {

		APIResources apiRes = APIResources.valueOf(resource);

		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		System.out.println(apiRes.getResource());

		if (method.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(apiRes.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = reqSpec.when().get(apiRes.getResource());
		}

		/*
		 * .then().spec(res) .header("Access-Control-Allow-Methods",
		 * equalTo("POST")).header("Server", "Apache/2.4.52 (Ubuntu)") .body("status",
		 * equalTo("OK")).extract().response()
		 */

	}

	@Then("API call is successful with status code {int}")
	public void api_call_is_successful_with_status_code(Integer int1) {

		Assert.assertTrue(response.getStatusCode() == 200);

	}

	@Then("Response body contains {string} with value {string}")
	public void response_body_contains_with_value(String key, String value) {

		Assert.assertTrue(getJSONPath(response, key).equalsIgnoreCase(value));

	}

	@Then("Validate {string} with data from {string}")
	public void validate_with_data_from(String key, String resource) throws IOException {

		place_id = getJSONPath(response, "place_id");
		reqSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
		APIResources apiRes = APIResources.valueOf(resource);
		call_with_http_request(resource, "Get");
		String address = getJSONPath(response, "address");
		Assert.assertTrue(address.equalsIgnoreCase(key));
		System.out.println(address);
		System.out.println(key);

	}

	@Then("Create Payload for DeletePlace API")
	public void create_payload_for_delete_place_api() throws IOException {

		reqSpec = given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));

	}

}
