package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		StepDefinition sd = new StepDefinition();

		if (StepDefinition.place_id == null) {
			sd.create_payload_for_add_place_api_with(50, "India", "Hindi");
			sd.call_with_http_request("AddPlaceAPI", "POST");
			sd.validate_with_data_from("India", "GetPlaceAPI");
		}
	}
	
	@After("@GetPlace")
	public void test() {
		
		//Code here will run after tag @GetPlace
	}

}
