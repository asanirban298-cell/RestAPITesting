package options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", glue = "stepDefinitions", plugin = {
		"json:target/jsonReports/cucumber-report.json" }/* , tags = "@DeletePlace" */)
public class TestRunner extends AbstractTestNGCucumberTests {

}
